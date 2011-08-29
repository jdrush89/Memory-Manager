import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Holds an ArrayList of Handles.  Users can specify the
 *  position of the Handle in the array to access that Handle.
 *  Insertions and Removals can be made from the record array.
 *
 *  @author Josh
 *  @version Aug 28, 2011
 */
public class RecordArray
{
    private ArrayList<Handle> records;

    /**
     * Creates a RecordArray.
     * @param size the size of the record array.
     */
    public RecordArray(int size)
    {
        records = new ArrayList<Handle>(size);
    }

    /**
     * Returns the size of the RecordArray
     * @return the capacity of the RecordArray
     */
    public int getSize()
    {
        return records.size();
    }

    /**
     * Inserts a Handle at the specified position.
     * @param position the position in the RecordArray to
     * make the insertion
     * @param theHandle the handle to be inserted into the RecordArray
     */
    public void insert(int position, Handle theHandle)
    {
        records.add(position, theHandle);
    }
}
