
/**
 *  A class to represent the position a record is
 *  stored at in memory.
 *
 *  @author Joshua Rush
 *  @author Benjamin Roble
 *  @version Aug 28, 2011
 */
public class Handle
{
    private int position;
    /**
     * Creates a new Handle object.
     * @param posNum the position of the record
     * this Handle represents in memory
     */
    public Handle(int posNum)
    {
        position = posNum;
    }
    /**
     * Returns the position associated with this Handle.
     * @return the position of this Handle
     */
    public int getPosition()
    {
        return position;
    }
}
