package utils;

import core.BaseEntity;
import core.WumpusWorld;

import java.util.Arrays;

public class GraphicMock {


    public static void print(BaseEntity[][] world,int size) {
        System.out.println(" ");
        String[][] asciiParsed = new String[size][size];
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] != null) {
                    asciiParsed[i][j] = world[i][j].asString();
                }
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.deepToString(asciiParsed[i]));
        }
        System.out.println(" ");
    }
}
