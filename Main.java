import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

/**
 * A main class to test the ArrayAnalyser class.
 * @author nilesh kapoor
 * @version 1.0.0
 */
public class Main
{
    public static final String SCHOOL_BUILDING = "School of Computing Building";

    /**
     * The main method for testing the ArrayAnalyser.
     * @param args Not used.
     * @throws IOException If the file cannot be read.
     */
    public static void main( String[ ] args ) throws IOException
    {
        ArrayAnalyser theAnalyser;
        String[ ] data1 = null, data2 = null;

        if( args.length == 0 )
        {
            // A small data set for testing.
            data1 = new String[ ]
                    { "one", "two", "I", "have", "lost", "my", "shoes", };
            data2 = new String[ ]
                    { "three", "four", "have", "I", "got", "the", "blues", };
            System.out.println( "Testing with a small amount of data." );
        }

        else if( args.length == 2 )
        {
            // Read the two files of data.
            data1 = readDataFromFile( args[ 0 ] );
            data2 = readDataFromFile( args[ 1 ] );
            System.out.println( "Testing with the real data." );
        }
        else
        {
            System.err.println( "Usage: There must be either two file names or no arguments." );
            System.exit(1 );
        }

        System.out.printf( "Array Analyser for %s%n", SCHOOL_BUILDING );
        // Set up the analyser.
        theAnalyser = new ArrayAnalyser( data1, data2 );
        // Test the analyser.
        testTheAnalyser( theAnalyser );
    }

    /**
     * Call the analyser's methods to test that it is returning
     * the correct values.
     * Print out the results of each test.
     * @param theAnalyser The analyser to be tested.
     */
    public static void testTheAnalyser( ArrayAnalyser theAnalyser )
    {
        String concatenation = theAnalyser.concatenate( );
        System.out.println( "Concatenation is " + concatenation );

        System.out.println("\nTesting getLongestLength method:");
        int longestLength = theAnalyser.getLongestLength();
        System.out.println("Longest String Length: " + longestLength);

        // Testing the getCommonStrings method
        System.out.println("\nTesting getCommonStrings method:");
        String[] commonStrings = theAnalyser.getCommonStrings();
        System.out.println("Common Strings: " + Arrays.toString(commonStrings));

        // Testing the getUniqueCount method
        System.out.println("\nTesting getUniqueCount method:");
        int uniqueCount = theAnalyser.getUniqueCount();
        System.out.println("Unique Count: " + uniqueCount);

        // Testing the product method
        System.out.println("\nTesting product method:");
        int productResult = theAnalyser.product();
        System.out.println("Product Result: " + productResult);

        // Testing the toString method
        System.out.println("\nTesting toString method:");
        String toStringResult = theAnalyser.toString();
        System.out.println("toString Result: " + toStringResult);


    }


    /**
     * Read the lines from the given file and return an
     * array of Strings.
     * @param theDataFile The file to be read.
     * @return The array of Strings from the file.
     */
    private static String[ ] readDataFromFile( String theDataFile ) throws IOException
    {
        Path filePath = Paths.get( theDataFile );

        List< String > theData = Files.readAllLines( filePath) ;

        return theData.toArray( new String[ 0 ] );
    }
}