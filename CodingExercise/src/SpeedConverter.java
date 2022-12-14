public class SpeedConverter {

    public static long toMilesPerHour(double kilometersPerHour)
    {
        if(kilometersPerHour < 0.0)
        {
            return  -1;
        }

        return Math.round(kilometersPerHour / 1.609);
    }

    public static void printConversion(double kilometersPerHours)
    {
        if(kilometersPerHours < 0.0)
        {
            System.out.println("Invalid Value");
        }
        else
        {
            long miles = toMilesPerHour(kilometersPerHours);

            String result = kilometersPerHours + " km/h = " + miles + " mi/h";

            System.out.println(result);
        }
    }
}
