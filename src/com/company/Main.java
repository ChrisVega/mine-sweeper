package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        MineSweeper field1 = new MineSweeper(new char[][]{{'*', '.', '.', '.'}, {'.', '.', '.', '.'}, {'.', '*', '.', '.'}, {'.', '.', '.', '.'}});
        System.out.println(field1.displayResult());

        while(!field1.isGameOver()) {
            field1.input(scan.next());
            System.out.println(field1.displayResult());
        }
    }
}
