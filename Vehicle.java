import java.util.*;
import java.util.ArrayList;
import java.util.List;

// Superclass Vehicle
public abstract class Vehicle
{
    protected String id;
    private List < Booking > bookings;

    public Vehicle( String id )
    {
        this.id = id;
        this.bookings = new ArrayList < > ( );
    }

    public String getId( )
    {
        return id;
    }

    public void addBooking( Booking booking )
    {
        bookings.add( booking );
    }

    public List < Booking > getBookings( )
    {
        return bookings;
    }

    public abstract int getTakings( );

}

// Subclass Cab
class Cab extends Vehicle
{
    protected String driverName;
    private int pricePerJourney;

    public Cab( String id, String driverName, int pricePerJourney )
    {
        super( id );
        this.driverName = driverName;
        this.pricePerJourney = pricePerJourney;
    }

    public int getTakings( )
    {
        return getBookings( ).size( ) * pricePerJourney;
    }

    @Override
    public String toString( )
    {
        return "Cab " + id + " driven by " + driverName;
    }
}

// Subclass Bus
class Bus extends Vehicle
{
    private int pricePerStop;
    private List< String > route;

    public Bus( String id, int pricePerStop, List< String > route )
    {
        super( id );
        this.pricePerStop = pricePerStop;
        this.route = route;
    }

    public int getTakings( )
    {
        int total = 0;

        for ( Booking booking : getBookings( ) )
        {
            String pickup = booking.getPickupLocation( ).trim( ).toLowerCase( );
            String destination = booking.getDestination( ).trim( ).toLowerCase( );
            List< String > Route = new ArrayList< > ( );
            for ( String stop : route )
            {
                Route.add( stop.trim( ).toLowerCase( ) );
            }
            int pickupIndex = Route.indexOf( pickup );
            int destinationIndex = Route.indexOf( destination );


            if ( pickupIndex != -1 && destinationIndex != -1 )
            {
                int numberOfStops;

                if ( pickupIndex <= destinationIndex )
                {
                    numberOfStops = destinationIndex - pickupIndex;
                }
                else
                {
                    // Handle circular route
                    numberOfStops = ( Route.size( ) - pickupIndex ) + destinationIndex;
                }

                total += ( numberOfStops * pricePerStop );
            }
            else
            {
                System.out.println( "Invalid booking: " + booking );
            }
        }

        return total;
    }

    @Override
    public String toString( )
    {
        return "Bus " + id + " has the route [" + route + "]";
    }
}