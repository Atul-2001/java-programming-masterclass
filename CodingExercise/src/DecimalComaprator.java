public class DecimalComaprator {

    public static boolean areEqualByThreeDecimalPlaces(double num1, double num2)
    {
        int newNum1 = (int) (num1 * 1000);
        int newNum2 = (int) (num2 * 1000);

        if (newNum1 == newNum2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
