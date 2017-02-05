package huffman.huffman.logic;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Raine Rantanen
 */
public class HuffmanTest {

    public HuffmanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        String[] testArray = new String[3];
        Huffman huffman = new Huffman(testArray);
    }

    @After
    public void tearDown() {
    }

}
