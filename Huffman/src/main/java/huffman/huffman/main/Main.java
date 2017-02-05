package huffman.huffman.main;

import huffman.huffman.logic.Huffman;

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

    public static void main(String[] args) {
        Huffman huffman = new Huffman(args);
    }
}
