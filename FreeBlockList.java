
// -------------------------------------------------------------------------
/**
 *  A class that represents free blocks in a memory pool.
 *  The blocks are arranged in decreasing order according to size
 *  in a doubly linked list.  Each block knows its size and index in memory;
 *  blocks can be used or freed.
 *
 *  @author Joshua Rush
 *  @author Benjamin Roble
 *  @version Aug 29, 2011
 */
public class FreeBlockList
{
    private Node head;
    private Node tail;

    /**
     * Create a new FreeblockList with one node representing a free
     * block the size of the memory pool.
     * @param memorySize the initial size of the freeblockList in bytes.
     */
    public FreeBlockList(int memorySize)
    {
        Node node = new Node(memorySize, 0, tail, head);
        head.next = node;
        tail.prev = node;
    }

    /**
     * Uses up a block of the indicated size.
     * @param size the size of the block of memory being used.
     * @return the position in memory where the used block will start
     */
    public int use(int size)
    {
        Node currentNode = tail.prev;
        while (currentNode.size < size)
        {
            currentNode = currentNode.prev;
            if (currentNode == head)
            {
                System.out.println("not enough free space exists");
                return -1;
            }
        }
        remove(currentNode);
        if (currentNode.size > size)
        {
            sort(new Node(currentNode.size - size, currentNode.index + size));
        }
        return currentNode.index;
    }
    /**
     * A node of the indicated size and position is added to the
     * freelist.  Merge adjacent nodes if necessary.
     * @param size the size in bytes of the new node being added
     * @param position the position in memory where the new node starts
     */
    public void free(int size, int position)
    {
        Node newNode = new Node(size, position);
        newNode = merge(newNode);
        sort(newNode);
    }
    /**
     * Sort the new node into the correct position in the linked list.
     * @param node the node to be sorted into the list
     */
    private void sort(Node node)
    {
        Node currentNode = head.next;
        Node newNode = node;
        while (newNode.next == null)
        {
            if (currentNode == tail || currentNode.size < newNode.size ||
               (currentNode.size == newNode.size &&
                    newNode.index < currentNode.index))
            {
                insertBefore(newNode, currentNode);
            }
            currentNode = currentNode.next;
        }
    }
    /**
     * Insert the new node before the target node in the linked list.
     * @param newNode the node to be inserted into the list
     * @param beforeNode the node the new node will be added before
     */
    private void insertBefore(Node newNode, Node beforeNode)
    {
        beforeNode.prev.next = newNode;
        newNode.next = beforeNode;
        newNode.prev = beforeNode.prev;
        beforeNode.prev = newNode;
    }
    /**
     * Merge any adjacent nodes to the node being added to the list
     * and return the merged node.
     * @param newNode the node being added to the list
     * @return the new Node after merging with any adjacent nodes
     */
    private Node merge(Node newNode)
    {
        Node currentNode = head.next;
        Node mergeNode = newNode;
        while (currentNode != tail)
        {
            if (currentNode.index + currentNode.size == mergeNode.index)
            {
                remove(currentNode);
                mergeNode.size += currentNode.size;
                mergeNode.index = currentNode.index;
            }
            else if (mergeNode.index + mergeNode.size == currentNode.index)
            {
                remove(currentNode);
                mergeNode.size += currentNode.size;
            }
            currentNode = currentNode.next;
        }
        return mergeNode;
    }

    private void remove(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * A basic node in a doubly linked list, storing a single data value and
     * references to both the next node and the previous node in the linked
     * chain.
     */
    private static class Node
    {
      //~ Instance/static variables .........................................

        private int size;
        private int index;
        private Node next;
        private Node prev;


        // ----------------------------------------------------------

        /**
         * Creates a node with null next and previous pointers.
         * @param size the size of memory this node represent
         * @param index the starting position of this block of memory
         */
        public Node(int size, int index)
        {
            this(size, index, null, null);
        }

        /**
         * Creates a node with a link to the specified node.
         * @param size the size of free space the node represents
         * @param index the position the block starts at
         * @param next the node to follow this one in the list
         * @param prev the node to precede this one in the list
         */
        public Node(int size, int index, Node next, Node prev)
        {
            setSize(size);
            setIndex(index);
            setNext(next);
            setPrev(prev);
        }

        //~ Public methods ....................................................

        // ----------------------------------------------------------
        /**
         * Set the size value stored in this node.
         * @param value the new size value to set
         */
        public void setSize(int value)
        {
            size = value;
        }

        /**
         * Set the index value stored in this node.
         * @param value the new index value to set
         */
        public void setIndex(int value)
        {
            index = value;
        }

        /**
         * Set the value of this node's next pointer.
         * @param value the node is to point to as the next one in the circle
         */
        public void setNext(Node value)
        {
            next = value;
        }

        /**
         * Set the value of this node's previous pointer.
         * @param value the node is to point to as the previous one in
         * the circle
         */
        public void setPrev(Node value)
        {
            prev = value;
        }

    }
}
