package huffman.huffman.logic;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen
 *
 * Luokka joka syötteen perusteella suorittaa ohjelman toiminnot tai palauttaa
 * virheviestin jos käyttäjän syöte ei ohjelman vaatimusten mukainen.
 */
public class Huffman {

    /**
     * Luokan konstruktori joka ajetaan Ohjelman käynnistyessä Main-luokasta.
     *
     * @param array Parametrina saatava Merkkijonotaulukko
     */
    public Huffman(String[] array) {

        // Jos ensimmäinen argumentti on compress, niin suoritetaan tiedoston pakkaus
        switch (array[0]) {
            case "compress": {
                try {
                    /* Tiedoston pakkausluokka saa parametreina pakattavan ja pakatun tiedoston
                nimen
                     */
                    Compress compress = new Compress(new File(array[1]), new File(array[2]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found! Please type correct file name for the file that "
                            + "you want to compress.");
                }
            }
            break;

            /* JOs ensimmäinen argumentti on decompress, niin suoritetaan 
                pakatun tiedoston purku
             */
            case "decompress": {
                try {

                    /* Tiedoston pakkausluokka saa parametreina purettavan ja puretun tiedoston
                nimen
                     */
                    Decompress decompress = new Decompress(new File(array[1]), new File(array[2]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found! Please type correct file name for the file that "
                            + "you want to decompress.");
                }
            }
            break;

            // Jos ensimmäinen argumentti on jotain muuta, palautetaan virheviesti
            default:
                throw new IllegalArgumentException("Illegal command line argument");
        }
    }
}
