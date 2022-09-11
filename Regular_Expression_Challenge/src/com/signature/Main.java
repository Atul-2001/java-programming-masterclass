package com.signature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    String ch1 = "I Want a bike.";
        System.out.println(ch1.matches("I Want a bike."));

        String regEx = "I Want a \\w+."; // another way :- "I Want a (bike|ball)." -> using group
        System.out.println(ch1.matches(regEx));

        String ch2 = "I Want a ball.";
        System.out.println(ch2.matches(regEx));

        Pattern ch3 = Pattern.compile(regEx);
        Matcher matcher = ch3.matcher(ch1);
        System.out.println(matcher.matches());
        matcher = ch3.matcher(ch2);
        System.out.println(matcher.matches());

        String ch4 = "Replace all blanks with underscores.";
        System.out.println(ch4.replaceAll("\\s", "_")); // replaceAll(" ", "_");

        String ch5 ="aaabccccccccdddefffg";
        System.out.println(ch5.matches("[abcdefg]+")); // or [a-g]+

        //ch6
        System.out.println(ch5.matches("^a{3}bc{8}d{3}ef{3}g$"));
        System.out.println(ch5.replaceAll("^a{3}bc{8}d{3}ef{3}g$", "REPLACED"));

        String ch7 = "Jk.01"/*"abcd.123"*/;
        System.out.println(ch7.matches("^(?i)[a-z]+[\\.]{1}[0-9]+$")); // tim :- "^[A-Z][a-z]+\\.\\d+$"

        String ch8 = "abcd.135uvqz.7tzik.999";
        Pattern pattern8 = Pattern.compile("[A-Za-z]+\\.(\\d+)");
        Matcher matcher8 = pattern8.matcher(ch8);
        while (matcher8.find()) {
            System.out.println("Occurrence : " + matcher8.group(1));
        }

        String ch9 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern9 = Pattern.compile("[A-za-z]+\\.(\\d+)\\s");
        Matcher matcher9 = pattern9.matcher(ch9);
        while (matcher9.find()) {
            System.out.println("Occurrence : " + matcher9.group(1));
        }

        String ch10 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern10 = Pattern.compile("[A-za-z]+\\.(\\d+)\\s");
        Matcher matcher10 = pattern10.matcher(ch10);
        while (matcher10.find()) {
            System.out.println("Occurrence : " + matcher10.start(1) + " to " + (matcher10.end(1)-1));
        }

        String ch11 = "{0, 2}, {0, 5}, {1, 3}, {2, 4}";
        Pattern pattern11 = Pattern.compile("\\{(.+?)\\}"); //
        Matcher matcher11 = pattern11.matcher(ch11);
        while (matcher11.find()) {
            System.out.println("Coordinates : " + matcher11.group(1));
        }

        System.out.println("**************************************************");

        String ch11a = "{0, 2}, {0, 5}, {1, 3}, {2, 4}, {x, y}, {6, 10}, {20, 12}";
        Pattern pattern11a = Pattern.compile("\\{(\\d+, \\d+)\\}"); //
        Matcher matcher11a = pattern11a.matcher(ch11a);
        while (matcher11a.find()) {
            System.out.println("Coordinates : " + matcher11a.group(1));
        }

        String ch12 = "11111";
        Pattern pattern12 = Pattern.compile("^\\d{5}$");
        Matcher matcher12 = pattern12.matcher(ch12);
        System.out.println(matcher12.matches());

        String ch13 = "11111-1111";
        System.out.println(ch13.matches("^\\d{5}\\-\\d{4}$"));

        // ch14
        System.out.println(ch12.matches("^\\d{5}(-\\d{4})?$"));
        System.out.println(ch13.matches("^\\d{5}(-\\d{4})?$"));
    }
}
