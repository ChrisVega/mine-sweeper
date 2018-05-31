package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MinesweeperGUI {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;

    private JPanel masterPanel;
    private JPanel ezPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    private static JFrame frame = new JFrame("Minesweeper");
    private MineSweeper field1;
    private int difficulty;
    private int[] mineXcoord = new int[4];
    private int[] mineYcoord = new int[4];

    public MinesweeperGUI() {
        /*
        please pretend I numbered the buttons intelligently, I have put too much effort into this as is.
        */
        button1.addActionListener(new SpaceClicked("1,1"));
        button5.addActionListener(new SpaceClicked("1,2"));
        button6.addActionListener(new SpaceClicked("1,3"));
        button7.addActionListener(new SpaceClicked("1,4"));

        button2.addActionListener(new SpaceClicked("2,1"));
        button8.addActionListener(new SpaceClicked("2,2"));
        button9.addActionListener(new SpaceClicked("2,3"));
        button10.addActionListener(new SpaceClicked("2,4"));

        button3.addActionListener(new SpaceClicked("3,1"));
        button13.addActionListener(new SpaceClicked("3,2"));
        button12.addActionListener(new SpaceClicked("3,3"));
        button11.addActionListener(new SpaceClicked("3,4"));

        button4.addActionListener(new SpaceClicked("4,1"));
        button14.addActionListener(new SpaceClicked("4,2"));
        button15.addActionListener(new SpaceClicked("4,3"));
        button16.addActionListener(new SpaceClicked("4,4"));

        button17.addActionListener(new SpaceClicked("1,5"));
        button18.addActionListener(new SpaceClicked("2,5"));
        button19.addActionListener(new SpaceClicked("3,5"));
        button20.addActionListener(new SpaceClicked("4,5"));
        button21.addActionListener(new SpaceClicked("5,1"));
        button22.addActionListener(new SpaceClicked("5,2"));
        button23.addActionListener(new SpaceClicked("5,3"));
        button24.addActionListener(new SpaceClicked("5,4"));
        button25.addActionListener(new SpaceClicked("5,5"));

        difficulty = chooseDifficulty();
        field1 = new MineSweeper(generateGameField());
    }


    private class SpaceClicked implements ActionListener {
        private String coordinates;

        public SpaceClicked(String coord) {
            this.coordinates = coord;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            field1.input(coordinates);
            ((JButton) e.getSource()).setText(field1.displayResult());
            if (field1.isGameOver()) {
                JOptionPane.showMessageDialog(null, field1.getStaus(),
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }
        }
    }

    private void resetGame() {
        MinesweeperGUI newone = new MinesweeperGUI();
        frame.setContentPane(newone.getGamePanel());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frame.setContentPane(new MinesweeperGUI().getGamePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private char[][] generateGameField() {
        char[][] game = getFieldForDifficulty();
        getRandomCoordinates(game);
        setMines(game);
        return game;
    }

    private void getRandomCoordinates(char[][] game) {
        Random random = new Random();
        int ybound = game.length;
        int xbound = game[0].length;
        generateCoordinates(ybound, xbound, random);
        checkForRepeatCoordinates(ybound, xbound, random);
    }

    private void generateCoordinates(int ybound, int xbound, Random random){
        int loops = setLoops();
        for (int i = 0; i < loops; i++) {
            mineYcoord[i] = random.nextInt(ybound);
            mineXcoord[i] = random.nextInt(xbound);
        }
    }

    private int setLoops(){
        if(difficulty == 0) {
            return 2;
        } else {
            return 4;
        }
    }

    private void checkForRepeatCoordinates(int ybound, int xbound, Random random) {
        for (int i = 0; i < (setLoops()/2); i++) {
            int base = (i*2);
            while (mineYcoord[base] == mineYcoord[base+1]) {
                mineYcoord[base+1] = random.nextInt(ybound);
            }
            while (mineXcoord[base] == mineXcoord[base+1]) {
                mineXcoord[base+1] = random.nextInt(xbound);
            }
        }
    }

    private void setMines(char[][] game) {
        for (int i = 0; i < (setLoops()/2); i++) {
            int base = (i * 2);
            game[mineYcoord[base]][mineXcoord[base]] = '*';
            game[mineYcoord[base+1]][mineXcoord[base+1]] = '*';
        }
    }

    private int chooseDifficulty(){
        Object[] options = {"2EZ", "unEZ"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Select difficulty",
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return choice;
    }

    private JPanel getGamePanel(){
        if(difficulty == 0){
            return ezPanel;
        } else {
            return masterPanel;
        }
    }

    private char[][] getFieldForDifficulty(){
        if(difficulty == 0) {
           return new char[][]{{'.', '.', '.', '.'}, {'.', '.', '.', '.'}, {'.', '.', '.', '.'}, {'.', '.', '.', '.'}};
        } else {
            return new char[][]{{'.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'}};
        }
    }
}
