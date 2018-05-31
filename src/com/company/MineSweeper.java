package com.company;

public class MineSweeper {

    private char field[][];
    private char gameField[][];
    private char evaluatedField[][];
    private int height;
    private int length;
    private static int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    private boolean gameOver = false;
    private int numberOfMoves;
    private String moveResult;
    private String status = "";

    public MineSweeper(char input[][]) {
        field = input;
        height = field.length;
        length = field[0].length;
        numberOfMoves = height * length;
        evaluatedField = evaluateSpaces();
        gameField = new char[height][length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if(field[i][j] == '*'){
                    numberOfMoves--;
                }
                gameField[i][j]='.';
            }
        }
        moveResult = toString(gameField);
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
        //System.out.println(toString(result));
        return result;
    }

    public String displayResult() {
        checkStaus();
        return moveResult;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void input(String input) {
        String [] arrOfStr = input.split(",");
        processMove(Integer.parseInt(arrOfStr[0]) - 1, Integer.parseInt(arrOfStr[1]) - 1);
    }

    public String getStaus(){
        return status;
    }

    private void processMove(int y, int x) {
        if(isAValidSpot(y, x)) {
            gameField[y][x] = evaluatedField[y][x];
            if (evaluatedField[y][x] == '*') {
                gameOver = true;
            } else {
                numberOfMoves--;
            }
            //moveResult = toString(gameField);
            moveResult = String.valueOf(evaluatedField[y][x]);
        }
    }

    private boolean isAValidSpot(int y, int x){
        if(y > height || x > length || y < 0 || x < 0){
            return false;
        } else {
            return true;
        }
    }

    private void checkStaus(){
        if(numberOfMoves == 0){
            status = status.concat("\nYou win!");
            gameOver = true;
        } else if(gameOver) {
            status = status.concat("\nYou lose!");
        }
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
