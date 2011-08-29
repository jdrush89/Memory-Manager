
// -------------------------------------------------------------------------
/**
 *  A class that manages records stored in a memory pool.
 *
 *  @author Joshua Rush
 *  @author Benjamin Roble
 *  @version Aug 28, 2011
 */
public class MemoryManager
{
    private byte[] pool;
    private FreeBlockList freeList;

    /**
     * Creates a memory manager with a memory pool of the
     * specified size.
     * @param poolsize the size of the pool of free memory
     */
    public MemoryManager(int poolsize)
    {
        pool = new byte[poolsize];
        freeList = new FreeBlockList(poolsize);
    }

    /**
     * Inserts a record and returns its position Handle.
     * @param space the record to be inserted
     * @param size the size of the record being inserted
     * @return the position Handle of the inserted record
     */
    public Handle insert(byte[] space, int size)
    {
        int position = freeList.use(size);
        System.arraycopy(space, 0, pool, position, size);
        return new Handle(position);
    }

    /**
     * Frees a block at the indicated posHandle.
     * Merge adjacent blocks if necessary.
     * @param theHandle the position Handle of the record
     * to be removed
     */
    public void remove(Handle theHandle)
    {
        int position = theHandle.getPosition();
        Byte sizeByte = new Byte(pool[position]);
        int sizeInt = sizeByte.intValue();
        //clears the pool of the record
        for (int i = position; i <= position + sizeInt; i++)
        {
            pool[i] = 0;
        }
        freeList.free(sizeInt, position);
    }

    /**
     * Return the record with the indicated position Handle,
     * up to size bytes.
     * @param theHandle the posHandle of the record to be returned
     * @param size the size of the of the record to be returned
     * @return the record from posHandle up to size
     */
    public byte[] get(Handle theHandle, int size)
    {
        byte[] space = new byte[size];
        System.arraycopy(freeList, theHandle.getPosition(), space, 0, size);
        return space;
    }

    /**
     * Prints out a representation of the nodes in the freeblock list.
     */
    public void dump()
    {
        freeList.toString();
    }
}
