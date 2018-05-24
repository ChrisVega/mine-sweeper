package com.company;

import java.util.Arrays;

public class MineSweeper {

    private char field[][];
    private int height;
    private int length;
    private static int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public MineSweeper(char input[][]) {
        field = input;
        height = field.length;
        length = field[0].length;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public char[][] evaluateSpaces() {
        char result[][] = new char[height][length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (field[i][j] == '*') {
                    result[i][j] = '*';
                } else {
                    result[i][j] = checkAdjacent(i, j);
                }
            }
        }
        System.out.println(toString(result));
        return result;
    }

    private String toString(char[][] field) {
        StringBuilder builder = new StringBuilder();
        for (char[] row : field) {
            for (char postion : row) {
                builder.append(postion);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private char checkAdjacent(int yCoordinate, int xCoordinate) {
        int xPosition, yPosition, result = 0;
        for (int[] direction : directions) {
            xPosition = xCoordinate + direction[0];
            yPosition = yCoordinate + direction[1];
            if ((yPosition >= 0 && yPosition < height) && (xPosition >= 0 && xPosition < length) &&
                    (field[yPosition][xPosition] == '*')) {
                result++;
            }
        }
        return (char) (result + '0');
    }
}
