package huffman.main;

import huffman.compression.Compress;
import huffman.compression.Decompress;
import java.io.File;
import java.io.FileNotFoundException;

/* HUOM! Jos ohjelman käynnistää Netbeansissa, se ajaa tällä hetkellä ohjelman
komentoriviargumenteilla: -compress MainTesti.txt pakattuMainTesti.txt
 */
/**
 * * Luokka josta ohjelma ajetaan, ohjelma suorittaa syötteen perusteella
 * ohjelman toiminnot tai palauttaa virheviestin jos käyttäjän syöte ei ohjelman
 * vaatimusten mukainen.
 * @author Raine Rantanen
 */
public class HuffmanApp {

    /**
     * Main metodi joka saa argumentteina käyttäjän komennot.
     *
     * @param args Käyttäjän komennot.
     */
    public static void main(String[] args) {

        // Jos käyttäjä ei anna syötettä
        if (args.length == 0) {

            // Tulostetaan ohjeistus
            help();

        } else {
            // Jos ensimmäinen argumentti on compress, niin suoritetaan tiedoston pakkaus
            switch (args[0]) {
                case "-compress": {
                    try {
                        /* Tiedoston pakkausluokka saa parametreina pakattavan ja pakatun tiedoston
                nimen
                         */
                        Compress compress = new Compress(new File(args[1]), new File(args[2] + ".hf"));

                        System.out.println("Antamasi tiedosto " + args[1] + " on pakattu. " + "Tiedosto löytyy nimellä " + args[2] + ".hf");
                        System.out.println("Alkuperäisen tiedoston koko: " + compress.getReadBits() + " bittiä. "
                                + "Pakatun tiedoston koko " + compress.getWrittenBits() + " bittiä\n"
                                + "Pakkausteho " + compress.getCompression() + " %");

                    } catch (FileNotFoundException ex) {
                        System.out.println("Tiedostoa ei löytynyt! Anna oikea nimi pakattavalle tiedostolle.");
                    }
                }
                break;

                /* Jos ensimmäinen argumentti on decompress, niin suoritetaan 
                pakatun tiedoston purku
                 */
                case "-decompress": {
                    try {

                        /* Tiedoston pakkausluokka saa parametreina purettavan ja puretun tiedoston
                nimen
                         */
                        Decompress decompress = new Decompress(new File(args[1]), new File(args[2]));

                        System.out.println("Antamasi tiedosto" + args[1] + " on purettu. " + "Tiedosto löytyy antamallasi nimellä " + args[2]);
                        System.out.println("Alkuperäisen tiedoston koko: " + decompress.getReadBits() + " bittiä. "
                                + "Puretun tiedoston koko " + decompress.getWrittenBits() + " bittiä");

                    } catch (FileNotFoundException ex) {
                        System.out.println("Tiedostoa ei löytynyt. Anna oikea nimi purettavalle tiedostolle.");
                    }
                }
                break;

                case "-help": {
                    help();
                }
                // Jos ensimmäinen argumentti on jotain muuta, palautetaan virheviesti
                default:
                    throw new IllegalArgumentException("Virheellinen komento");
            }
        }
    }

    /**
     * Metodi tulostaa ohjeet ohjelman käyttöön.
     *
     */
    private static void help() {
        System.out.println("Ohjelma pakkaa ja purkaa pakattuja tiedostoja käyttäen Huffmanin koodausta.\n"
                + "Anna käsky muodossa java -jar Huffman-1.0-SNAPSHOT.jar <-toiminto> <lähdetiedoston nimi> <kohdetiedosto nimi>\n"
                + "esim. java -jar Huffman-1.0-SNAPSHOT.jar -compress pakattava.txt pakattu.txt\n"
                + "Toiminnot: \n"
                + "-compress Pakkaa lähdetiedoston ja nimeää pakatun tiedoston antamallasi kohdetiedoston nimellä lisäten .hf tiedostopäätteen.\n"
                + "-decompress Purkaa antamasi lähdetiedoston pakkauksen ja nimeää puretun tiedoston antamallasi kohdetiedoston nimellä.\n"
                + "Huom! Purkaus toimii vain .hf-päätteisille tiedostoille.\n"
                + "-help (ei lisäargumentteja) Tulostaa nämä ohjeet");
    }
}
