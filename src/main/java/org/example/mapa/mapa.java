package org.example.mapa;

import java.util.Random;

public class Mapa {
    int rows;
    int cols;
    String[][] hraciePole;
    int[][] mini;
    Random rand;
    int najdeneMini = 0;

    public void najdeneVsetkyMini() {
        this.najdeneMini = getPocetCelkovychMin();
    }

    public void setMini(int[][] miniVytupne) {
        this.mini = miniVytupne;
    }

    public int getPocetNajdenychMin() {
        return najdeneMini;
    }

    public int getPocetCelkovychMin() {
        return rows*cols/6;
    }

    public int[][] getMini() {
        return mini;
    }

    public void najdenaMina() {
        najdeneMini++;
    }

    public void setBunkaMapa(int r, int c, String symbol) {
        hraciePole[r][c] = symbol;
    }

    public String getBunkaMapa(int r, int c) {
        return hraciePole[r][c];
    }

    public Mapa(int r, int c) {
        this.cols = c;
        this.rows = r;
        hraciePole = new String[rows][cols];
        this.rand = new Random();
        this.mini = new int[c*r/6][2];
    }

    public void vytvorMapu() {
        for (int r= 0; r < rows; r++) {
            for (int c= 0; c < cols; c++) {
                hraciePole[r][c] = "_";
            }
        }
    }

    public void nakresliMapu() {
        String[] riadok;
        for (int r= 0; r < rows; r++) {
            riadok = hraciePole[r];
            for (String bunka : riadok) {
                System.out.print("|"+bunka);
            }
            System.out.print("|\n");
        }
    }

    private  void skontrolujMini(int m) {
        mini[m][0] = rand.nextInt(rows);
        mini[m][1] = rand.nextInt(cols);
        for (int j=0;j < m;j++) {
            if (mini[j][0] == mini[m][0] && mini[j][1] == mini[m][1]) {
                this.skontrolujMini(m);
            }
        }
    }

    public void genereujMini() {
        int pocetMin = this.getPocetCelkovychMin();

        for (int m= 0; m < pocetMin; m++) {
            this.skontrolujMini(m);
        }
    }

    public void pridajMini(){

        for (int[] mina : mini) {
            hraciePole[mina[0]][mina[1]] = "*";

            if(mina[0] > 0) {
                try{
                    int number = Integer.parseInt(hraciePole[mina[0]-1][mina[1]]);
                    hraciePole[mina[0]-1][mina[1]] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    hraciePole[mina[0]-1][mina[1]] = "1";
                }

                if(mina[1] > 0) {
                    try{
                        int number = Integer.parseInt(hraciePole[mina[0]-1][mina[1]-1]);
                        hraciePole[mina[0]-1][mina[1]-1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        hraciePole[mina[0]-1][mina[1]-1] = "1";
                    }
                }

                if(mina[1] < (cols-1) ) {
                    try{
                        int number = Integer.parseInt(hraciePole[mina[0]-1][mina[1]+1]);
                        hraciePole[mina[0]-1][mina[1]+1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        hraciePole[mina[0]-1][mina[1]+1] = "1";
                    }
                }
            }

            if(mina[1] > 0) {
                try{
                    int number = Integer.parseInt(hraciePole[mina[0]][mina[1]-1]);
                    hraciePole[mina[0]][mina[1]-1] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    hraciePole[mina[0]][mina[1]-1] = "1";
                }
            }

            if(mina[1] < (cols-1) ) {
                try{
                    int number = Integer.parseInt(hraciePole[mina[0]][mina[1]+1]);
                    hraciePole[mina[0]][mina[1]+1] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    hraciePole[mina[0]][mina[1]+1] = "1";
                }
            }

            if(mina[0] < (rows-1)) {
                try{
                    int number = Integer.parseInt(hraciePole[mina[0]+1][mina[1]]);
                    hraciePole[mina[0]+1][mina[1]] = Integer.toString(number+1);
                }
                catch (NumberFormatException ex){
                    hraciePole[mina[0]+1][mina[1]] = "1";
                }

                if(mina[1] > 0) {
                    try{
                        int number = Integer.parseInt(hraciePole[mina[0]+1][mina[1]-1]);
                        hraciePole[mina[0]+1][mina[1]-1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        hraciePole[mina[0]+1][mina[1]-1] = "1";
                    }
                }

                if(mina[1] < (cols-1) ) {
                    try{
                        int number = Integer.parseInt(hraciePole[mina[0]+1][mina[1]+1]);
                        hraciePole[mina[0]+1][mina[1]+1] = Integer.toString(number+1);
                    }
                    catch (NumberFormatException ex){
                        hraciePole[mina[0]+1][mina[1]+1] = "1";
                    }
                }
            }

        }

        for (int[] mina : mini) {
            hraciePole[mina[0]][mina[1]] = "*";
        }
    }


    public  void vypisPocetMin() {
        int pocetMin = this.rows*this.cols/6;
        if(pocetMin == 1) {
            System.out.println("Pocet umiestnenych min je "+pocetMin+". Najdi ju!");
        } else {
            System.out.println("Pocet umiestnenych min je "+pocetMin+". Najdi ich!");
        }
    }
}
