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
    static BigInteger Vec[] = new BigInteger[2000];
    static int Vec2[] = new int[10];

    public static void main(String[] args) {
        LLenar2();
        // System.out.println("Tiempo Burbuja: "+Burbuja(Vec2)+" ns");
        // System.out.println("Tiempo Selecciòn: "+Selección(Vec2)+" ns");
     //   System.out.println("Tiempo Inserciòn: " + Inserción(Vec2) + " ns");
       System.out.println("Tiempo Shell: "+Shell(Vec2)+" ns");

    }

    public static long Burbuja(int[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        for (int i = 0; i < Vec2.length; i++) {
            for (int j = 0; j < Vec2.length - 1; j++) {
                if (Vec2[j] > Vec2[j + 1]) {
                    int Temp = Vec2[j];
                    Vec2[j] = Vec2[j + 1];
                    Vec2[j + 1] = Temp;
                }
            }
        }
        Time_end = System.currentTimeMillis();
        Show(Vec2);
        return 1000000 * Time_end - Time_start;
    }

    public static long Selección(int[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        for (int i = 0; i < Vec2.length - 1; i++) {
            int Pos_Min = i;
            for (int j = i + 1; j < Vec2.length; j++) {
                if (Vec2[j] < Vec2[Pos_Min]) {
                    Pos_Min = j;
                }
            }
            int Temp = Vec2[i];
            Vec2[i] = Vec2[Pos_Min];
            Vec2[Pos_Min] = Temp;
        }
        Time_end = System.currentTimeMillis();
        Show(Vec2);
        return 1000000 * Time_end - Time_start;
    }

    public static long Inserción(int[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        for (int i = 1; i < Vec2.length; i++) {
            int j = i;
            while (j > 0 && Vec2[j] < Vec2[j - 1]) {
                int tmp = Vec2[j];
                Vec2[j] = Vec2[j - 1];
                Vec2[j - 1] = tmp;
                j--;
            }
        }
        Time_end = System.currentTimeMillis();
        Show(Vec2);
        return 1000000 * Time_end - Time_start;
    }

    public static long Shell(int[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.currentTimeMillis();
        int Inc = Vec2.length/2;
        while(Inc>0){
            for (int i = Inc+1; i < Vec2.length; i++) {
                int j = i-Inc;
                while(j>=0){
                    int k = j+Inc;
                    if (Vec2[j]>Vec2[k]) {
                        int Temp = Vec2[j];
                        Vec2[j] = Vec2[k];
                        Vec2[k] = Temp;
                    }else{
                        j = 0;
                    }
                    j = j-Inc;
                }
            }
            Inc = Inc/2;
        }
        Time_end = System.currentTimeMillis();
        Show(Vec2);
        return 1000000 * Time_end - Time_start;
    }

    public static void LLenar() {
        for (int i = 0; i < Vec.length; i++) {
            Vec[i] = random(new BigInteger("10000000000000"), new BigInteger("999999999999999"), new Random());
            System.out.print(Vec[i] + "|");
        }
    }

    public static void LLenar2() {
        for (int i = 0; i < Vec2.length; i++) {
            Vec2[i] = (int) (Math.random() * 99 + 1);
        }
    }

    public static void Show(int[] Vec2) {
        for (int i = 0; i < Vec2.length; i++) {
            System.out.print(Vec2[i] + "|");
        }
    }

    public static BigInteger random(BigInteger Menor, BigInteger Mayor, Random rnd) {
        BigInteger r;
        do {
            r = new BigInteger(50, rnd);
        } while (r.compareTo(Mayor) == 1 || r.compareTo(Menor) == -1);
        return r;
    }

}
