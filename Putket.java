package Ohjelma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Putket {

    private Map<String, Double> putket;
    private List<String> apu;
    private DecimalFormat muotoilija;

    public Putket() {
        this.putket = new HashMap<>();
        this.apu = new ArrayList<>();
        this.muotoilija = new DecimalFormat("0.00");
    }
//luetaan tiedosto

    public void lueputket(String materiaali) {
        String tiedosto = materiaali + ".csv";
        try {
            Files.lines(Paths.get(tiedosto))
                    .map(l -> l.split(";"))
                    .forEach(ine -> putket.put(ine[1], Double.parseDouble(ine[3])));

        } catch (IOException ex) {

            System.out.println("Virhe tiedoston käsittelyssä.");

        }
    }

    public void lueputket2(String materiaali) {
        String tiedosto = materiaali + ".csv";
        try {

            Files.lines(Paths.get(tiedosto))
                    .map(l -> l.split(";"))
                    .forEach(a -> apu.add(a[1]));
        } catch (IOException ex) {

            System.out.println("Virhe tiedoston käsittelyssä.");

        }
    }

    //tulostaa kysytyn materiaalin putkikoot mitä laskenta tekee
    public void tulosta(String materiaali) {

        for (String alkio : apu) {

            System.out.println("materiaali " + alkio + " sisähalkaisija: " + putket.get(alkio));
        }
        apu.clear();
    }

    //ajan laskeminen
    public void laskeAika(Double mv, Scanner lukija) {
        double aika = 0;
        while (true) {
            System.out.print("Anna putkikoko (tyhjä putkikoko laskee ajan) ");
            String koko = "cu DN" + lukija.nextLine().trim().toLowerCase();
            //System.out.println(putket.get(koko));
            if (koko.equals("cu DN")) {
                break;
            } else {
                double pintaala = Math.pow((putket.get(koko) / 1000) / 2, 2) * Math.PI;
                //System.out.println(pintaala);
                double nopeus = (mv / 1000) / pintaala;

                if (nopeus > 3.0) {
                    System.out.println("Tarkista putkikoot, nopeus yli 3m/s ("+nopeus+")");
                }
                //System.out.println(nopeus);
                System.out.print("Monta metria putkea on: ");
                int matka = Integer.parseInt(lukija.nextLine());
                aika += (matka / nopeus);
                //System.out.println(aika);
            }
        }
        System.out.println("Odotusaika on: " + muotoilija.format(aika) + " sekuntia");
        System.out.println();

    }

    public void materiaalinLuku(String valinta, Scanner lukija) {

        System.out.print("Anna putkimateriaali: ");
        String materiaali = lukija.nextLine();
        if (materiaali.equals("cu") || materiaali.equals("Cu") || materiaali.equals("CU")) {
            materiaali = "kupari";
        }
        if (valinta.equals("matsku")) {
            lueputket2(materiaali);
        }
        if (valinta.equals("laske")) {
            lueputket(materiaali);
        }
    }
}
