package huffman.huffman.logic;

import huffman.huffman.io.Input;
import huffman.huffman.io.Output;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen
 * 
 * Luokka tiedoston pakkaamiseen. 
 */
public class Compress {

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Input input;
    private Output output;

    /**
     * Luokan konstruktori joka saa syötteenä halutut tiedoston nimet pakattavalle ja pakatulle tiedostolle.
     * @param inputFile Pakattava tiedoston nimi
     * @param outputFile Pakatun tiedosto nimi
     * @throws FileNotFoundException Palauttaa virheen jos pakattavaa tiedostoa ei löydy.
     */
    public Compress(File inputFile, File outputFile) throws FileNotFoundException {
        input=new Input();
        
        try {
          String inputString=input.read(inputFile);
                     
          
          //tiivistys tähän 
          
          output.write(inputString, outputFile);
            
        } catch (Exception e) {

        }

    }
}
