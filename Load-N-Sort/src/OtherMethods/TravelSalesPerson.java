package OtherMethods;

import UserInterface.*;

public class TravelSalesPerson
{
    public static int firstCity;
    public static int checkCity;
    public static int citiesNum;
    public static int cost;
    public static int bestCost;
    public static int[] bestRoute;
    public static int[] citiesLex;
    public static int[][] thisMatrix;
    
    public static void setupTSP(final int[][] matrix) {
        PrimaryWindow.sorted.clear();
        TravelSalesPerson.thisMatrix = matrix;
        TravelSalesPerson.citiesNum = matrix.length;
        TravelSalesPerson.citiesLex = new int[TravelSalesPerson.citiesNum];
        TravelSalesPerson.bestRoute = new int[TravelSalesPerson.citiesNum];
        for (int i = 0; i < TravelSalesPerson.citiesNum; ++i) {
            TravelSalesPerson.citiesLex[i] = i;
        }
        TravelSalesPerson.firstCity = TravelSalesPerson.citiesLex[0];
        TravelSalesPerson.checkCity = TravelSalesPerson.citiesLex[TravelSalesPerson.citiesNum - 1];
        TSP(TravelSalesPerson.citiesLex);
    }
    
    public static void setupTSP(final double[][] matrix) {
    }
    
    public static void TSP(final int[] citiesLex) {
        TravelSalesPerson.cost = 0;
        while (citiesLex[0] == TravelSalesPerson.firstCity) {
            TravelSalesPerson.cost = 0;
            for (int i = 0; i < TravelSalesPerson.citiesNum - 1; ++i) {
                TravelSalesPerson.cost += TravelSalesPerson.thisMatrix[citiesLex[i]][citiesLex[i + 1]];
            }
            TravelSalesPerson.cost += TravelSalesPerson.thisMatrix[citiesLex[TravelSalesPerson.citiesNum - 1]][citiesLex[0]];
            if (TravelSalesPerson.cost < TravelSalesPerson.bestCost) {
                TravelSalesPerson.bestCost = TravelSalesPerson.cost;
                for (int i = 0; i < TravelSalesPerson.citiesNum; ++i) {
                    final int temp = citiesLex[i];
                    TravelSalesPerson.bestRoute[i] = temp;
                }
            }
            if (citiesLex[1] == TravelSalesPerson.checkCity) {
                break;
            }
            PermutationLex.nextPermutation(citiesLex);
        }
        display();
    }
    
    public static void display() {
        PrimaryWindow.sorted.appendText("Best Route: ");
        for (int i = 0; i < TravelSalesPerson.citiesNum; ++i) {
            PrimaryWindow.sorted.appendText(TravelSalesPerson.bestRoute[i] + " -> ");
        }
        PrimaryWindow.sorted.appendText(TravelSalesPerson.bestRoute[0] + "\n");
        PrimaryWindow.sorted.appendText("Best Cost = " + TravelSalesPerson.bestCost + "\n");
        PrimaryWindow.sorted.appendText("\n\n");
    }
    
    static {
        TravelSalesPerson.bestCost = 1000000;
    }
}
