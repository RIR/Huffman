package huffman.huffman.main;

import huffman.huffman.domain.FrequencyCounter;
import huffman.huffman.io.Input;
import huffman.huffman.io.Output;
import huffman.huffman.logic.Huffman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 *
 * @author Raine Rantanen
 *
 * Ohjelman pääluokka, josta ohjelma ajetaan. Tarkoitus toteuttaa niin, että
 * ohjelma toimii käyttäjän syötteellä: java komento (compress/decompress)
 * pakattava tiedosto pakattu tiedosto eli esim. java compress pakattava.txt
 * pakattu.txt .
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // testidataa ohjelman toimintojen testaamiseen
        // Testataan tiedoston lukemista
        Input input = new Input(new File("Testi.txt"));
        Output output = new Output(new File("outputTesti.txt"));

        System.out.println("");

        char[] testArray = {'T', 'o', 'i', 'm', 'i', 'i', '\n'};

        System.out.println("");

        char[] array = input.readChars();
        // Huom! newline tuo merkin lisää pituuteen
        System.out.println(array.length);

        System.out.println("");

        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j]);
        }

        // Testataan toistumistaulukon toimintaa                  
        char[] array2 = {'s', 'a', 't', 'a', 'a'};
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        int[] freqs = frequencyCounter.getFrequencies(array2);

        //taulukko kooltaan varmasti 256 OK!
        System.out.println(freqs.length);

        for (int k = 0; k < freqs.length; k++) {
            System.out.print(freqs[k] + " ");
        }

        System.out.println("");

        String[] testitaulukko = new String[3];
        testitaulukko[0] = "compress";
        testitaulukko[1] = "Testi.txt";
        testitaulukko[2] = "Testi.txt.pakattu";
        Huffman huffman = new Huffman(testitaulukko);
    }
}
