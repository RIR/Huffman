package huffman.compression;

import huffman.logic.FrequencyCounter;
import huffman.logic.HuffmanTree;
import huffman.io.Input;
import huffman.io.Output;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * Luokka tiedoston pakkaamiseen.
 *
 * @author Raine Rantanen
 *
 */
public class Compress {

    private Input input;
    private Output output;
    private char[] chars;
    private FrequencyCounter frequencyCounter = new FrequencyCounter();
    private long readBits;
    private long writtenBits;
    private int[] frequencies;
    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Luokan konstruktori joka saa parametreina tiedostojen nimet pakattavalle
     * ja pakatulle tiedostolle.
     *
     * @param inputFile Pakattava tiedoston nimi
     * @param outputFile Pakatun tiedosto nimi
     * @throws FileNotFoundException Palauttaa virheen jos pakattavaa tiedostoa
     * ei löydy.
     */
    public Compress(File inputFile, File outputFile) throws FileNotFoundException {
        input = new Input(inputFile);
        output = new Output(outputFile);

        //  Luetaan merkit taulukkoon         
        chars = input.readFile();

        // Lasketaan eri merkkien toistumiset
        frequencies = frequencyCounter.getFrequencies(chars);

        // Merkataan luettujen bittien määrä
        readBits = input.getReadBitsTotal();

        // Luodaan Huffmanin puu
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);

        // Kirjoitetaan Huffmanin puu binäärinä myöhempää tiedoston purkua varten
        output.writeHuffmanTree(huffmanTree.getRoot());

        // Kirjoitetaan luetun tiedoston koko tavuina      
        output.writeLength(chars.length);

        /* Kirjoitetaan merkit binääreinä käyttäen apuna Huffmanin puun avulla
        luotua merkkijonotaulukkoa. Tässä kohdin tiedoston merkit käydään läpi toiseen kertaan.
         */
        String[] binaryCodes = huffmanTree.getBinaryCodes();

        for (int i = 0; i < chars.length; i++) {
            String binary = binaryCodes[chars[i]];
            for (int j = 0; j < binary.length(); j++) {
                switch (binary.charAt(j)) {
                    case '0':
                        output.writeBit(0);
                        break;
                    case '1':
                        output.writeBit(1);
                        break;
                    default:
                        System.out.println("Tapahtui virhe luokassa Compress () "
                                + "kirjoittaessa binäärejä tiedostoon. Merkki oli joku muu "
                                + "kuin 0 tai 1."
                        );
                        break;
                }
            }
        }

        // Merkataan kirjoitettujen bittien määrä
        writtenBits = output.getWrittenBitsTotal();

        //Pakkaus on valmis, suljetaan tiedostoon kirjoitus.
        output.close();

    }

    /**
     * Metodi palauttaa luettujen bittien määrän
     *
     * @return Luetut bitit
     */
    public long getReadBits() {
        return readBits;
    }

    /**
     * Metodi palauttaa kirjoitettujen bittien määrän
     *
     * @return Kirjoitetut bitit
     */
    public long getWrittenBits() {
        return writtenBits;
    }

    /**
     * Metodi palauttaa pakkaustehokkuuden prosentteina.
     *
     * @return Pakkausprosentti
     */
    public String getCompression() {
        return df.format((double) (100.00 * (readBits - writtenBits) / readBits));
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