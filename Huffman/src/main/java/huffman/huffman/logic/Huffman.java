package huffman.huffman.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raine Rantanen
 * 
 * Luokka joka syötteen perusteella suorittaa ohjelman toiminnot tai palauttaa virheviestin
 * jos käyttäjän syöte ei ohjelman vaatimusten mukainen.
 */
public class Huffman {

    public Huffman(String[] array){
        switch (array[0]) {
            case "compress":
        {
            try {
                Compress compress = new Compress(new File (array[1]), new File (array[2]));
            } catch (FileNotFoundException ex) {
                System.out.println("File not found! Please type correct file name for the file that "
                        + "you want to compress.");
            }
        }
                break;
            case "decompress":
                Decompress decompress = new Decompress();
                break;
            default:
                throw new IllegalArgumentException("Illegal command line argument");
        }
    }
    
}
