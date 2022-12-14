public class MegaBytesConverter {

    public static void printMegaBytesAndKiloBytes(int kiloBytes)
    {
        if (kiloBytes < 0)
        {
            System.out.println("Invalid Value");
        }
        else
        {
            int megaBytes = kiloBytes / 1024;
            int kB = kiloBytes % 1024;

            String result = kiloBytes + " KB = " + megaBytes + " MB and " + kB + " KB";
            System.out.println(result);
        }
    }
}
