public class ByteShortIntLong {

    public static void main(String[] args) {

        int myMaxIntValue = Integer.MAX_VALUE;
        int myMinIntValue = Integer.MIN_VALUE;

        System.out.println("Integer Minimum Value = " + myMinIntValue);
        System.out.println("Integer Maximum Value = " + myMaxIntValue);

        System.out.println("Busted Minimum Value = " + (myMinIntValue - 1));
        System.out.println("Busted Maximum Value = " + (myMaxIntValue + 1));

        int value = 2_147_483_647; //new method of assigning valid from java 7
        System.out.println("Value = " + value);

        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;

        System.out.println("\nByte Minimum Value = " + myMinByteValue);
        System.out.println("Byte Maximum Value = " + myMaxByteValue);

        System.out.println("Busted Minimum Value = " + (myMinByteValue - 1));
        System.out.println("Busted Maximum Value = " + (myMaxByteValue + 1));

        short myShortMinValue = Short.MIN_VALUE;
        short myShortMaxValue = Short.MAX_VALUE;

        System.out.println("\nShort Minimum Value = " + myShortMinValue);
        System.out.println("Short Maximum Value = " + myShortMaxValue);

        System.out.println("Busted Minimum Value = " + (myShortMinValue - 1));
        System.out.println("Busted Maximum Value = " + (myShortMaxValue + 1));

        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;

        System.out.println("\nLong Minimum Value = " + myMinLongValue);
        System.out.println("Long Maximum Value = " + myMaxLongValue);

        System.out.println("Busted Minimum Value = " + (myMinLongValue - 1));
        System.out.println("Busted Maximum Value = " + (myMaxLongValue + 1));


        int myNewIntValue = (myMinIntValue / 2);
        byte myNewByteValue = (byte) (myMinByteValue / 2);
        short myNewShortValue = (short) (myShortMinValue / 2);

        System.out.println("\nInteger = " + myNewIntValue);
        System.out.println("Byte = " + myNewByteValue);
        System.out.println("Short = " + myNewShortValue);
    }
}
