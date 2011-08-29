import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 *  The main classfor a memory manager.  Creates a client to parse and
 *  feed commands into a memory manager.
 *
 *  @author Joshua Rush
 *  @author Benjamin Roble
 *  @version Aug 29, 2011
 */
public class Memman
{

    private static Client client;
    // ----------------------------------------------------------
    /**
     * Creates a client to manage memory and execute commands from
     * a file.
     * @param args a list of arguments to execute the function.
     * args[0] the memory pool size
     * args[1] the size of the record array
     * args[2] the filepath to the text file containing the commands.
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
    throws FileNotFoundException
    {
        client = new Client(Integer.valueOf(args[0]),
            Integer.valueOf(args[1]), args[2]);
    }


}
