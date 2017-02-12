package huffman.huffman.logic;

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
    private String[] binaryCodes;

    private Node root;

    /**
     * Luokan konstruktori joka saa parametrina luettavien merkkien
     * toistumistaulukon.
     *
     * @param frequencies Parametrina annettava merkkien toistumistaulukko
     */
    public HuffmanTree(int[] frequencies) {
        this.frequencies = frequencies;

        /* !HUOM Priorityqueue varmaan sellainen tietorakenne joka luotava itse, 
        Tee tämä kun ohjelma muuten toimii
         */
        this.queue = new PriorityQueue();
        this.binaryCodes = new String[frequencies.length];

        // Luotu Huffmanin puu
        this.root = create();
              
        //binääriluvut merkeille
        listBinaryCodes(binaryCodes, root, "");
    }

    /**
     * Metodi luo Huffmanin puurakenteen.
     *
     * @return Luodun Huffmanin puun juurisolmu
     */
    private Node create() {
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
            Node parent = new Node('\0', left.getFrequency() + right.getFrequency(), left, right);

            queue.add(parent);
        }
        return queue.remove();
    }

    /**
     * Metodi läpikäy Huffmanin puun ja kirjoittaa merkkejä vastaavat binäärit
     * talteen taulukkoon.
     *
     * @param codes Merkkijonotaulukko binäärilukujen tallentamiseen
     * @param node Huffmanin puu joka annetaan juurisolmun muodossa parametrina
     * @param s Merkkijono josta rakennetaan tallennettava binääriluku
     */
    private void listBinaryCodes(String[] codes, Node node, String s) {
        if (!node.isLeaf()) {
            listBinaryCodes(codes, node.getLeft(), s +'0');
            listBinaryCodes(codes, node.getRight(), s +'1');
        } else {
            codes[node.getChar()] = s;
        }
    }

    /**
     * Metodi palauttaa luodun Huffmanin puun juuren käsittelyä varten.
     *
     * @return Huffmanin puu
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Metodi palauttaa Huffmanin puusta talteen kirjoitetut merkkejä vastaavat
     * binääriluvut merkkijonotaulukkona.
     *
     * @return Binääriluvut merkkijonotaulukkona
     */
    public String[] getBinaryCodes() {
        return binaryCodes;
    }

}
