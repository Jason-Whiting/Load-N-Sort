package OtherMethods;

import javafx.scene.control.*;

public class PermutationLex
{
    public static int permNum;
    
    public static void permutationLex(final String string, final int num, final TextArea sorted) {
        sorted.clear();
        PermutationLex.permNum = 0;
        final String[] input = string.split(",");
        final int[] lex = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            lex[i] = Integer.parseInt(input[i]);
        }
        display(lex, sorted);
        for (int i = 0; i < num; ++i) {
            nextPermutation(lex, sorted);
        }
    }
    
    public static void nextPermutation(final int[] lex) {
        int i;
        for (i = lex.length - 1; i > 0 && lex[i - 1] >= lex[i]; --i) {}
        int j;
        for (j = lex.length - 1; lex[j] <= lex[i - 1]; --j) {}
        int temp = lex[i - 1];
        lex[i - 1] = lex[j];
        lex[j] = temp;
        for (j = lex.length - 1; i < j; ++i, --j) {
            temp = lex[i];
            lex[i] = lex[j];
            lex[j] = temp;
        }
    }
    
    public static void nextPermutation(final int[] lex, final TextArea sorted) {
        int i;
        for (i = lex.length - 1; i > 0 && lex[i - 1] >= lex[i]; --i) {}
        int j;
        for (j = lex.length - 1; lex[j] <= lex[i - 1]; --j) {}
        int temp = lex[i - 1];
        lex[i - 1] = lex[j];
        lex[j] = temp;
        for (j = lex.length - 1; i < j; ++i, --j) {
            temp = lex[i];
            lex[i] = lex[j];
            lex[j] = temp;
        }
        display(lex, sorted);
    }
    
    public static void display(final int[] lex, final TextArea sorted) {
        sorted.appendText("Permutation #" + PermutationLex.permNum + "\n");
        for (int i = 0; i < lex.length; ++i) {
            if (i == lex.length - 1) {
                sorted.appendText(lex[i] + "");
            }
            else {
                sorted.appendText(lex[i] + ", ");
            }
        }
        sorted.appendText("\n\n");
        ++PermutationLex.permNum;
    }
}
