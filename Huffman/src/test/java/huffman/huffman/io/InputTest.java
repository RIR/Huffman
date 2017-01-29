package huffman.huffman.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
public class InputTest {

    Input input;

    public InputTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        input = new Input(new File("Testi.txt"));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void readBytesReadsBytesAndReturnsCharArrayCorrectly() throws FileNotFoundException, IOException {
    char[] inputArray=input.readBytes();
    char[] testArray={'T','o','i','m','i','i','\n'};
        Assert.assertArrayEquals(inputArray, testArray);
    }
    
    @Test
    public void readBitReadsBits(){
    
             
        assertEquals("","");
    }
}
