package org.example;

import org.example.mapa.Mapa;

import java.util.Scanner;

public class Main {

    private static boolean inArray(int[][] arr, int[] toCheckValue) {
        boolean output = false;
        for (int[] element : arr) {
            if (element[0] == toCheckValue[0] && element[1] == toCheckValue[1]) {
                output = true;
                break;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cols;
        int rows;
        int[] suradnice = {-1,-1};
        boolean hladatMinu = false;

        do {
            System.out.println("Zadaj platnu velkost plochy [sirka vyska]: ");
            cols = sc.nextInt();
            rows = sc.nextInt();
        } while (cols <= 0 || rows <= 0);

        Mapa hraciPlan = new Mapa(cols,rows);
        Mapa mapaMini = new Mapa(cols,rows);

        hraciPlan.genereujMini();
        hraciPlan.vytvorMapu();

        mapaMini.vytvorMapu();

        hraciPlan.vypisPocetMin();
        hraciPlan.nakresliMapu();

        mapaMini.setMini(hraciPlan.getMini());
        mapaMini.pridajMini();

        //mapaMini.nakresliMapu();

        while(hraciPlan.getPocetNajdenychMin() != hraciPlan.getPocetCelkovychMin()) {
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
                if(inArray(hraciPlan.getMini(),suradnice)) {
                    System.out.println("Uspesne si oznacil minu.");
                    hraciPlan.setBunkaMapa(suradnice[0],suradnice[1],"M");
                    hraciPlan.najdenaMina();
                } else {
                    System.out.println("Toto nebola mina. :( Prehral si.");
                    hraciPlan.najdeneVsetkyMini();
                }
            } else {
                if (inArray(hraciPlan.getMini(), suradnice)) {
                    System.out.println("Trafil si minu! Prehral si!");
                    hraciPlan.setBunkaMapa(suradnice[0],suradnice[1],"M");
                    hraciPlan.najdeneVsetkyMini();
                } else {
                    try {
                        int number = Integer.parseInt(mapaMini.getBunkaMapa(suradnice[0],suradnice[1] ));
                        hraciPlan.setBunkaMapa(suradnice[0],suradnice[1],Integer.toString(number));
                    } catch (NumberFormatException ex) {
                        hraciPlan.setBunkaMapa(suradnice[0],suradnice[1],"0");
                    }
                }
            }

            hraciPlan.nakresliMapu();
        }
        System.out.println("\nKoniec hry. ");

        System.out.println("Celkova mapa s minami: ");
        mapaMini.nakresliMapu();
    }

}