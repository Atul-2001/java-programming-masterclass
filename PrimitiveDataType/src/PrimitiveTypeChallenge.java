public class PrimitiveTypeChallenge {

    public static void main(String[] args) {

        byte byteValue = 100;
        short shortValue = 200;
        int value = 300;

        long set1 = (long) 10 * (byteValue + shortValue + value);
        System.out.println("Set = " + set1);
        long result = 5000L + (10L * (byteValue + shortValue + value));

        System.out.println("Result = " + result);

        short totalShort = (short) (1000 + 10 * (byteValue + shortValue + value));
        System.out.println("Result = " + totalShort);
    }
}
