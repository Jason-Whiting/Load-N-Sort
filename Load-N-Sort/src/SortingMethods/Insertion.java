package SortingMethods;

import UserInterface.*;

public class Insertion
{
    public static void insertionSort(final int[] ints) {
        for (int i = 1; i < ints.length; ++i) {
            int temp;
            int j;
            for (temp = ints[i], j = i - 1; j >= 0 && temp < ints[j]; --j) {
                ints[j + 1] = ints[j];
            }
            ints[j + 1] = temp;
        }
        display(ints);
    }
    
    public static void insertionSort(final double[] doubles) {
        for (int i = 1; i < doubles.length; ++i) {
            double temp;
            int j;
            for (temp = doubles[i], j = i - 1; j >= 0 && temp < doubles[j]; --j) {
                doubles[j + 1] = doubles[j];
            }
            doubles[j + 1] = temp;
        }
        display(doubles);
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
