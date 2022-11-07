package com.dht.util;

import java.util.Random;

public class RandomUtils {

    private static Random random = null;

    static {
        random = new Random();
    }

    public static String authCode(int length){
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            switch (random.nextInt(3)){
                case 0:
                    strb.append(random.nextInt(10));
                    break;
                case 1:
                    strb.append((char)(random.nextInt(26)+65));
                    break;
                case 2:
                    strb.append((char)(random.nextInt(26)+97));
                    break;
            }
        }
        return strb.toString();
    }

    public static int getNum(int bound){
        return random.nextInt(bound);
    }
}
