package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String[][] vytvorMapu(int rows, int cols) {
        String[][] mapa;

        mapa = new String[rows][cols];
        for (int r= 0; r < rows; r++) {
            for (int c= 0; c < cols; c++) {
                mapa[r][c] = "_";
            }
        }
        return mapa;
    }

    private static void nakresliMapu(String[][] mapa,int rows) {
        for (int r= 0; r < rows; r++) {
            System.out.println(Arrays.toString(mapa[r]));
        }
    }

    private static boolean inArray(int[][] arr, int[] toCheckValue)
    {
        boolean output = false;
        for (int[] element : arr) {
            if (element[0] == toCheckValue[0] && element[1] == toCheckValue[1]) {
                output = true;
                break;
            }
        }
        return output;
    }

    private  static void skontrolujMini(int[][] mini, int m, int rows, int cols) {
        Random rand = new Random();

        mini[m][0] = rand.nextInt(rows);
        mini[m][1] = rand.nextInt(cols);
        for (int j=0;j < m;j++) {
            if (mini[j][0] == mini[m][0] && mini[j][1] == mini[m][1]) {
                skontrolujMini(mini, m, rows, cols);
            }
        }
    }

    private static void genereujMini(int rows, int cols,int[][] mini) {
        int pocetMin = rows*cols/6;

        for (int m= 0; m < pocetMin; m++) {
            skontrolujMini(mini,m,rows,cols);
        }

        // Vypisanie min
        for (int j=0; j<pocetMin;j++) {
            System.out.println(Arrays.toString(mini[j]));
        }
    }
    private static void pridajMini(String[][] mapa, int rows, int cols, int[][] mini ){

        for (int[] mina : mini) {
            mapa[mina[0]][mina[1]] = "*";

            if(mina[0] > 0) {
                try{
                    int number = Integer.parseInt(mapa[mina[0]-1][mina[1]]);
                    mapa[mina[0]-1][mina[1]] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    mapa[mina[0]-1][mina[1]] = "1";
                }

                if(mina[1] > 0) {
                    try{
                        int number = Integer.parseInt(mapa[mina[0]-1][mina[1]-1]);
                        mapa[mina[0]-1][mina[1]-1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        mapa[mina[0]-1][mina[1]-1] = "1";
                    }
                }

                if(mina[1] < (cols-1) ) {
                    try{
                        int number = Integer.parseInt(mapa[mina[0]-1][mina[1]+1]);
                        mapa[mina[0]-1][mina[1]+1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        mapa[mina[0]-1][mina[1]+1] = "1";
                    }
                }
            }

            if(mina[1] > 0) {
                try{
                    int number = Integer.parseInt(mapa[mina[0]][mina[1]-1]);
                    mapa[mina[0]][mina[1]-1] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    mapa[mina[0]][mina[1]-1] = "1";
                }
            }

            if(mina[1] < (cols-1) ) {
                try{
                    int number = Integer.parseInt(mapa[mina[0]][mina[1]+1]);
                    mapa[mina[0]][mina[1]+1] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    mapa[mina[0]][mina[1]+1] = "1";
                }
            }

            if(mina[0] < (rows-1)) {
                try{
                    int number = Integer.parseInt(mapa[mina[0]+1][mina[1]]);
                    mapa[mina[0]+1][mina[1]] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    mapa[mina[0]+1][mina[1]] = "1";
                }

                if(mina[1] > 0) {
                    try{
                        int number = Integer.parseInt(mapa[mina[0]+1][mina[1]-1]);
                        mapa[mina[0]+1][mina[1]-1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        mapa[mina[0]+1][mina[1]-1] = "1";
                    }
                }

                if(mina[1] < (cols-1) ) {
                    try{
                        int number = Integer.parseInt(mapa[mina[0]+1][mina[1]+1]);
                        mapa[mina[0]+1][mina[1]+1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        mapa[mina[0]+1][mina[1]+1] = "1";
                    }
                }
            }

        }

        for (int[] mina : mini) {
            mapa[mina[0]][mina[1]] = "*";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cols;
        int rows;
        int pocetMin;
        int[][] mini;
        int[] suradnice = {-1,-1};
        boolean endProcess = true;
        //int[][] najdeneMini =
        String[][] mapa;
        String[][] mapaMini;

        cols = sc.nextInt();
        rows = sc.nextInt();

        pocetMin  = rows*cols/6;

        mini = new int[pocetMin][2];
        genereujMini(rows,cols,mini);
        mapa = vytvorMapu(rows,cols);
        mapaMini = vytvorMapu(rows,cols);
        pridajMini(mapaMini,rows,cols,mini);

        nakresliMapu(mapaMini,rows);

        while(endProcess) {
            System.out.println("Zadaj suradnice[X Y]");
            suradnice[1] = sc.nextInt()-1;
            suradnice[0] = sc.nextInt()-1;

            if(inArray(mini,suradnice)) {
                System.out.println("Mine was found!");
                mapa[suradnice[0]][suradnice[1]] = "M";
                endProcess = false;
            } else {
                try{
                    int number = Integer.parseInt(mapaMini[suradnice[0]][suradnice[1]]);
                    mapa[suradnice[0]][suradnice[1]] = Integer.toString(number);
                }
                catch (NumberFormatException ex){
                    mapa[suradnice[0]][suradnice[1]] = "0";
                }
            }

            nakresliMapu(mapa,rows);
        }
    }

}