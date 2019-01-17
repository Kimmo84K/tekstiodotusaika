package Ohjelma;

import java.util.Scanner;

public class Kayttoliittyma {

    private Putket putket;
    private Scanner lukija;
    private Tietoa tieto;

    public Kayttoliittyma() {
        this.putket = new Putket();
        this.lukija = new Scanner(System.in);
        this.tieto = new Tietoa();
    }
//ohjelman alustus
    public void ohjelma() {

        System.out.println("");
        System.out.println("käytettävissä olevat komennot:");

        System.out.println("1 Laske odotusaika\n"
                + "2 putkimateriaalien kokojen listaus\n"
                + "3 tietoa ohjelmasta\n"
                + "x lopeta");
        System.out.println("Käytettäväissä olevat putkimateriaalit\n"
                + "Kupari(Cu)\n"
                + "Muovi(m)\n"
                +"rauta(fe)"
                + "x lopeta");
        System.out.println();

        while (true) {
            System.out.print("Komento: ");
            String komento = lukija.nextLine();

            if (komento.equals("x")) {
                break;
            } else if (komento.equals("1")) {
                String valinta = "laske";
                putket.materiaalinLuku(valinta,lukija);
                System.out.print("Anna mitoitusvirtaama putkelle (l/s): ");
               
                Double mv = Double.parseDouble(lukija.nextLine().replace(",", "."));
               
            	   
               
                System.out.println("");
                putket.laskeAika(mv, lukija);
            } else if (komento.equals("2")) {
                String valinta = "matsku";
                putket.materiaalinLuku(valinta, lukija);
                putket.tulosta(komento);
            } else if (komento.equals("3")) {
                tieto.tiedot();
            } else {
                System.out.println("Viallinen komento yritä uudestaan");

            }
        }
    }
}
