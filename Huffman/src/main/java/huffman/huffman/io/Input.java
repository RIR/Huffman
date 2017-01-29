package huffman.huffman.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

    InputStream in;

    public Input() {
    }

    /**
     * Metodi joka lukee tiedoston tavuina ja kirjoittaa ne Stringiin
     *
     * @param inputFile Luettava tiedosto
     * @return Merkkijono luetuista tavuista
     * @throws FileNotFoundException poikkeus jos luettavaa tiedostoa ei löydy
     * @throws IOException Jos lukemisessa tulee virhe
     */
    public String read(File inputFile) throws FileNotFoundException, IOException {
        int read;
        in = new BufferedInputStream(new FileInputStream(inputFile));

        StringBuilder sb = new StringBuilder();

        while ((read = in.read()) != -1) {
            sb.append(read);
        }

        in.close();

        return sb.toString();
    }

}
