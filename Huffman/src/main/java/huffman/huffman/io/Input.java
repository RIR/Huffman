package huffman.huffman.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raine Rantanen
 */
/**
 * Luokka tiedostojen lukemiseen
 *
 */
public class Input {

    private InputStream in;

    private int currentByte;

    private int bitsRemaining;

    /**
     * Luokan konstruktori, joka alustaa bittivirran lukemista varten
     * tarvittavat tavu- ja bittiseuraajan.
     *
     * @param inputFile Luettava tiedosto
     */
    public Input(File inputFile) {
        this.currentByte = 0;
        this.bitsRemaining = 0;
        try {
            in = new BufferedInputStream(new FileInputStream(inputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Problem in Input()");
        }
    }

    /**
     * Metodi joka lukee tiedoston tavuina ja palauttaa merkkitaulukon
     *
     * @return Merkkitaulukko
     */
    public char[] readBytes() {
        int read;
        
        // Luetaan merkit ensin StringBuilderiin, koska ei tiedetä niiden määrää.
        StringBuilder sb = new StringBuilder();

        try {
            while ((read = in.read()) != -1) {
                sb.append((char)read);
            }
        } catch (IOException ex) {
            System.out.println("I/O exception when reading inputstream. Problem in readBytes()");
        }

        close();

        // Muunnos Stringbuilderista Stringiksi ja merkkitaulukoksi.
        char[] input = sb.toString().toCharArray();

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
        if (bitsRemaining == 0) {
            try {
                currentByte = in.read();
            } catch (IOException ex) {
                System.out.println("I/O exception when reading inputstream. Problem in readBit()");
            }
            if (currentByte == -1) {
                return -1;
            }
            bitsRemaining = 8;
        }

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
