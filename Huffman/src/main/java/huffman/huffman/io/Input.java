package huffman.huffman.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Raine Rantanen
 */
/**
 * Luokka tiedostojen lukemiseen
 *
 */
public class Input {

    // Luettava tavuvirta
    private InputStream in;

    //Apumuuttuja bittien lukemista varten
    private int currentByte;

    // Muuttuja pitää lukua luetun tavun luetuista biteistä
    private int bitsRemaining;

    // Laskurimuuttuja yhteensä luetuille biteille 
    private int readBitsTotal;

    /**
     * Luokan konstruktori, joka alustaa bittivirran lukemista varten
     * tarvittavat tavu- ja bittiseuraajan.
     *
     * @param inputFile Luettava tiedosto
     * @throws java.io.FileNotFoundException
     */
    public Input(File inputFile) throws FileNotFoundException {
        this.currentByte = 0;
        this.bitsRemaining = 0;
        this.in = new BufferedInputStream(new FileInputStream(inputFile));
        this.readBitsTotal = 0;
    }

    /**
     * Metodi joka lukee tiedoston tavuina (merkki kerrallaan) ja palauttaa
     * merkkitaulukon
     *
     * @return Merkkitaulukko
     */
    public char[] readFile() {
        int read;

        /* Luetaan merkit ensin StringBuilderiin, koska ei tiedetä niiden määrää.
        Tässä voisi käyttää Stringiä, mutta käsittääkseni suorituskyky parempi kun
        konkatenoidaan loopissa.
         */
        StringBuilder sb = new StringBuilder();

        try {
            while ((read = in.read()) != -1) {

                // Muunnetaan luettu tavu merkiksi
                sb.append((char) read);
            }
        } catch (IOException ex) {
            System.out.println("I/O exception luettaessa InputStreamia. Ongelma metodissa readFile()");
        }

        close();

        // Kasvatetaan yhteensä luettujen bittien määrää (luetut tavut *8)
        readBitsTotal += sb.length() * 8;

        // Viedään Stringbuilderin merkit merkkitaulukkoon
        char[] input = new char[sb.length()];
        sb.getChars(0, sb.length()-1, input, 0);

        //palautetaan merkkitaulukko
        return input;
    }

    /**
     *
     * Metodi lukee yhden merkin verran bittejä (8) ja palauttaa binäärilukua
     * vastaavan merkin.
     *
     * @return Luettu merkki
     */
    public char readChar() {
        int read = 0;
        try {
            read = in.read();
        } catch (IOException ex) {
            System.out.println("I/O exception luettaessa InputStreamia. Ongelma metodissa readChar()");
        }
        return (char) read;
    }

    /**
     * Metodi joka lukee bitin tavuvirrasta.
     *
     * @return palauttaa bitin (1/0) tai -1 jos ei enää luettavaa.
     */
    public int readBit() {
        if (currentByte == -1) {
            return -1;
        }
        /* Luetaan uusi tavu jos aiemmin luetussa luettavia bittejä ei enää
        jäljellä
         */
        if (bitsRemaining == 0) {
            try {
                currentByte = in.read();
            } catch (IOException ex) {
                System.out.println("I/O exception luettaessa InputStreamia. Ongelma metodissa readBit()");
            }
            // Jos luettava tavu on tiedoston loppu
            if (currentByte == -1) {
                return -1;
            }
            // Bittien määrä tavussa
            bitsRemaining = 8;
        }

        // Luettaessa bittejä laskuria vähennetään
        bitsRemaining--;

        // Kasvatetaan yhteensä luettujen bittien määrää
        readBitsTotal++;

        int result = (currentByte >>> bitsRemaining) & 1;

        if (result != -1) {
            return result;
        }

        return -1;
    }

    /**
     * Metodi palauttaa yhteensä luetut bitit
     *
     * @return Yhteensä luetut bitit
     */
    public int getReadBitsTotal() {
        return readBitsTotal;
    }

    /**
     * Metodi sulkee tavuvirran.
     */
    public void close() {
        currentByte = -1;
        bitsRemaining = 0;

        try {
            in.close();
        } catch (IOException ex) {
            System.out.println("I/O exception suljettaessa InputStreamia. Ongelma metodissa close()");
        }
    }

}
