package huffman.huffman.compression;

import huffman.huffman.logic.FrequencyCounter;
import huffman.huffman.logic.HuffmanTree;
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
    private char[] chars;
    private FrequencyCounter frequencyCounter = new FrequencyCounter();
    private int readBits;
    private int writtenBits;
    private int[] frequencies;

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
        output=new Output(outputFile);

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
        
        //Pakkaus on valmis, suljetaan tiedostoon kirjoitus.
        output.close();

    }

    /**
     * Metodi palauttaa luettujen bittien määrän
     *
     * @return Luetut bitit
     */
    public int getReadBits() {
        return readBits;
    }

    /**
     * Metodi palauttaa kirjoitettujen bittien määrän
     *
     * @return Kirjoitetut bitit
     */
    public int getWrittenBits() {
        return writtenBits;
    }

}
