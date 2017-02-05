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
        in = new BufferedInputStream(new FileInputStream(inputFile));
    }

    /**
     * Metodi joka lukee tiedoston tavuina (merkki kerrallaan) ja palauttaa
     * merkkitaulukon
     *
     * @return Merkkitaulukko
     */
    public char[] readChars() {
        int read;

        // Luetaan merkit ensin StringBuilderiin, koska ei tiedetä niiden määrää.
        StringBuilder sb = new StringBuilder();

        try {
            while ((read = in.read()) != -1) {

                // Muunnetaan luettu tavu merkiksi
                sb.append((char) read);
            }
        } catch (IOException ex) {
            System.out.println("I/O exception when reading inputstream. Problem in readChars()");
        }

        close();

        // Muunnos Stringbuilderista Stringiksi ja merkkitaulukoksi.
        char[] input = sb.toString().toCharArray();

        //palautetaan merkkitaulukko
        return input;
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
                System.out.println("I/O exception when reading inputstream. Problem in readBit()");
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

        int result = (currentByte >>> bitsRemaining) & 1;

        if (result != -1) {
            return result;
        }

        return -1;
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
            System.out.println("I/O exception when closing inputstream. Problem in close()");
        }
    }

}
