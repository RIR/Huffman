package huffman.huffman.logic;

import huffman.huffman.logic.Compress;
import huffman.huffman.logic.Decompress;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen
 * 
 * Luokka joka syötteen perusteella suorittaa ohjelman toiminnot tai palauttaa virheviestin
 * jos käyttäjän syöte ei ohjelman vaatimusten mukainen.
 */
public class Huffman {

    public Huffman(String[] array) throws FileNotFoundException {
        switch (array[0]) {
            case "compress":
                Compress compress = new Compress(new File (array[1]), new File (array[2]));
                break;
            case "decompress":
                Decompress decompress = new Decompress();
                break;
            default:
                throw new IllegalArgumentException("Illegal command line argument");
        }
    }
    
}
