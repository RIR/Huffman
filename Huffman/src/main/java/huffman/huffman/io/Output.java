package huffman.huffman.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Raine Rantanen
 * Luokka tiedostoon kirjoittamiseen
 */
public class Output {

    private OutputStream out;

    private int currentByte;

    private int bits;

    /**
     * Luokan konstruktori. 
     * @param outputFile Kirjoitettava tiedosto.
     */
    public Output(File outputFile) {
        this.currentByte = 0;
        this.bits = 0;

        try {
            out = new BufferedOutputStream(new FileOutputStream(outputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Problem in Output()");
        }
    }

    /**
     * Metodi bittien kirjoittamista varten. Bitit kirjoitetaan aina tavu
     * kerrallaan.
     *
     * @param bit Parametrina annettu kirjoitettava bitti. Oltava 1 tai 0.
     */
    public void writeBit(int bit) {

        if (bit != 1 && bit != 0) {
            System.out.println("Parameter has to be 1 or 0");
        } else {
            currentByte = (currentByte << 1) | bit;
            bits++;

            if (bits == 8) {
                try {
                    out.write(currentByte);
                } catch (IOException ex) {
                    System.out.println("I/O exception when writing to outputstream. Problem in writeBit()");
                }
                currentByte = 0;
                bits = 0;
            }
        }
    }

    /**
     * Metodi sulkee tavuvirran.
     */
    public void close() {
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("I/O exception when closing outputstream. Problem in Output.close()");
        }
    }
}
