package huffman.huffman.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * @author Raine Rantanen
 */
public class InputTest {

    Input input;
    Output output;
    char[] testArray;
    String dir = "/home/fuksi/Dev/TiraLabra/Huffman/";

    public InputTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        input = new Input(new File(dir + "testitiedostot/inputTesti.txt"));
        output = new Output(new File(dir + "testitiedostot/inputTestinOutput.txt"));
        testArray = new char[]{'T', 'o', 'i', 'm', 'i', 'i', '\n'};

    }

    @After
    public void tearDown() {
    }

    // Testaa tavujen (merkkien) lukua ja merkkijonon muodostusta
    @Test(expected = FileNotFoundException.class)
    public void constructorGivesExceptionWhenFileNotFOund() throws FileNotFoundException {
        Input input2 = new Input(new File(dir + "testitiedostot/eiOle.txt"));

    }

    // Testaa tavujen (merkkien) lukua ja merkkijonon muodostusta
    @Test
    public void readFileReadsFileAndReturnsCharArrayCorrectly() throws FileNotFoundException, IOException {
        char[] inputArray = input.readFile();
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

    // Testaa luettujen bittien laskuria lukemalla tavuja
    @Test
    public void getReadBitsTotalReturnsCorrectWhenReadChars() {
        input.readFile();
        assertEquals(56, input.getReadBitsTotal());
    }

    // Testaa luettujen bittien laskuria lukemalla yksittäisiä bittejä
    @Test
    public void getReadBitsTotalReturnsCorrectWhenReadBits() {
        input.readBit();
        assertEquals(1, input.getReadBitsTotal());

        for (int i = 1; i < 56; i++) {
            input.readBit();
            assertEquals(1 + i, input.getReadBitsTotal());
        }
    }

    // Testaa yksittäisten merkkien lukua
    @Test
    public void readCharReadsNextChar() {
        char c = input.readChar();
        assertEquals('T', c);

        c = input.readChar();
        assertEquals('o', c);
    }

    // Testaa yksittäisten merkkien lukua
    @Test
    public void readCharReadsNextCharAfterSingleBitAlreadyRead() {
        input.readBit();

        char c = input.readChar();
        assertNotEquals('T', c);

        assertEquals(9, input.getReadBitsTotal());

        input.readChar();
        assertEquals(17, input.getReadBitsTotal());

    }

    // Testaa Huffmanin puun lukua pakatun tiedoston alusta
    @Test
    public void readHuffmanTreeWorks() {

    }

    /* Testaa pakkaamattoman tiedoston koon lukemista (koko tavuina) pakatusta
    tiedostosta
     */
    @Test
    public void readlengthReadsCorrectLength() {

    }

}
