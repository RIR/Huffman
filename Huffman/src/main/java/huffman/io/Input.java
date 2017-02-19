package huffman.io;

import huffman.logic.CharArrayBuilder;
import huffman.logic.Node;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Luokka tiedostojen lukemiseen.
 *
 * @author Raine Rantanen
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
     * Metodi joka lukee tiedoston tavuina ja palauttaa merkkitaulukon.
     *
     * @return Merkkitaulukko
     */
    public char[] readFile() {
        int read;

        CharArrayBuilder chars = new CharArrayBuilder();

        try {
            while ((read = in.read()) != -1) {

                chars.add((char) read);

                // Kasvatetaan yhteensä luettujen bittien määrää
                readBitsTotal += 8;
            }
        } catch (IOException ex) {
            System.out.println("I/O exception luettaessa InputStreamia. Ongelma metodissa readFile()");
        }
        close();

        return chars.getChars();
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

        // Jos edellinen tavu luettu jo kokonaan
        if (bitsRemaining == 0) {

            //Luetaan suoraan uusi kokonainen tavu
            try {
                read = in.read();
            } catch (IOException ex) {
                System.out.println("I/O exception luettaessa InputStreamia. Ongelma metodissa readChar()");
            }

            bitsRemaining = 0;
            readBitsTotal += 8;

            return (char) read;
        }

        /* Jos sisään luetussa tavussa on vielä lukemattomia bittejä, 
        yhdistetään nykyisen tavun lukemattomat bitit, seuraavan luettavan tavun
            8-(nykyisen tavun lukemattomat) ensimmäisen bitin kanssa, jotta saadaan 
        niistä palautettua tavullinen bittejä eli merkki. Nämä eivät ole lukuvaiheessakaan
        aina tasatavullisia, sillä pakattuun tiedostoon on kirjoitettu pakkaamattoman tiedoston
        merkkien lisäksi koodauksessa käytetty Huffmanin puu sekä pakkaamattoman tiedoston merkkien
        lukumäärä.
         */
        int x = currentByte;
        x <<= (8 - bitsRemaining);

        try {
            currentByte = in.read();
        } catch (IOException ex) {
            System.out.println("I/O Exception luettaessa InputStreamia. Ongelma metodissa readChar()");
        }

        readBitsTotal += 8;

        x |= (currentByte >>> bitsRemaining);

        return (char) (x & 0xff);
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
     * Metodi lukee pakatusta tiedostosta pakkaamattoman tiedoston pituuden
     * tavuina.
     *
     * @return Pakkaamattoman tiedoston pituus tavuina. Luetaan yhteensä 4 tavua
     * eli 32 bittiä.
     */
    public int readLength() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }

        return x;
    }

    /**
     * Metodi lukee Huffmanin puun pakatun tiedoston alusta.
     *
     * @return Huffmanin puu, juurisolmun muodossa
     */
    public Node readHuffmanTree() {
        boolean isLeaf = (readBit() == 1);
        if (isLeaf) {
            return new Node(readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readHuffmanTree(), readHuffmanTree());
        }
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
