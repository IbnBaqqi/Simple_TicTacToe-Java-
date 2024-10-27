package tictactoe;

import java.util.Scanner;

public class Trial {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[][] table = new String[3][3];
        draw3x3(input, table);
        checkWin(table);
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

    public static void checkWin(String[][] table){
       /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        } */
        //int i = 0, j = 0;
        //Check row wins

        boolean xWins = false, oWins = false;
        for(int i = 0; i < 3; i++) {
            if (table[i][0].equals(table[i][1]) && table[i][1].equals(table[i][2])) {
                System.out.println(table[i][0] + " wins");
                return;
            }
        }
        //Check column wins
        for(int i = 0; i < 3; i++) {
            if (table[0][i].equals(table[1][i]) && table[1][i].equals(table[2][i])) {
                if (table[i][0].equals("x")) xWins = true;
                if (table[i][0].equals("o")) oWins = true;;
                //return;
            }
        }
        //Check diagonal wins
        if (table[0][0].equals(table[1][1]) && table[1][1].equals(table[2][2])) {
            System.out.println(table[0][0] + " wins");
            return;
        }
        if (table[0][2].equals(table[1][1]) && table[1][1].equals(table[2][0])) {
            System.out.println(table[0][2] + " wins");
            return;
        }
        //Check for draw or incomplete game
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals(" ")) {
                    draw = false;
                    break;
                }
            }
        }
        if(draw) {
            System.out.println("Draw");
        }else {
            System.out.println("Game not finished");
        }

        if(xWins && oWins) {
            System.out.println("impossible");
        }else if (xWins) {
            System.out.println("X Wins");
        } else if (oWins) {
            System.out.println("O Wins");
        }
    }
}