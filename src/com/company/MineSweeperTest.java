package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MineSweeperTest {
    char testField1[][] = {{'*', '.', '.', '.'}, {'.', '.', '.', '.'}, {'.', '*', '.', '.'}, {'.', '.', '.', '.'}};
    char expectedOutput1[][] = {{'*', '1', '0', '0'}, {'2', '2', '1', '0'}, {'1', '*', '1', '0'}, {'1', '1', '1', '0'}};
    MineSweeper field1 = new MineSweeper(testField1);

    char testField2[][] = {{'*', '*', '.', '.', '.'}, {'.', '.', '.', '.', '.'}, {'.', '*', '.', '.', '.'}};
    char expectedOutput2[][] = {{'*', '*', '1', '0', '0'}, {'3', '3', '2', '0', '0'}, {'1', '*', '1', '0', '0'}};
    MineSweeper field2 = new MineSweeper(testField2);

    @Test
    public void checkDimentions() {
        Assert.assertEquals(4, field1.getHeight());
        Assert.assertEquals(4, field1.getLength());

        Assert.assertEquals(3, field2.getHeight());
        Assert.assertEquals(5, field2.getLength());
    }

    @Test
    public void fieldTest() {
        System.out.println("Field #1:");
        Assert.assertArrayEquals(expectedOutput1, field1.evaluateSpaces());
        System.out.println("");
        System.out.println("Field #2:");
        Assert.assertArrayEquals(expectedOutput2, field2.evaluateSpaces());
    }
}
