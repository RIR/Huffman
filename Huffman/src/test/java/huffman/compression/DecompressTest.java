package huffman.compression;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DecompressTest {
    Decompress decompress;
    
    public DecompressTest() {
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
            decompress = new Decompress(new File("testitiedostot/pakkaatesti.txt.hf"), new File("testitiedostot/purettu.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt. Ongelma luokan Decompress-konstruktorissa.");
        }
    }
    
    @After
    public void tearDown() {
    }

      @Test
    public void getWrittenBitsReturnsBitsEqualToOriginalUnpackedFile() {
        File file = new File("testitiedostot/pakkaaTesti.txt");
        assertEquals(file.length()*8, decompress.getWrittenBits());
    }

    @Test
    public void getWrittenBitsIsMoreThanReadBits() {
        assertTrue(decompress.getWrittenBits() > decompress.getReadBits());
    }
}
