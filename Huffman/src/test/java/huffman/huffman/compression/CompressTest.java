package huffman.huffman.compression;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
public class CompressTest {

    Compress compress;

    public CompressTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            compress = new Compress(new File("pakkaaTesti.txt"), new File("pakattuTesti.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt, tarkista nimi");
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }
}
