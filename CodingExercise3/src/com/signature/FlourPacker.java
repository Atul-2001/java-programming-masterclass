package com.signature;

public class FlourPacker {

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (goal < 0 || smallCount < 0 || bigCount < 0) {
            return false;
        } else {
            int bigSize = 5 * bigCount;
            if (bigSize > goal) {
                if (bigSize%goal == 0) {
                    return true;
                } else if (smallCount == 0){
                    return false;
                } else {
                    while (bigSize!=0) {
                        bigSize-=5;
                        if(bigSize+smallCount >= goal) {
                            return true;
                        }
                    }
                    if (bigSize + smallCount >= goal) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            else if (bigSize + smallCount >= goal) {
                return true;
            } else {
                return false;
            }
        }
    }
}
