package huffman.huffman.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Raine Rantanen Luokka tiedostoon kirjoittamiseen
 */
public class Output {

    // tavuvirta johon kirjoitetaan
    private OutputStream out;

    // Apumuuttuja bittien kirjoitusta varten. Kuvaa käsittelyssä olevaa tavua.
    private int currentByte;

    /*Apumuuuttuja joka pitää lukua parametrina saatavista biteistä
    Kun writebitiia kutsuttu 8 kertaa (eli saatu 8 bittiä) kirjoitetaan tavu
    ulos.
     */
    private int bits;

    /**
     * Luokan konstruktori
     *
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
     * Metodi bittien kirjoittamista varten. Bitit kirjoitetaan ulos aina tavu
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
                writeByte();
            }
        }
    }

    // Apumetodi joka kirjoittaa tavullisen (8 bittiä) kerrallaan kohteeseen
    private void writeByte() {

        try {
            out.write(currentByte);
        } catch (IOException ex) {
            System.out.println("I/O exception when writing to outputstream. Problem in writeBit()");
        }
        currentByte = 0;
        bits = 0;
    }

    /**
     * Metodi kirjoittaa merkin binäärinä eli tavullisen bittejä.
     */
    public void writeChar(char c) {
        for (int i = 0; i < 8; i++) {
        
        }
    }

    // Apumetodi joka täyttää tavun nollilla jos siihen ei muuten riitä bittejä
    private void fillByte() {
        while (bits > 0 && bits <= 8) {
            writeBit(0);
        }
    }

    /**
     * Metodi sulkee tavuvirran. Jos kohteeseen kirjoittamattomia bittejä jää
     * jäljelle, kirjoitetaan loppu täyteen nollia, niin että tavu tulee
     * täyteen.
     */
    public void close() {
        fillByte();

        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("I/O exception when closing outputstream. Problem in Output.close()");
        }
    }
}
