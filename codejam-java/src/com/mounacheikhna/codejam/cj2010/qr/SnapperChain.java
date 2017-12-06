package com.mounacheikhna.codejam.cj2010.qr;

import java.io.IOException;

public class SnapperChain {

    public String solve(int n, int k) {
        int mask = (1 << n) - 1; // = a =  nb where n bits are set in a binary form , n = 4 .... 1 1 1 1 <=> 2^n - 1
        return (k & mask) == mask ? "ON" : "OFF";
    }

    public static void main(String[] args) throws IOException {
        SnapperChain sc = new SnapperChain();
        System.out.println(sc.solve(4, 47)); // ON
        System.out.println(sc.solve(1, 0));  // OFF
        System.out.println(sc.solve(1, 1));  // ON
        System.out.println(sc.solve(4, 0));  // OFF
    }
}
