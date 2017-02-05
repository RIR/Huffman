package huffman.huffman.domain;

/**
 *
 * @author Raine Rantanen
 * Luokka Huffman-koodauksen puuta varten.
 */
public class Trie {
    private int[] frequencies;

    /**
     * Luokan konstruktori joka saa parametrina luettavien merkkien
     * toistumistaulukon.
     * @param frequencies Parametrina annettava merkkien toistumistaulukko
     */
    public Trie(int[] frequencies) {
        this.frequencies = frequencies;
    }
    
    
}
