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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
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
            decompress = new Decompress(new File("kuva.tif.pakattu"), new File("kuva.tif"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt. Ongelma luokan Decompress-konstruktorissa.");
        }
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void hello() {}
}
