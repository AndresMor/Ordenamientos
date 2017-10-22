/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.math.BigInteger;
import java.util.Arrays;
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
    static int Iter = 0;
    public static void main(String[] args) {
        BigInteger Array[] = new BigInteger[2000];
        LLenar(Array);
        System.out.println("---------------------------------------------------------------");
        System.out.println("| Tiempo Burbuja     | "+Burbuja(Array) + " ns  "  +" | Iteraciònes: "+Iter+ " |");Iter=0;
        System.out.println("| Tiempo Selecciòn   | "+Selección(Array) + " ns    "+" | Iteraciònes: "+Iter+ " |");Iter=0;
        System.out.println("| Tiempo Inserciòn   | "+Inserción(Array) + " ns  "+" | Iteraciònes: "+Iter+ "  |");Iter=0;
        System.out.println("| Tiempo Shell       | "+Shell(Array) + " ns    "    +" | Iteraciònes: "+Iter+ "   |");Iter=0;
        System.out.println("| Tiempo Radix       | "+Radix(Array)+" ns   "      +" | Iteraciònes: "+Iter+ "   |");Iter=0;
        System.out.println("| Tiempo HeapSort    | "+HeapSort(Array)+" ns    "   +" | Iteraciònes: "+Iter+ "   |");Iter=0;
        System.out.println("| Tiempo Merge       | "+Merge(Array,0,Array.length-1)+" ns    "+" | Iteraciònes: "+Iter+ "   |");Iter=0;Show(Array);
        System.out.println("| Tiempo QuickSort   | "+QuickSort(Array,0, Array.length - 1) + " ns    "+" | Iteraciònes: "+Iter+ "     |");Iter=0;Show(Array);
        System.out.println("---------------------------------------------------------------");
    }

    public static long Burbuja(BigInteger[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        for (int i = 0; i < Vec2.length; i++) {
            for (int j = 0; j < Vec2.length - 1; j++) {
                if (Vec2[j].compareTo(Vec2[j + 1]) == 1) {
                    BigInteger Temp = new BigInteger(Vec2[j].toString());
                    Vec2[j] = Vec2[j + 1];
                    Vec2[j + 1] = Temp;
                    Iter++;
                }
            }
            Iter++;
        }
        Time_end = System.nanoTime();
        Show(Vec2);
        return Time_end - Time_start;
    }

    public static long Selección(BigInteger[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        for (int i = 0; i < Vec2.length - 1; i++) {
            int Pos_Min = i;
            for (int j = i + 1; j < Vec2.length; j++) {
                if (Vec2[j].compareTo(Vec2[Pos_Min]) == -1) {
                    Pos_Min = j;
                }
                Iter++;
            }
            BigInteger Temp = new BigInteger(Vec2[i].toString());
            Vec2[i] = Vec2[Pos_Min];
            Vec2[Pos_Min] = Temp;
            Iter++;
        }
        Time_end = System.nanoTime();
        Show(Vec2);
        return Time_end - Time_start;
    }

    public static long Inserción(BigInteger[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        for (int i = 1; i < Vec2.length; i++) {
            int j = i;
            while (j > 0 && Vec2[j].compareTo(Vec2[j - 1]) == -1) {
                BigInteger tmp = new BigInteger(Vec2[j].toString());
                Vec2[j] = Vec2[j - 1];
                Vec2[j - 1] = tmp;
                j--;
                Iter++;
            }
            Iter++;
        }
        Time_end = System.nanoTime();
        Show(Vec2);
        return Time_end - Time_start;
    }

    public static long Shell(BigInteger[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        int Inc = Vec2.length / 2;
        while (Inc > 0) {
            for (int i = Inc; i < Vec2.length; i++) {
                int j = i - Inc;
                while (j >= 0) {
                    int k = j + Inc;
                    if (Vec2[j].compareTo(Vec2[k]) == 1) {
                        BigInteger Temp = new BigInteger(Vec2[j].toString());
                        Vec2[j] = Vec2[k];
                        Vec2[k] = Temp;
                    } else {
                        j = 0;
                    }
                    j = j - Inc;
                    Iter++;
                }
            }
            Inc = Inc / 2;
        }
        Time_end = System.nanoTime();
        Show(Vec2);
        return Time_end - Time_start;
    }

    public static long QuickSort(BigInteger[] Vec2,int Inf, int Sup) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        int i, j;
        BigInteger Pivote, temp;
        i = Inf;
        j = Sup;
        Pivote = new BigInteger(Vec2[(Inf + Sup)/2].toString());
        do {
            while ((Vec2[i].compareTo(Pivote)==-1) && (i < Sup)) {
                i++;
            }
            while ((Pivote.compareTo(Vec2[j])==-1) && (j > Inf)) {
                j--;
            }
            if (i <= j) {
                temp = new BigInteger(Vec2[i].toString());
                Vec2[i] = Vec2[j];
                Vec2[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (Inf < j) {
            QuickSort(Vec2,Inf, j);
        }
        if (i < Sup) {
            QuickSort(Vec2,i, Sup);
        }
        Iter++;
        Time_end = System.nanoTime();
        return  Time_end - Time_start;
    }

    public static long Radix(BigInteger[] Vec2) {
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        BigInteger m = getMax(Vec2, Vec2.length);
        BigInteger exp = BigInteger.ONE;
        for (; m.divide(exp).compareTo(BigInteger.ZERO)>0; exp = exp.multiply(BigInteger.TEN)) {
            countSort(Vec2, Vec2.length, exp);
            Iter++;
        }
        Time_end = System.nanoTime();
        Show(Vec2);
        return Time_end - Time_start;
    }

    static BigInteger getMax(BigInteger arr[], int n) {
        BigInteger mx = new BigInteger(arr[0].toString());
        for (int i = 1; i < n; i++) {
            if (arr[i].compareTo(mx)==1) {
                mx = new BigInteger(arr[i].toString());
            }
        }
        return mx;
    }

    static void countSort(BigInteger arr[], int n, BigInteger exp) {
        BigInteger output[] = new BigInteger[n]; // output array
        int i;
        BigInteger count[] = new BigInteger[10];
        for (int j = 0; j < 10; j++) {
            count[j] = BigInteger.ZERO;
        }
        for (i = 0; i < n; i++) {
            int S = Integer.parseInt((arr[i].divide(exp).mod(BigInteger.TEN)).toString());
            count[S] = count[S].add(BigInteger.ONE) ;
        }
        for (i = 1; i < 10; i++) {
            count[i] =count[i].add(count[i - 1]);
        }
        for (i = n - 1; i >= 0; i--) {
            int R = Integer.parseInt((arr[i].divide(exp)).mod(BigInteger.TEN).toString());
            int D = Integer.parseInt(count[R].subtract(BigInteger.ONE).toString());
            output[D] = new BigInteger(arr[i].toString());
            count[R]= count[R].subtract(BigInteger.ONE);
            Iter++;
        }
        for (i = 0; i < n; i++) {
            arr[i] = new BigInteger(output[i].toString());
        }
    }
    
    public static long HeapSort(BigInteger[] Vec){
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        
        for (int i = Vec.length/2-1; i >= 0; i--) {
            heapify(Vec,Vec.length,i);
        }
        for (int i=Vec.length-1; i>=0; i--){
            BigInteger temp = new BigInteger(Vec[0].toString());
            Vec[0] = Vec[i];
            Vec[i] = temp;
            heapify(Vec, i, 0);
            Iter++;
        }
        
        Time_end = System.nanoTime();
        Show(Vec);
        return Time_end - Time_start;
    }
    
    public static void heapify(BigInteger[] arr, int n, int i){
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
        if (l < n && arr[l].compareTo(arr[largest])==1)
            largest = l;
        if (r < n && arr[r].compareTo(arr[largest])==1)
            largest = r;
        if (largest != i){
            BigInteger swap = new BigInteger(arr[i].toString());
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
        Iter++;
    }
    
    public static long Merge(BigInteger[] Vec, int Inf, int Sup){
        long Time_start, Time_end;
        Time_start = System.nanoTime();
        if (Inf<Sup) {
            int Mid = (Inf+Sup)/2;
            Merge(Vec,Inf,Mid);
            Merge(Vec,Mid+1,Sup);
            MergeSort(Vec,Inf,Mid,Sup);
            Iter++;
        }
        Time_end = System.nanoTime();
        return Time_end - Time_start;
    }
    
    public static void MergeSort(BigInteger[] arr, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        BigInteger L[] = new BigInteger[n1];
        BigInteger R[] = new BigInteger [n2];
        for (int i=0; i<n1; i++){
            L[i] = new BigInteger(arr[l + i].toString());
        }
        for (int j=0; j<n2; j++){
            R[j] = new BigInteger(arr[m + 1+ j].toString());
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2){
            if (L[i].compareTo(R[j])<0){
                arr[k] = new BigInteger(L[i].toString());
                i++;
            }
            else{
                arr[k] = new BigInteger(R[j].toString());
                j++;
            }
            k++;
            Iter++;
        }
        while (i < n1){
            arr[k] = new BigInteger(L[i].toString());
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = new BigInteger(R[j].toString());
            j++;
            k++;
        }
    }

    public static void LLenar(BigInteger[] Array) {
        for (int i = 0; i < Vec.length; i++) {
            Vec[i] = random(new BigInteger("10000000000000"), new BigInteger("999999999999999"), new Random());
            Array[i] = new BigInteger(Vec[i].toString());
        }
    }

   

    public static void Show(BigInteger[] Vec2) {
        for (int i = 0; i < Vec2.length; i++) {
           // System.out.print(Vec2[i] + "|");
            Vec2[i] = new BigInteger(Vec[i].toString());
        }
    }

    public static BigInteger random(BigInteger Menor, BigInteger Mayor, Random rnd) {
        BigInteger r;
        do {
            r = new BigInteger(50, rnd);
        } while (r.compareTo(Mayor) > 0 || r.compareTo(Menor) < 0);
        return r;
    }

}
