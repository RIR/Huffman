package huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
public class HuffmanTreeTest {

    char[] chars;
    FrequencyCounter frequencies;
    HuffmanTree huffmanTree;

    public HuffmanTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        frequencies = new FrequencyCounter();
        huffmanTree = new HuffmanTree(frequencies.getFrequencies(chars));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getRootGetsNodeWithCorrectFrequency() {
        Node root = huffmanTree.getRoot();
        assertEquals(root.getFrequency(), 5);
        assertEquals(root.getChar(), '\0');
    }

    @Test
    public void getRootGetsRootWithCorrectChildren() {
        char[] chars2 = {'a', 'b', 'c', 'c', 'd'};

        FrequencyCounter frequencies2 = new FrequencyCounter();
        HuffmanTree huffmanTree2 = new HuffmanTree(frequencies2.getFrequencies(chars2));

        Node root = huffmanTree2.getRoot();

        assertEquals(root.getFrequency(), 5);
        assertEquals(root.getChar(), '\0');

        assertEquals(root.getLeft().getChar(), 'c');
        assertEquals(root.getLeft().getFrequency(), 2);

        assertEquals(root.getRight().getChar(), '\0');
        assertEquals(root.getRight().getFrequency(), 3);
    }
}
