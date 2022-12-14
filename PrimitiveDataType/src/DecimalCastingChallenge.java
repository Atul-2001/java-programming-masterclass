public class DecimalCastingChallenge {

    public static void main(String[] args) {

        int myIntValue = 100;
        float myFloatValue = 3.75f;
        double myDoubleValue = 3.033745;

        double result = 7345.8901823 + 10 * (myIntValue + myFloatValue + myDoubleValue);
        System.out.println("Result = " + result);

        float myValue = (float) result;
        System.out.println("double > float = " + result);

        double myVal = myFloatValue;
        System.out.println("float > double = " + myVal);

        double myVal2 = myIntValue;
        System.out.println("int > double = " + myVal2);

        float myValue2 = myIntValue;
        System.out.println("int > float = " + myValue2);

        int value = (int) myFloatValue;
        System.out.println("float > int = " + value);

        int value2 = (int) result;
        System.out.println("double > int = " + value2);

    }
}
