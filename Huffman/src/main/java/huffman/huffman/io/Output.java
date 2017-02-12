package huffman.huffman.io;

import huffman.huffman.logic.Node;
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
    Kun writebitia kutsuttu 8 kertaa (eli saatu 8 bittiä) kirjoitetaan tavu
    ulos.
     */
    private int bits;

    //Pitää lukua yhteensä kirjoitetuista biteistä
    private int writtenBitsTotal;

    /**
     * Luokan konstruktori
     *
     * @param outputFile Kirjoitettava tiedosto.
     */
    public Output(File outputFile) {
        this.currentByte = 0;
        this.bits = 0;
        this.writtenBitsTotal = 0;

        try {
            out = new BufferedOutputStream(new FileOutputStream(outputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt! Ongelma Output-luokan konstruktorissa");
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
            System.out.println("Bitin kirjoitus ei onnistunut. Luvun oltava 1 or 0");
        } else {
            currentByte = (currentByte << 1) | bit;
            bits++;

            if (bits == 8) {
                writeByte();
            }
        }
    }

    // Apumetodi writeBitille joka kirjoittaa tavullisen (8 bittiä) kerrallaan kohteeseen
    private void writeByte() {
        try {
            out.write(currentByte);
        } catch (IOException ex) {
            System.out.println("I/O exception kirjoittaessa OutputStreamiin. Ongelma metodissa writeByte()");
        }
        currentByte = 0;
        bits = 0;
        writtenBitsTotal += 8;
    }

    // Apumetodi joka täyttää viimeisen kirjoitettavan tavun nollilla jos siihen ei muuten riitä bittejä
    private void fillByte() {
        while (bits > 0 && bits <= 8) {
            writeBit(0);
        }
    }

    /**
     * Metodi kirjoittaa parametrina saatavan merkin binäärinä.
     *
     * @param c Kirjoitettava merkki
     */
    public void writeChar(char c) {
        /* Jos yksittäisiä bittejä ei vielä varattuna kirjoitettavaksi
        voidaan kirjoittaa suoraan merkki
         */
        if (bits == 0) {
            try {
                out.write(c);
            } catch (IOException ex) {
                System.out.println("I/O exception kirjoittaessa OutputStreamiin. Ongelma metodissa writeChar()");
            }
            writtenBitsTotal += 8;
            
            // Muuten edetään bitti kerrallaan
        } else {
            for (int i = 0; i < 8; i++) {
                int bit = ((c >>> (8 - i - 1)) & 1);
                writeBit(bit);
            }
        }
    }

    /**
     * Metodi Huffmanin puun tallentamiseen binäärinä, tämän avulla saadaan
     * myöhemmin luettua pakattu tiedosto.
     *
     * @param node Puun solmu, Huffmanissa tämä ensin juurisolmu
     */
    public void writeHuffmanTree(Node node) {
        if (node.isLeaf()) {
            writeBit(1);
            writeChar(node.getChar());
            writtenBitsTotal += 9;
            return;
        }
        writeBit(0);
        writtenBitsTotal++;
        writeHuffmanTree(node.getLeft());
        writeHuffmanTree(node.getRight());
    }

    /**
     * Metodi kirjoittaa luettujen merkkien (tavujen) määrän binäärinä ulos.
     * Tätä tarvitaan purettaessa tiedostoa mm. jos kirjoitetut bitit eivät ole
     * jaollisia tavumäärällä eli bitit%8 !=0 ja viimeisen tavun täytteeksi on
     * tarvittu nollia
     *
     * @param length Luettujen merkkien määrä
     */
    public void writeLength(int length) {
        //Bittisiirrot tarpeen jotta mahdollista lukea 32-bitin kokoinen luku
        writeLengthToBits((length >>> 24) & 0xff);
        writeLengthToBits((length >>> 16) & 0xff);
        writeLengthToBits((length >>> 8) & 0xff);
        writeLengthToBits((length) & 0xff);
    }

    //Apumetodi julkiselle writeLength() metodille
    private void writeLengthToBits(int length) {
        for (int i = 0; i < 8; i++) {
            boolean bit = ((length >>> (8 - i - 1)) & 1) == 1;

            if (bit) {
                writeBit(1);
            } else {
                writeBit(0);
            }
        }
    }

    /**
     * Metodi palauttaa yhteensä kirjoitetut bitit.
     *
     * @return Yhteensä kirjoitetut bitit
     */
    public int getWrittenBitsTotal() {
        return writtenBitsTotal;
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
            System.out.println("I/O exception suljettaessa OutputStreamia. Ongelma metodissa Output.close()");
        }
    }
}
