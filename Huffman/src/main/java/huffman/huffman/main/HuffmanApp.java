package huffman.huffman.main;

import huffman.huffman.logic.Compress;
import huffman.huffman.logic.Decompress;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen
 *
 * Luokka josta ohjelma ajetaan. Ohjelma suorittaa syötteen perusteella ohjelman toiminnot tai palauttaa
 * virheviestin jos käyttäjän syöte ei ohjelman vaatimusten mukainen.
 */
public class HuffmanApp {

    /**
     * Main metodi joka saa argumentteina käyttäjän komennot. 
     *
     * @param args Käyttäjän komennot. 
     */
    public static void main(String[] args) {
        
        // Jos ensimmäinen argumentti on compress, niin suoritetaan tiedoston pakkaus
        switch (args[0]) {
            case "-compress": {
                try {
                    /* Tiedoston pakkausluokka saa parametreina pakattavan ja pakatun tiedoston
                nimen
                     */
                    Compress compress = new Compress(new File(args[1]), new File(args[2]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found! Please type correct file name for the file that "
                            + "you want to compress.");
                }
            }
            break;

            /* JOs ensimmäinen argumentti on decompress, niin suoritetaan 
                pakatun tiedoston purku
             */
            case "-decompress": {
                try {

                    /* Tiedoston pakkausluokka saa parametreina purettavan ja puretun tiedoston
                nimen
                     */
                    Decompress decompress = new Decompress(new File(args[1]), new File(args[2]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found! Please type correct file name for the file that "
                            + "you want to decompress.");
                }
            }
            break;

               case "-help": {
                   System.out.println("Anna käsky muodossa <-toiminto> <lähdetiedoston nimi> <kohdetiedosto nimi>"
                           + "Toiminnot: "
                           + "-compress Pakkaa lähdetiedoston ja nimeää pakatun tiedoston antamallasi kohdetiedoston nimellä."
                           + "-decompress Purkaa antamasi lähdetiedoston pakkauksen ja nimeää puretun tiedoston antamallasi kohdetiedoston nimellä."
                           + "-help Tulostaa nämä ohjeet");
            }
            // Jos ensimmäinen argumentti on jotain muuta, palautetaan virheviesti
            default:
                throw new IllegalArgumentException("Illegal command line argument");
        }
    }
}
