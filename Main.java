import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * A class containing the main method for the taxi company program.
 * There are several test methods that will display the results of
 * different parts of the program.
 */
public class Main
{
    // A set of all the ids of the vehicles.
    private static final Set< String > ids = new HashSet< > ( );

    public static void addId( String id )
    {
        ids.add( id );
    }

    /**
     * The main method for the cab company program.
     * Three arguments are required:
     * 1. The name of the file of vehicle details.
     * 2. The name of the file of bookings.
     * 3. The name of the file for the report.
     * @param args The program arguments.
     * @throws IOException If there is an error reading the files.
     */
    public static void main( String[ ] args ) throws IOException
    {
        if( args.length != 3 )
        {
            System.out.println(
                    "There should be three program arguments: " +
                            "the name of the vehicles file, " +
                            "the name of the bookings file and " +
                            "the name of the output file for the report.");
            System.exit( 1 );
        }
        String vehiclesFilename = args[ 0 ];
        String bookingsFilename = args[ 1 ];
        String reportFilename = args[ 2 ];

        // Read the vehicle details.
        ArrayList< Vehicle > theVehicles = readVehicleDetails( vehiclesFilename );
        // Test the reading of the vehicle details.
        testPartOne( theVehicles );

        CabCompany cabCompany = new CabCompany( );
        for ( Vehicle vehicle : theVehicles )
        {
            cabCompany.addVehicle( vehicle );
        }
        // TODO: Call addVehicle on the cabCompany for each vehicle in the ArrayList of vehicles.

        // Now test the listing of the HashMap.
        testPartTwo(cabCompany);

        // Read the bookings.
        cabCompany.readBookings( bookingsFilename );

        // Test the listing of the bookings.
        testPartThree( cabCompany );

        // Test the calculation of the takings.
        testPartFour( cabCompany );

        // Write the report.
        cabCompany.writeReport( reportFilename );
        testPartFive( reportFilename );

        cabCompany = new CabCompany( );
        // Assume this is populated

        // TODO: Call addVehicle on the cabCompany for each vehicle in the ArrayList of vehicles.
    }

    /**
     * Decode the vehicle details and create a Vehicle object.
     * TODO: Complete this method by creating the correct type of Vehicle object.
     * @param vehicleDetails The details of the vehicle.
     * @return The Vehicle object.
     */
    private static Vehicle decodeVehicleDetails( String vehicleDetails )
    {
        String[ ] parts = vehicleDetails.split( "," );
        if ( parts[ 0 ].equals( "C" ) )
        {
            return new Cab( parts[ 1 ], parts[ 2 ], Integer.parseInt( parts[ 3 ] ) );
        }
        else if ( parts[ 0 ].equals( "B" ) )
        {
            List< String > route = Arrays.asList( parts[ 3 ].split( ":" ) );
            return new Bus( parts[ 1 ], Integer.parseInt( parts[ 2 ] ), route );
        }
        return null;
    }
    /**
     * This method just needs to make sure that details of each vehicle
     * are printed as it is created in the readVehicleDetails method.
     */
    private static void testPartOne( ArrayList< Vehicle > theVehicles )
    {
        System.out.println( );
        System.out.println( "Test part 1: Results of printing the vehicle details as they are read in." );
        for( Vehicle aVehicle : theVehicles )
        {
            System.out.println( aVehicle );
        }
        System.out.println( "=========================================================================" );
    }

    /**
     * This method must list all the vehicles that are stored
     * in the cab company's HashMap.
     */
    private static void testPartTwo( CabCompany cabCompany )
    {
        System.out.println( );
        System.out.println( "Test part 2: Results of printing the HashMap of vehicle details." );

        cabCompany.listAllVehicles( );

        System.out.println( "================================================================" );
    }

    /**
     * This method must list all the bookings for all the vehicles.
     */
    private static void testPartThree( CabCompany cabCompany )
    {
        System.out.println( );
        System.out.println( "Test part 3: Results of printing the list of bookings." );

        cabCompany.listAllBookings( );

        System.out.println( "======================================================" );
    }

    /**
     * This method must print the takings for each vehicle.
     */
    private static void testPartFour( CabCompany cabCompany )
    {
        System.out.println( );
        System.out.println( "Test part 4: Results of calculating takings." );


        for( String id : ids )
        {
            int takings = cabCompany.getVehicleTakings( id );
            System.out.println( id + " £" + takings );
        }

        System.out.println( "============================================" );
    }

    /**
     * This method must print the contents of the report.
     */
    private static void testPartFive( String reportFilename ) throws IOException
    {
        System.out.println( );
        System.out.println( "Test part 5: Results of writing the report." );

        Path filePath = Paths.get( reportFilename );
        List< String > lines = Files.readAllLines( filePath );
        for( String line : lines )
        {
            System.out.println( line );
        }

        System.out.println( "===========================================" );
    }


    /**
     * Read the file of vehicle details.
     * @param vehiclesFilename The name of the file of vehicle details.
     * @throws IOException  If there is an error reading the file.
     */
    private static ArrayList< Vehicle > readVehicleDetails( String vehiclesFilename ) throws IOException
    {
        Path filePath = Paths.get( vehiclesFilename );
        List< String > lines = Files.readAllLines( filePath );
        ArrayList< Vehicle > theVehicles = new ArrayList< >( );
        for( String vehicleDetails : lines )
        {
            vehicleDetails = vehicleDetails.trim( );
            Vehicle aVehicle = decodeVehicleDetails( vehicleDetails );
            if( aVehicle != null )
            {
                theVehicles.add( aVehicle );
            }
        }
        return theVehicles;
    }


}
