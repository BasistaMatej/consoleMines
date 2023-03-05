package org.example.hrac;

public class Hrac {
    //int vyhral = 0;
    //int prehral = 0;
    int najdeneMini = 0;
    int pocetPokusov = 0;
    int pocetHier = 0;

    public int getNajdeneMini() {
        return najdeneMini;
    }

    public int getPocetPokusov() {
        return pocetPokusov;
    }

    public int getPocetHier() {
        return pocetHier;
    }
    public void incrementPocetHry() {
        pocetHier++;
    }
    public void incrementPocetPokusov() {
        pocetPokusov++;
    }
    public void incrementNajdeneMini() {
        najdeneMini++;
    }
}
