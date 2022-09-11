package com.signature;

public class SecondAndMinuteChallenge {

    public static final String INVALID_VALUE_MESSAGE = "Invalid value";

    public static void main(String[] args) {

        System.out.println(getDurationString(65, 45));
        System.out.println(getDurationString(3945L));
        System.out.println(getDurationString(-41));
        System.out.println(getDurationString(65,9));

    }

    public static String getDurationString(long minute, long second)
    {
        if(minute < 0 || (second < 0 || second > 59))
        {
            return INVALID_VALUE_MESSAGE;
        }
        else
        {
            long hours = minute / 60;
            long min = minute % 60;

            String hourString = "0", minuteString = "0", secondString = "0";

            hourString = hours + "h";
            if(hours < 10)
            {
                hourString = "0" + hourString;
            }

            minuteString = min + "m";
            if (min < 10)
            {
                minuteString = "0" + minuteString;
            }

            secondString = second + "s";
            if (second < 10)
            {
                secondString = "0" + secondString;
            }

            return (hourString + " " + minuteString + " " + secondString);
        }
    }

    public static String getDurationString(long second)
    {
        if (second < 0)
        {
            return INVALID_VALUE_MESSAGE;
        }
        else
        {
            long hour = second / 3600;
            long minute = (second % 3600) / 60;
            long sec = (second % 3600) % 60;

            String hourString, minuteString, secondString;

            hourString = hour + "h";
            if (hour < 10)
            {
                hourString = "0" + hourString;
            }

            minuteString = minute + "m";
            if (minute < 10)
            {
                minuteString = "0" + minuteString;
            }

            secondString = sec + "s";
            if (sec < 10)
            {
                secondString = "0" + secondString;
            }

            return (hourString + " " + minuteString + " " + secondString);
        }
    }
}
