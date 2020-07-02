package com.codepath.apps.restclienttemplate.models;


public class TwitterNumbers {
    public String format(int i){
        double d = (double)i;
        return coolFormat(d,0);
    }

    /*https://stackoverflow.com/questions/4753251/how-to-go-about-formatting-1200-to-1-2k-in-java
      Elijah Saounkine*/
    public static String coolFormat(double n, int iteration) {
        char[] c = new char[]{'K', 'M', 'B', 'T'};
        double d = ((long) n / 100) / 10.0;
        boolean isRound = (d * 10) %10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
        return (d < 1000? //this determines the class, i.e. 'k', 'm' etc
                ((d > 99.9 || isRound || (!isRound && d > 9.99)? //this decides whether to trim the decimals
                        (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
                ) + "" + c[iteration])
                : coolFormat(d, iteration+1));
    }
}
