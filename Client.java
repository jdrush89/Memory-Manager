import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Client
{
    private static MemoryManager manager;
    private static RecordArray record;

    public Client(int poolSize, int recSize, String fileName)
    throws FileNotFoundException
    {
        manager = new MemoryManager(poolSize);
        record = new RecordArray(recSize);
        executeCommands(fileName);
    }

    private static void executeCommands(String filePath)
    throws FileNotFoundException
    {
        Scanner lineScanner = new Scanner(new File(filePath));
        try
        {
            while(lineScanner.hasNext())
            {
                parseCommand(lineScanner.next());
            }
        }
        finally
        {
            lineScanner.close();
        }
    }

    private static void parseCommand(String pString)
    {
        String trimString = pString.trim();
        StringTokenizer st = new StringTokenizer(trimString, " ");
        if(st.hasMoreTokens()) {
            String command = st.nextToken();

            if(command.equals("insert"))
                parseInsert(st);
            else if(command.equalsIgnoreCase("insert"))
                parseInsert(st);
            else if(command.equalsIgnoreCase("remove"))
                parseRemove(st);
            else if(command.equalsIgnoreCase("print"))
                parsePrint(st);
            else
                System.out.println("Invalid command '" + command + "'");
        }
    }

    //insert recnum x y name
    private static void parseInsert(StringTokenizer st) {
        if(st.countTokens() != 4)
        {
            System.out.println("Insert commands MUST be in the format 'insert recnum x y name'");
            return;
        }

        int recnum = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        String name = st.nextToken();

        //<THEN CALL INSERT WITH THIS INFO>
    }

    //remove recnum
    private static void parseRemove(StringTokenizer st)
    {
        if(st.countTokens() != 1)
        {
            System.out.println("Remove commands MUST be in the format 'remove recnum'");
            return;
        }

        int recnum = Integer.parseInt(st.nextToken());

        //<THEN CALL REMOVE WITH THIS INFO>

    }

    //print recnum
    //print
    private static void parsePrint(StringTokenizer st)
    {
        if(st.countTokens() != 1 && st.countTokens() != 0)
        {
            System.out.println("print commands MUST be in the format " +
            		"'print recnum' OR 'print'");
            return;
        }
        if(st.hasMoreTokens())
        {
            int recnum = Integer.parseInt(st.nextToken());
            //<THEN CALL PRINT WITH THIS INFO>
        }
        else {
            //<THEN CALL PRINT>
        }

    }
}
