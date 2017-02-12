/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DecompressTest {
    Decompress decompress;
    String dir = "/home/fuksi/Dev/TiraLabra/Huffman/";
    
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
            decompress = new Decompress(new File(dir+"testitiedostot/pakkaatesti.txt.hf"), new File(dir+"testitiedostot/purettu.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt. Ongelma luokan Decompress-konstruktorissa.");
        }
    }
    
    @After
    public void tearDown() {
    }

    // Testaa että purettu tiedosto on samankokoinen alkuperäisen pakkaamattoman kanssa
      @Test
    public void getWrittenBitsWorks() {
        assertEquals(3166 * 8 + 12*16 + 8, decompress.getWrittenBits());
    }

    // Testaa että on tiedosto oli pakattu
    @Test
    public void getWrittenBitsIsMoreThanReadBits() {
        assertTrue(decompress.getWrittenBits() > decompress.getReadBits());
    }
}
