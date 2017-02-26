package huffman.compression;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
            compress = new Compress(new File("testitiedostot/pakkaaTesti.txt"), new File("testitiedostot/pakkaatesti.txt.hf"));
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
    public void getReadBitsReturnsTotalReadBits() {
        assertEquals(3166 * 8 + 12 * 16 + 8, compress.getReadBits());
    }

    @Test
    public void getWrittenBitsIsLessThanReadBits() {
        assertTrue(compress.getWrittenBits() < compress.getReadBits());
    }

    @Test
    public void getCompressionShowsCompression() {
        
        String oCompression=compress.getCompression();
        String nCompression=oCompression.replace(',', '.');
        assertTrue(Double.parseDouble(nCompression) > 0);
    }

}
