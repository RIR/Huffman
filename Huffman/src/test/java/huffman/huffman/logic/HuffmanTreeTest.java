package huffman.huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
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
        chars = new char[256];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) i;
        }
        frequencies = new FrequencyCounter();
        huffmanTree = new HuffmanTree(frequencies.getFrequencies(chars));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }
}
