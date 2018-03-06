package SortingMethods;

import UserInterface.*;

public class Merge
{
    private static int[] intArray;
    private static double[] doubleArray;
    private static int[] tempInt;
    private static double[] tempDouble;
    private static int length;
    
    public static void sort(final int[] ints) {
        Merge.intArray = ints;
        Merge.length = ints.length;
        Merge.tempInt = new int[Merge.length];
        mergeInt(0, Merge.length - 1);
    }
    
    public static void sort(final double[] doubles) {
        Merge.doubleArray = doubles;
        Merge.length = doubles.length;
        Merge.tempDouble = new double[Merge.length];
        mergeDouble(0, Merge.length - 1);
    }
    
    public static void mergeInt(final int low, final int high) {
        if (low < high) {
            final int mid = low + (high - low) / 2;
            mergeInt(low, mid);
            mergeInt(mid + 1, high);
            mergePartsInt(low, mid, high);
        }
    }
    
    public static void mergeDouble(final int low, final int high) {
        if (low < high) {
            final int mid = low + (high - low) / 2;
            mergeDouble(low, mid);
            mergeDouble(mid + 1, high);
            mergePartsDouble(low, mid, high);
        }
    }
    
    public static void mergePartsInt(final int low, final int mid, final int high) {
        for (int i = low; i <= high; ++i) {
            Merge.tempInt[i] = Merge.intArray[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (Merge.tempInt[i] <= Merge.tempInt[j]) {
                Merge.intArray[k] = Merge.tempInt[i];
                ++i;
            }
            else {
                Merge.intArray[k] = Merge.tempInt[j];
                ++j;
            }
            ++k;
        }
        while (i <= mid) {
            Merge.intArray[k] = Merge.tempInt[i];
            ++k;
            ++i;
        }
        display(Merge.intArray);
    }
    
    public static void mergePartsDouble(final int low, final int mid, final int high) {
        for (int i = low; i <= high; ++i) {
            Merge.tempDouble[i] = Merge.doubleArray[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (Merge.tempDouble[i] <= Merge.tempDouble[j]) {
                Merge.doubleArray[k] = Merge.tempDouble[i];
                ++i;
            }
            else {
                Merge.doubleArray[k] = Merge.tempDouble[j];
                ++j;
            }
            ++k;
        }
        while (i <= mid) {
            Merge.doubleArray[k] = Merge.tempDouble[i];
            ++k;
            ++i;
        }
        display(Merge.doubleArray);
    }
    
    public static void display(final int[] ints) {
        PrimaryWindow.sorted.clear();
        for (int i = 0; i < ints.length; ++i) {
            PrimaryWindow.sorted.appendText("[" + i + "] = " + ints[i] + "\n");
        }
    }
    
    public static void display(final double[] doubles) {
        PrimaryWindow.sorted.clear();
        for (int i = 0; i < doubles.length; ++i) {
            PrimaryWindow.sorted.appendText("[" + i + "] = " + doubles[i] + "\n");
        }
    }
}
