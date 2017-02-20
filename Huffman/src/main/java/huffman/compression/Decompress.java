package huffman.compression;

import huffman.io.Input;
import huffman.io.Output;
import huffman.logic.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * Luokka pakatun tiedoston purkamiseen.
 *
 * @author Raine Rantanen
 *
 */
public class Decompress {

    private Input input;
    private Output output;
    private long readBits;
    private long writtenBits;
    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Luokan konstruktori joka saa parametreina tiedostojen nimet purettavalle
     * ja puretulle tiedostolle.
     *
     * @param inputFile Purettava tiedosto
     * @param outputFile Purettu tiedosto
     * @throws FileNotFoundException Palauttaa poikkeuksen jos purettavaksi
     * haluttua tiedosto ei löydy
     */
    public Decompress(File inputFile, File outputFile) throws FileNotFoundException {
        input = new Input(inputFile);
        output = new Output(outputFile);

        // Luetaan puu binääristä takaisin
        Node root = input.readHuffmanTree();

        // Luetaan pakkaamattoman tiedoston pituus tavuina takaisin binääristä kokonaislukumuotoon
        int length = input.readLength();

        /* Puuta ja merkkien määrää apuna käyttäen luetaan tiedot takaisin
        pakkaamattomaan muotoon.
         */
        for (int i = 0; i < length; i++) {
            Node node = root;

            while (!node.isLeaf()) {
                if (input.readBit() == 1) {
                    node = node.getRight();
                } else {
                    node = node.getLeft();
                }
            }
            output.writeChar(node.getChar());
        }

        readBits = input.getReadBitsTotal();
        writtenBits = output.getWrittenBitsTotal();

        input.close();
        output.close();
    }

    /**
     * Metodi palauttaa luettujen bittien määrän.
     *
     * @return Luetut bitit
     */
    public long getReadBits() {
        return readBits;
    }

    /**
     * Metodi palauttaa kirjoitettujen bittien määrän.
     *
     * @return Kirjoitetut bitit
     */
    public long getWrittenBits() {
        return writtenBits;
    }

     /**
     * Metodi palauttaa luettujen bittien määrän kilotavuina
     *
     * @return Luetut kilotavut
     */
    public String getReadKiloBytes() {
        return df.format((double) readBits / 8192);
    }

    /**
     * Metodi palauttaa kirjoitettujen bittien määrän kilotavuina
     *
     * @return Kirjoitetut kilotavut
     */
    public String getWrittenKiloBytes() {
        return df.format((double) writtenBits / 8192);
    }

}
