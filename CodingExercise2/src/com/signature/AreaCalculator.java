package com.signature;

public class AreaCalculator {

    public static double area(double radius)
    {
        if (radius < 0.0)
        {
            return -1.0;
        }
        else
        {
            return (Math.PI * Math.pow(radius, 2));
        }
    }

    public static double area(double x, double y)
    {
        if (x < 0.0 || y < 0.0)
        {
            return -1.0;
        }
        else
        {
            return (x * y);
        }
    }
}
