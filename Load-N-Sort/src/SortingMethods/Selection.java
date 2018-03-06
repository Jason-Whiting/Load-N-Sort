package SortingMethods;

import UserInterface.*;

public class Selection
{
    public static void selectionSort(final int[] ints) {
        for (int i = 0; i < ints.length - 1; ++i) {
            for (int j = i + 1; j < ints.length; ++j) {
                if (ints[i] > ints[j]) {
                    final int temp = ints[i];
                    ints[i] = ints[j];
                    ints[j] = temp;
                }
            }
        }
        display(ints);
    }
    
    public static void selectionSort(final double[] doubles) {
        for (int i = 0; i < doubles.length - 1; ++i) {
            for (int j = i + 1; j < doubles.length; ++j) {
                if (doubles[i] > doubles[j]) {
                    final double temp = doubles[i];
                    doubles[i] = doubles[j];
                    doubles[j] = temp;
                }
            }
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
