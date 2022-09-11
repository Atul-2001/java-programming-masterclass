package com.signature;

public class PaintJob {
    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        } else {
            double area = width * height;
            double requiredBucket = area / areaPerBucket;
            return (int) Math.ceil(requiredBucket - extraBuckets);
        }
    }

    public static int getBucketCount(double width, double height, double areaPerBucket) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0) {
            return -1;
        } else {
            double area = width * height;
            double reqBucket = area / areaPerBucket;
            System.out.println("area = "+area+" Bucket = "+reqBucket);
            return (int) Math.ceil(reqBucket);
        }
    }

    public static int getBucketCount(double area, double areaPerBucket) {
        if (area <= 0 || areaPerBucket <= 0) {
            return -1;
        } else {
            double reqBucket = area / areaPerBucket;
            return (int) Math.ceil(reqBucket);
        }
    }
}
