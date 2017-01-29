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
 * Luokka tiedoston lukemiseen
 *
 */
//Reilusti vaiheessa eikä siis myöskään oikein dokumentoitu.
public class Input {

    private InputStream in;

    private int currentByte;

    private int bitsRemaining;

    public Input() {
        this.currentByte = 0;
        this.bitsRemaining = 0;
    }

    /**
     * Metodi joka lukee tiedoston tavuina ja kirjoittaa ne Stringiin
     *
     * @param inputFile Luettava tiedosto
     * @return Merkkijono luetuista tavuista
     */
    public String readBytes(File inputFile) {
        int read;

        try {
            in = new BufferedInputStream(new FileInputStream(inputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        StringBuilder sb = new StringBuilder();

        try {
            while ((read = in.read()) != -1) {
                sb.append(read);
            }
        } catch (IOException ex) {
            System.out.println("Ongelmia tiedoston lukemisessa");
        }

        try {
            in.close();
        } catch (IOException ex) {
            System.out.println("Ongelmia lukuvirran sulkemisessa");
        }
        return sb.toString();
    }

    public int readBit(File inputFile) {
        int read;

        try {
            in = new BufferedInputStream(new FileInputStream(inputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("Luettavaa tiedostoa ei löydy");
        }

        if (currentByte == -1) {
            return -1;
        }
        if (bitsRemaining == 0) {
            try {
                currentByte = in.read();
            } catch (IOException ex) {
                System.out.println("Ongelmia tavuvirran lukemisessa");
            }
            if (currentByte == -1) {
                return -1;
            }
            bitsRemaining = 8;
        }

        bitsRemaining--;

        int result = (currentByte >>> bitsRemaining) & 1;

        try {
            in.close();
        } catch (IOException ex) {
            System.out.println("Virhe tavuvirran sulkemisessa");
        }
        currentByte = -1;
        bitsRemaining = 0;

        if (result != -1) {
            return result;
        }

        return -1;
    }

}
