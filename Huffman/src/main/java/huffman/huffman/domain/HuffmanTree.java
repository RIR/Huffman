package huffman.huffman.domain;

import java.util.PriorityQueue;

/**
 *
 * @author Raine Rantanen Luokka Huffman-koodauksen puuta varten.
 */
public class HuffmanTree {

    private int[] frequencies;
    
    // Jono jota käytetään puun muodostukseen
    private PriorityQueue<Node> queue;
         
    // Taulukko kunkin merkin binäärimuodon tallentamiseen
    private String [] codes;

    /**
     * Luokan konstruktori joka saa parametrina luettavien merkkien
     * toistumistaulukon.
     *
     * @param frequencies Parametrina annettava merkkien toistumistaulukko
     */
    public HuffmanTree(int[] frequencies) {
        this.frequencies = frequencies;             
        this.queue = new PriorityQueue();
        this.codes = new String[frequencies.length];
        
    }

    /**
     *Metodi luo Huffmanin puurakenteen.
     */
    public Node create() {
        /* Alustetaan puu yksittäisillä solmuilla (eli jonossa käytännössä nyt
        nyt yhden solmun kokoinen puu jokaiselle erilaiselle merkille
         */
        for (char i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new Node(i, frequencies[i], null, null));
            }
        }

        /* Muodostetaan näistä yksi puu niin, että aina kaksi pienintä solmua
        ynnätään (niiden merkkien toistumislukumäärät) ja luodaan uusi solmu jonka
        arvoksi tulee näiden solmujen yhteistulos. Uusi solmu lisätään puuhun ja
        ynnätyt solmut lisätään tälle solmulle lapsisolmuiksi
         */
        while (queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();          
            Node parent = new Node('\0', left.getFrequency()+right.getFrequency(), left, right);
            
            queue.add(parent);
        }   
        return queue.remove();
    }
    
    /**
     * Metodi luo listan 
     */
    public void codes () {
    
    }

}
