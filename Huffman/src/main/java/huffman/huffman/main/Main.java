package huffman.huffman.main;

import huffman.huffman.domain.FrequencyCounter;
import huffman.huffman.io.Input;
import huffman.huffman.logic.Huffman;
import java.io.File;
import java.io.FileNotFoundException;

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
        char[] array2 = {'s','a','t','a','a'};
        FrequencyCounter frequencyCounter = new FrequencyCounter();
        int[] freqs=frequencyCounter.getFrequencies(array2);
        
        for (int i = 0; i < freqs.length; i++) {
            System.out.print(freqs[i] + " ");          
        }
        
        System.out.println("");
        
        Input input = new Input(new File("Testi.txt"));
        
        char[] array=input.readBytes();
        System.out.println(array.length);
        
        System.out.println("");
        
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);           
        }
       
        String[] testitaulukko = new String[3];
        testitaulukko[0] = "compress";
        testitaulukko[1] = "Testi.txt";
        testitaulukko[2] = "Testi.txt.pakattu";
        Huffman huffman = new Huffman(testitaulukko);
    }
}
