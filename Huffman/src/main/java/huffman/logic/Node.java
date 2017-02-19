package huffman.logic;

/**
 *
 * @author Raine Rantanen
 * Luokka Huffman-koodauksen puurakennetta varten.
 * Luokka kuvaa puun solmua.
 */
public class Node implements Comparable<Node>  {

    private final char c;
    private int frequency;
    private final Node left, right;

    /**
     * Luokan konstruktori joka saa parametreina merkin, kyseisen merkin 
     * toistumislukumäärän, sekä solmun vasemman ja oikean lapsen (jos sellaisia on).
     * @param c Merkki
     * @param frequency Merkin toistumislukumäärä
     * @param left Solmun vasen lapsi
     * @param right Merkin oikea lapsi
     */
    public Node(char c, int frequency, Node left, Node right) {
        this.c = c;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    /**
     * Palauttaa solmun sisältämän merkin toistumiskerrat
     * @return Merkin toistumiskerrat
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Metodi asettaa solmun merkin toistumisen.
     * @param frequency Solmun merkin toistuminen
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    
    

    /**
     * Boolean-metodi joka tarkistaa onko solmu puun lehti, eli solmu jolla 
     * ei ole lapsisolmuja.
     * @return {@code true} jos kyseessä on lehti, muuten {@code false}
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
    Metodi palautta vasemman alisolmun.
     * @return Vasen alisolmu
    */
    public Node getLeft() {
        return left;
    }

    /**
     * Metodi palauttaa oikean alisolmun
     * @return Oikea alisolmu
     */
    public Node getRight() {
        return right;
    }

    /**
     * Metodi palauttaa solmun sisältämän merkin
     * @return Solmun sisältämä merkki 
     */
    public char getChar() {
        return c;
    }
      
    /**
     * Metodi solmujen keskinäistä vertailua varten, 
     * käyttää vertailuun solmun merkin toistumislukumäärää.
     * @param other Parametrina annettu toinen solmu, johon metodin omaavaa 
     * solmua verrataan.
     * @return Metodin omaavan solmun merkin toistumiskerrat-verrattavan solmun
     * merkin toistumiskerrat
     */
    @Override
    public int compareTo(Node other){
    return this.frequency-other.frequency;
    }
}
