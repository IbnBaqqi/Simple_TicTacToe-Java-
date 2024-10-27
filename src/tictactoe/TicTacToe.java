package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static final public Scanner scan = new Scanner(System.in);
    static String[][] table = new String[3][3];
    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //String input = scan.nextLine();
        //String[][] table = new String[3][3];
        //draw3x3(input, table);
        //getplay("X");
        // checkWin(table);
        playgame();
    }
    //Game loop
    public static void playgame(){
        String emptyGrid = "         ";
        String player1 = "X";
        String player2 = "O";
        boolean turns = true;
        int turnCount = 1;
        draw3x3(emptyGrid, table);
        while (!xWins || !oWins) {

            if (turns) {
                getplay(player1);
            } else {
                getplay(player2);
            }
            checkWin(table);
            turnCount++;
            if(turnCount % 2 == 0) {
                turns = false;
            }else {
                turns = true;
            }
            if (xWins || oWins || draw) {
                break;
            }
            //drawUpdateTable(table);
        }
    }

    public static void draw3x3(String input, String[][] table) {

        System.out.println("---------");
        int i = 0;
        int index = 0;
        while(i < 3) {
            System.out.print( "| ");
            int j = 0;
            while (j < 3) {
                char currentChar = input.charAt(index);
                System.out.print(currentChar + " ");
                table[i][j] = String.valueOf((currentChar));
                index++;
                j++;
            }
            System.out.println("|");
            i++;
        }
        System.out.println("---------");
    }

    public static void getplay(String XorO){
        String input;
        int row = 0, col = 0;
        boolean validInput = false, isGridFree = false;


        //Check if only two cordinate were input
        while(!validInput || !isGridFree) {
            input = scan.nextLine();
            String[] cell = input.split(" ");
            if (cell.length == 2 && isInteger(cell[0]) && isInteger(cell[1])) {

                row = Integer.parseInt(cell[0]);
                col = Integer.parseInt(cell[1]);

                if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                    row -= 1;
                    col -= 1;

                    validInput = true;
                    isGridFree = checkGrid(row, col);
                    if (!isGridFree) {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        if (isGridFree) {
            placeInGrid(row, col, XorO);
            drawUpdateTable(table);
        }
    }
    //public static <table> void drawUpdateTable(table)
    public static void drawUpdateTable(String[][] table) {
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    //To place X or O in the 3x3 grid
    public static void placeInGrid(int row, int col, String player) {
        table[row][col] = player;
    }

    //Helper function to check if grid is empty or not
    public static boolean checkGrid(int row, int column) {
        if (table[row][column].equals(" ")) {
            return true;
        }else {
            return false;
        }
    }

    //Helper function to check if input is integer
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    static boolean xWins = false;
    static boolean oWins = false;
    static boolean draw = false;
    public static void checkWin(String[][] table){

        //boolean xWins = false, oWins = false;
        int xCount = 0, oCount = 0;
        // Count X's and O's
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equalsIgnoreCase("x")) xCount++;
                if (table[i][j].equalsIgnoreCase("o")) oCount++;
            }
        }
        //Check row wins
        for(int i = 0; i < 3; i++) {
            if (table[i][0].equals(table[i][1]) && table[i][1].equals(table[i][2])) {
                if (table[i][0].equalsIgnoreCase("x")) xWins = true;
                if (table[i][0].equalsIgnoreCase("o")) oWins = true;
                //return;
            }
        }
        //Check column wins
        for(int i = 0; i < 3; i++) {
            if (table[0][i].equals(table[1][i]) && table[1][i].equals(table[2][i])) {
                if (table[0][i].equalsIgnoreCase("x")) xWins = true;
                if (table[0][i].equalsIgnoreCase("o")) oWins = true;
                //return;
            }
        }
        //Check diagonal wins
        if (table[0][0].equals(table[1][1]) && table[1][1].equals(table[2][2])) {
            if (table[0][0].equalsIgnoreCase("x")) xWins = true;
            if (table[0][0].equalsIgnoreCase("o")) oWins = true;
            //return;
        }
        if (table[0][2].equals(table[1][1]) && table[1][1].equals(table[2][0])) {
            if (table[0][2].equalsIgnoreCase("x")) xWins = true;
            if (table[0][2].equalsIgnoreCase("o")) oWins = true;
            //return;
        }

        if(xWins && oWins) {
            System.out.println("Impossible");
        }else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else if (Math.abs(xCount - oCount) > 1) {
            System.out.println("Impossible");
        } else {
            if (xCount + oCount == 9) {
                System.out.println("Draw");
                draw = true;
            }//else
            //System.out.println("Game not finished");
            //
        }
    }
}