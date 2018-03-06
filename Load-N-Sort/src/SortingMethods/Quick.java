package SortingMethods;

import UserInterface.*;

public class Quick
{
    public static void sort(final int[] ints) {
        quickSort(ints, 0, ints.length - 1);
    }
    
    public static void quickSort(final int[] ints, final int low, final int high) {
        int i = low;
        int j = high;
        final int pivot = ints[(low + high) / 2];
        while (i <= j) {
            while (ints[i] < pivot) {
                ++i;
            }
            while (ints[j] > pivot) {
                --j;
            }
            if (i <= j) {
                final int temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
                ++i;
                --j;
            }
        }
        if (low < j) {
            quickSort(ints, low, j);
        }
        if (i < high) {
            quickSort(ints, i, high);
        }
        display(ints);
    }
    
    public static void display(final int[] ints) {
        PrimaryWindow.sorted.clear();
        for (int i = 0; i < ints.length; ++i) {
            PrimaryWindow.sorted.appendText("[" + i + "] = " + ints[i] + "\n");
        }
    }
    
    public static void sort(final double[] doubles) {
        quickSort(doubles, 0, doubles.length - 1);
    }
    
    public static void quickSort(final double[] doubles, final int low, final int high) {
        int i = low;
        int j = high;
        final double pivot = doubles[(low + high) / 2];
        while (i <= j) {
            while (doubles[i] < pivot) {
                ++i;
            }
            while (doubles[j] > pivot) {
                --j;
            }
            if (i <= j) {
                final double temp = doubles[i];
                doubles[i] = doubles[j];
                doubles[j] = temp;
                ++i;
                --j;
            }
        }
        if (low < j) {
            quickSort(doubles, low, j);
        }
        if (i < high) {
            quickSort(doubles, i, high);
        }
        display(doubles);
    }
    
    public static void display(final double[] doubles) {
        PrimaryWindow.sorted.clear();
        for (int i = 0; i < doubles.length; ++i) {
            PrimaryWindow.sorted.appendText("[" + i + "] = " + doubles[i] + "\n");
        }
    }
}
