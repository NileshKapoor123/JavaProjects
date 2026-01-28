public class Booking
{
    private String vehicleId;
    private String pickupLocation;
    private String destination;

    public Booking( String vehicleId, String pickupLocation, String destination )
    {
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
    }

    public String getVehicleId( )
    {
        return vehicleId;
    }

    public String getPickupLocation( )
    {
        return pickupLocation;
    }

    public String getDestination( )
    {
        return destination;
    }

    @Override
    public String toString( )
    {
        return vehicleId + ", " + pickupLocation + ", " + destination;
    }
}
