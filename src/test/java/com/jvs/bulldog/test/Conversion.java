package com.jvs.bulldog.test;

import io.silverspoon.bulldog.core.util.easing.StringUtil;

/**
 * Created by jorgevs on 12/2/15.
 */
public class Conversion {

    public int dectobcd(int val) {
        return ((val / 10 * 16) + (val % 10));
    }

    public int bcdtodec(int val) {
        return ((val / 16 * 10) + (val % 16));
    }
    //----------------------------------------------------------------------
    public int dec2bcd(int n) {
        return n + 6 * (n / 10);
    }

    public int bcd2dec(int n) {
        return n - 6 * (n >> 4);
    }
    //----------------------------------------------------------------------
    public static void main(String[] args){
        Conversion conversion = new Conversion();
        int valor = 14;
        int valor1 = conversion.dec2bcd(valor);
        int valor2 = conversion.dectobcd(valor);
        System.out.println(valor1);
        System.out.println(valor2);
        System.out.println(conversion.bcd2dec(valor1));
        System.out.println(conversion.bcdtodec(valor2));
    }
}
