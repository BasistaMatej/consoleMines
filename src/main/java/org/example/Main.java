package org.example;

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
        String[] riadok;
        for (int r= 0; r < rows; r++) {
            riadok = mapa[r];
            for (String bunka : riadok) {
                System.out.print("|"+bunka);
            }
            System.out.print("|\n");
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

    private  static void skontrolujMini(int[][] mini, int m, int rows, int cols, Random rand) {
        mini[m][0] = rand.nextInt(rows);
        mini[m][1] = rand.nextInt(cols);
        for (int j=0;j < m;j++) {
            if (mini[j][0] == mini[m][0] && mini[j][1] == mini[m][1]) {
                skontrolujMini(mini, m, rows, cols, rand);
            }
        }
    }

    private static void genereujMini(int rows, int cols,int[][] mini) {
        int pocetMin = rows*cols/6;
        Random rand = new Random();

        for (int m= 0; m < pocetMin; m++) {
            skontrolujMini(mini,m,rows,cols, rand);
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

    private static  void vypisPocetMin(int pocetMin) {
        if(pocetMin == 1) {
            System.out.println("Pocet umiestnenych min je "+pocetMin+". Najdi ju!");
        } else {
            System.out.println("Pocet umiestnenych min je "+pocetMin+". Najdi ich!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cols;
        int rows;
        int pocetMin;
        int[][] mini; // zoznam min a ich suradnice
        int[] suradnice = {-1,-1};
        boolean hladatMinu = false;
        int najdeneMini;

        String[][] mapa;     // Mapa, ktoru s poliami, ktore pouzivatel vyskusal
        String[][] mapaMini; // Vykreslena mapa s cislami a minami

        do {
            System.out.println("Zadaj platnu velkost plochy [sirka vyska]: ");
            cols = sc.nextInt();
            rows = sc.nextInt();
        } while (cols <= 0 || rows <= 0);

        pocetMin  = rows*cols/6;
        najdeneMini = 0;

        mini = new int[pocetMin][2];
        genereujMini(rows,cols,mini);
        mapa = vytvorMapu(rows,cols);
        mapaMini = vytvorMapu(rows,cols);
        pridajMini(mapaMini,rows,cols,mini);

        vypisPocetMin(pocetMin);
        nakresliMapu(mapa,rows);

        while(najdeneMini != pocetMin) {
            do {
                System.out.println("Zadaj platné suradnice[X Y]:");
                System.out.println("Alebo zadaj cislo 0 na oznacenie mini:");
                suradnice[1] = sc.nextInt() - 1;
                if(suradnice[1] == -1) {
                    System.out.println("Zadaj platné suradnice miny [X Y]:");
                    suradnice[1] = sc.nextInt() - 1;
                    hladatMinu = true;
                }
                suradnice[0] = sc.nextInt() - 1;
            } while (suradnice[1] < 0 || suradnice[0] < 0 || suradnice[1] >= cols || suradnice[0] >= rows);

            if(hladatMinu) {
                if(inArray(mini,suradnice)) {
                    System.out.println("Uspesne si oznacil minu.");
                    mapa[suradnice[0]][suradnice[1]] = "M";
                    najdeneMini++;
                } else {
                    System.out.println("Toto nebola mina. :( Prehral si.");
                    najdeneMini = pocetMin;
                }
            } else {
                if (inArray(mini, suradnice)) {
                    System.out.println("Trafil si minu! Prehral si!");
                    mapa[suradnice[0]][suradnice[1]] = "M";
                    najdeneMini = pocetMin;
                } else {
                    try {
                        int number = Integer.parseInt(mapaMini[suradnice[0]][suradnice[1]]);
                        mapa[suradnice[0]][suradnice[1]] = Integer.toString(number);
                    } catch (NumberFormatException ex) {
                        mapa[suradnice[0]][suradnice[1]] = "0";
                    }
                }
            }

            nakresliMapu(mapa,rows);
        }
        System.out.println("\nKoniec hry. ");

        System.out.println("Celkova mapa s minami: ");
        nakresliMapu(mapaMini,rows);
    }

}