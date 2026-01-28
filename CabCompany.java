import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabCompany {
    private Map< String, Vehicle > vehicles;

    public CabCompany( )
    {
        vehicles = new HashMap< > ( );
    }

    public void addVehicle( Vehicle vehicle )
    {
        vehicles.put( vehicle.getId( ), vehicle );
        Main.addId( vehicle.getId( ) );
    }

    public void listAllVehicles( )
    {
        for ( Vehicle vehicle : vehicles.values( ) )
        {
            System.out.println( vehicle );
        }
    }

    public void listAllBookings( )
    {
        for ( Vehicle vehicle : vehicles.values( ) )
        {
            for ( Booking booking : vehicle.getBookings( ) )
            {
                System.out.println( booking );
            }
        }
    }

    public int getVehicleTakings( String id )
    {
        Vehicle vehicle = vehicles.get( id );
        if ( vehicle != null )
        {
            return vehicle.getTakings( );
        }
        return 0;
    }

    public void readBookings( String bookingsFilename ) throws IOException
    {
        Path filePath = Paths.get( bookingsFilename );
        List< String > lines = Files.readAllLines( filePath );

        for ( String line : lines )
        {
            String[ ] parts = line.split("," );
            if ( parts.length == 3 )
            {
                String vehicleId = parts[ 0 ].trim( );
                String pickupLocation = parts[ 1 ].trim( );
                String destination = parts[ 2 ].trim( );

                Booking booking = new Booking( vehicleId, pickupLocation, destination );
                Vehicle vehicle = vehicles.get( vehicleId );
                if ( vehicle != null )
                {
                    vehicle.addBooking( booking );
                }
            }
        }
    }

    public void writeReport( String reportFilename ) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter( reportFilename ) ) )
        {
            writer.write( "Cab Company Report\n\n");

            int totalTakings = 0;

            for ( Vehicle vehicle : vehicles.values( ) )
            {
                int takings = vehicle.getTakings( );
                totalTakings += takings;

                if ( vehicle instanceof Cab )
                {
                    Cab cab = ( Cab ) vehicle;
                    writer.write( String.format( "Cab %s driven by %s had %d journeys and made £%d\n",
                            cab.getId( ), cab.driverName, cab.getBookings( ).size( ), takings ) );
                }

                else if ( vehicle instanceof Bus )
                {
                    Bus bus = ( Bus ) vehicle;
                    writer.write( String.format( "Bus %s had %d journeys and made £%d\n",
                            bus.getId( ), bus.getBookings( ).size( ), takings ) );
                }
            }

            writer.write( String.format( "\nTotal takings for the day £%d\n", totalTakings ) );

        }
    }
}