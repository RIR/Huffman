package huffman.huffman.compression;

import huffman.huffman.io.Input;
import huffman.huffman.io.Output;
import huffman.huffman.logic.Node;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen 
 * Luokka pakatun tiedoston purkamiseen.
 */
public class Decompress {

    private Input input;
    private Output output;
    private int readBits;
    private int writtenBits;

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
     * @return Luetut bitit
     */
    public int getReadBits() {
        return readBits;
    }

    /**
     * Metodi palauttaa kirjoitettujen bittien määrän.
     * @return Kirjoitetut bitit
     */
    public int getWrittenBits() {
        return writtenBits;
    }

}
