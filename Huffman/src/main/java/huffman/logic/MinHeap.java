package huffman.logic;

/**
 * Luokka joka tarjoaa vain tarvittavat kekotoiminnot HuffmanTree-luokkaa
 * varten, ei yleiskäyttöinen minimikeko.
 *
 * @author Raine Rantanen
 */
public class MinHeap {

    private Node[] minHeap;
    private int position;

    /**
     * Luokan konstruktori alustaa kekoa kuvaavan taulukon.
     */
    public MinHeap() {
        this.minHeap = new Node[257];
        position = 0;
    }

    /**
     * Metodi lisää kekoon uuden solmun.
     *
     * @param node Lisättävä solmu
     */
    public void insert(Node node) {
        if (position == 0) {
            minHeap[position + 1] = node;
            position = 2;
        } else {
            minHeap[position++] = node;
            swimUp();
        }
    }

    /**
     * Metodi poistaa keosta vertailuarvoltaan pienimmän solmun.
     *
     * @return Pienin solmu
     */
    public Node remove() {
        Node min = minHeap[1];
        minHeap[1] = minHeap[position - 1];
        minHeap[position - 1] = null;
        position--;
        sinkDown(1);
        return min;
    }

    /**
     * Metodi palauttaa keon solmujen määrän.
     *
     * @return Keon solmujen määrä
     */
    public int size() {
        return position - 1;
    }

    // Yksityinen apumetodi joka järjestää keon solmun lisäyksen jälkeen.
    private void swimUp() {
        int i = position - 1;
        while (i > 1 && isSmaller(i, parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Yksityinen apumetodi joka järjestää keon kun keosta poistetaan juurisolmu.
    private void sinkDown(int i) {
        int min = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < position && isSmaller(left, min)) {
            min = left;
        }
        if (right < position && isSmaller(right, min)) {
            min = right;
        }
        if (min != i) {
            swap(i, min);
            sinkDown(min);
        }
    }

    // Apumetodi joka palauttaa annetun indeksin ylisolmun indeksin
    private int parent(int i) {
        return i / 2;
    }

    // Apumetodi joka palauttaa annetun indeksin vasemman lapsen indeksin
    private int leftChild(int i) {
        return i * 2;
    }

    // Apumetodi joka palauttaa annetun indeksin oikean lapsen indeksin
    private int rightChild(int i) {
        return i * 2 + 1;
    }

    /* Yksityinen metodi joka tarkistaa onko ensimmäisenä parametrina annettu solmu
        vertailuarvoltaan pienempi kuin toisena parametrina annettu solmu.
     */
    private boolean isSmaller(int n, int m) {
        Node node1 = minHeap[n];
        Node node2 = minHeap[m];
        return node1.compareTo(node2) < 0;
    }

    // Yksityinen metodi joka vaihtaa solmut keskenään
    private void swap(int n, int m) {
        Node temp = minHeap[n];
        minHeap[n] = minHeap[m];
        minHeap[m] = temp;
    }
}
