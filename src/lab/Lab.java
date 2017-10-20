/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author moralesea
 */
public class Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigInteger Vec[] = new BigInteger[2000];
        Vec = LLenar(Vec);
        System.out.println("Tiempo Burbuja: "+Burbuja(Vec)+" ns");
    }
    
    public static long Burbuja(BigInteger[] Vec){
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        for (int i = 0; i < Vec.length; i++) {
            for (int j = 0; j < Vec.length-1; j++) {
                if (Vec[j].compareTo(Vec[i])==1) {
                    BigInteger Temp = Vec[j];
                    Vec[j] = Vec[j+1];
                    Vec[j+1] = Temp;
                }
            }
        }
        Time_end = System.currentTimeMillis();
        return 1000000*Time_end - Time_start;
    }
    public static long Selección(BigInteger[] Vec){
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        for (int i = 0; i < Vec.length-1; i++) {
            int Pos_Min=i;
            for (int j = i+1; j < Vec.length; j++) {
                if (Vec[j].compareTo(Vec[Pos_Min])==-1) {
                    Pos_Min = j;
                }
            }
            BigInteger Temp = Vec[i];
            Vec[i] = Vec[Pos_Min];
            Vec[Pos_Min] = Temp;
        }
        Time_end = System.currentTimeMillis();
        return 1000000*Time_end - Time_start;
    }
    
    public static BigInteger[] LLenar(BigInteger[] Vec){
        for (int i = 0; i < Vec.length; i++) {
           Vec[i] = random(new BigInteger("10000000000000"),new BigInteger("999999999999999"),new Random());
        }
        return Vec;
    }
    
    public static BigInteger random(BigInteger Menor, BigInteger Mayor, Random rnd){
        BigInteger r;
        do{
            r = new BigInteger(50,rnd);
        }while(r.compareTo(Mayor)==1 || r.compareTo(Menor)==-1);
        return r;
    }
    
}
