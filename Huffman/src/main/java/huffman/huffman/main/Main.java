package huffman.huffman.main;

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
        Input input = new Input(new File("Testi.txt"));
       
        String[] testitaulukko = new String[3];
        testitaulukko[0] = "compress";
        testitaulukko[1] = "Testi.txt";
        testitaulukko[2] = "Testi.txt.pakattu";
        Huffman huffman = new Huffman(testitaulukko);
    }
}
