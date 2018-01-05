package com.example.aliothman.mutimedia;

import java.math.BigInteger;

/**
 * Created by AliOthman on 12/14/2017.
 */

public class Rsa {

    public static int pRandom;
    public static int qRandom;
    public static int pE;
    public static int qE;
    public double p;
    public double pp;
    private int q;
    private int n;
    public int z;
    private double d;

    public Rsa() {
    }

    public static int getpRandom() {
        return pRandom;
    }

    public static void setpRandom(int pRandom) {
        Rsa.pRandom = pRandom;
    }

    public static int getqRandom() {
        return qRandom;
    }

    public static void setqRandom(int qRandom) {
        Rsa.qRandom = qRandom;
    }

    public double getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public double getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public double getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    private int e;
    private double c;



    public static int getpE() {
        return pE;
    }

    public static void setpE(int pE) {
        Rsa.pE = pE;
    }

    public static int getqE() {
        return qE;
    }

    public static void setqE(int qE) {
        Rsa.qE = qE;
    }

    public int calculateN() {
        this.n = this.pE * this.qE;
        return n;
    }

    public int calculatez() {
        this.z = (this.pE - 1) * (this.qE - 1);
        return this.z;
    }
    //calculate D


    public int calculateD(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x % this.z;
        return 1;
    }


    public int calculateC(int p) {
        this.c = (Math.pow(p, this.e)) % (this.n);
        return (int) (this.c);
    }

    public BigInteger calculateP(int c, int d) {
        if(d==getE()) d=d+getZ();
        String f = String.valueOf(c);
        BigInteger result = (new BigInteger(f)).pow(d);
        BigInteger b = BigInteger.valueOf(this.n);
        BigInteger rs = (BigInteger) result.mod(b);
        return rs;
    }

//    public int calculateE() {
//        return this.e;
//    }
//
//    //check if it is prime or not
//    private static boolean isPrime(int n) {
//        int i;
//        for (i = 2; i <= Math.sqrt(n); i++) {
//            if (n % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    // get p & q numbers
//    public static void pqNumbersGenerate() {
//        while (true) {
//            pRandom = (int) (Math.random() * (127 - 2) + 2);
//            if (isPrime(pRandom)) {
////                System.out.println("Got Random Prime Q :" + pRandom);
//
//                break;
//            }
//        }
//        while (true) {
//            qRandom = (int) (Math.random() * (127 - 2) + 2);
//            if (isPrime(qRandom)) {
////                System.out.println("Got Random Prime Q :" + qRandom);
//                break;
//            }
//        }
//
//    }
    //calculate n=p*q
}
