package huffman.huffman.compression;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
            compress = new Compress(new File("pakkaaTesti.txt"), new File("yoga.avi.hf"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt, tarkista nimi");
        }
    }

    @After
    public void tearDown() {
    }

    /* Testaa yhteensä luettujen bittien laskuria
    Kaava = Luetut merkit * bittejä merkissä (8) + 8 bittiä tiedoston lopussa olevalle \n merkille
     */
    @Test
    public void getReadBitsWorks() {
        assertEquals(3082 * 8 + 8, compress.getReadBits());
    }

    // Testaa että on tapahtunut tiivistystä.
    @Test
    public void getWrittenBitsIsLessThanReadBits() {
        assertTrue(compress.getWrittenBits() < compress.getReadBits());
    }

    // Testaa että on tapahtunut tiivistystä isohkolla kuvatiedostolla (TIFF 61,3 MB).
    @Test
    public void getWrittenBitsIsLessThanReadBitsWithBigImage() {
        try {
            compress = new Compress(new File("kuva.tif"), new File("kuva.tif.pakattu"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt, tarkista nimi");
        }

        assertTrue(compress.getWrittenBits() < compress.getReadBits());
    }
}
