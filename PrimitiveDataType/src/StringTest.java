public class StringTest {

    public static void main(String[] args) {
        String myVal = "This is a String";
        System.out.println("My Value : " + myVal);

        myVal+=", and this more";
        System.out.println("New Value : " + myVal);

        String mathVal = "150.55";
        mathVal+= "49.95";
        System.out.println("Math in String : "+ mathVal);

        String mVal = "10";
        int n = 20;
        mVal+=n;
        System.out.println("Addition of int and String : "+mVal);

        String numStr = "10";
        int i = 30;
        numStr = i + numStr;
        System.out.println(numStr);
    }
}
