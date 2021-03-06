package huffman.io;

import huffman.compression.Compress;
import huffman.logic.Node;
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

public class InputTest {

    Input input;
    File inputFile;
    File outputFile;
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
    public void setUp() throws FileNotFoundException {
        input = new Input(new File("testitiedostot/inputTesti.txt"));
        testArray = new char[]{'T', 'o', 'i', 'm', 'i', 'i', '\n'};

        inputFile = new File("testitiedostot/length.txt");
        outputFile = new File("testitiedostot/lengthPakattu.hf");
    }

    @After
    public void tearDown() {
    }

    @Test(expected = FileNotFoundException.class)
    public void constructorGivesExceptionWhenFileNotFOund() throws FileNotFoundException {
        Input input2 = new Input(new File("testitiedostot/eiOle.txt"));

    }
  
    @Test
    public void readFileReadsFileAndReturnsCharArrayCorrectly() throws FileNotFoundException, IOException {
        char[] inputArray = input.readFile();
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(inputArray[i]);

        }
        Assert.assertArrayEquals(inputArray, testArray);
    }

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

    
    @Test
    public void getReadBitsTotalReturnsCorrectWhenReadChars() {
        input.readFile();
        assertEquals(56, input.getReadBitsTotal());
    }

    @Test
    public void getReadBitsTotalReturnsCorrectWhenReadBits() {
        input.readBit();
        assertEquals(1, input.getReadBitsTotal());

        for (int i = 1; i < 56; i++) {
            input.readBit();
            assertEquals(1 + i, input.getReadBitsTotal());
        }
    }

    @Test
    public void readCharReadsNextChar() {
        char c = input.readChar();
        assertEquals('T', c);

        c = input.readChar();
        assertEquals('o', c);
    }

    @Test
    public void readCharReadsNextCharAfterSingleBitAlreadyRead() {
        input.readBit();

        char c = input.readChar();
        assertNotEquals('T', c);

        assertEquals(9, input.getReadBitsTotal());

        input.readChar();
        assertEquals(17, input.getReadBitsTotal());
    }

//    @Test
//    public void readLengthReadsCorrectLength() throws FileNotFoundException {
//        Compress compress = new Compress(inputFile, outputFile);
//        Input outIn = new Input(new File("testitiedostot/lengthPakattu.hf"));
//        Node Root = outIn.readHuffmanTree();
//
//        assertEquals(inputFile.length(), outIn.readLength());
//        outIn.close();
//    }
}
