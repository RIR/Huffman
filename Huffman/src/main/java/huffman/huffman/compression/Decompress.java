package huffman.huffman.compression;

import huffman.huffman.io.Input;
import huffman.huffman.io.Output;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen Luokka pakatun tiedoston purkamiseen.
 */
public class Decompress {

    private Input input;
    private Output output;  

    /**
     * Luokan konstruktori joka saa parametreina tiedostojen nimet purettavalle ja puretulle tiedostolle.
     * @param inputFile Purettava tiedosto
     * @param outputFile Purettu tiedosto
     * @throws FileNotFoundException Palauttaa poikkeuksen jos purettavaksi haluttua tiedosto ei löydy
     */
    public Decompress(File inputFile, File outputFile) throws FileNotFoundException {
        input=new Input(inputFile);
    }

}
