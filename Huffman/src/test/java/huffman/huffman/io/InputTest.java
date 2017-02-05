package huffman.huffman.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    char[] testArray;

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
        
        testArray = new char[]{'T', 'o', 'i', 'm', 'i', 'i', '\n'};

    }

    @After
    public void tearDown() {
    }

    // Testaa tavujen lukua ja merkkijonon muodostusta
    @Test
    public void readBytesReadsBytesAndReturnsCharArrayCorrectly() throws FileNotFoundException, IOException {
        char[] inputArray = input.readChars();
        Assert.assertArrayEquals(inputArray, testArray);
    }

    // Testaa bittien lukua
    @Test
    public void readBitReadsBitsCorrectly() {
        StringBuilder bits1 = new StringBuilder();
        int i = 0;
        int bitti = input.readBit();

        while (bitti != -1) {
            bits1.append(bitti);
            i++;
            bitti = input.readBit();
        }

        String bits2 = "";
        for (char c : testArray) {
            String byt = Integer.toBinaryString((int) c);
            /*Lisätään luetun tavun alkuun nollia jos tarve
            koska Integer.toBinaryString antaa tavun ilman etunollia
             */

            while (byt.length() < 8) {
                byt = '0' + byt;
            }
            bits2 += byt;

        }
        assertEquals(bits1.toString(), bits2);
    }
}
