package huffman.huffman.logic;

import huffman.huffman.main.HuffmanApp;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Raine Rantanen
 */
public class HuffmanAppTest {

    public HuffmanAppTest() {
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
        HuffmanApp huffman = new HuffmanApp(testArray);
    }

    @After
    public void tearDown() {
    }

}
