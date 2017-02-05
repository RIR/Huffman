package huffman.huffman.io;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
public class OutputTest {

    Input input;
    Output output;

    public OutputTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        input = new Input(new File("Testi.txt"));
        output = new Output(new File("outputTesti.txt"));

    }

    @After
    public void tearDown() {
    }

    /* Testaa että bitit kirjoitetaan tiedostoon Tässä samalla testaantuu
    writeByte-metodi joka on yksityinen apumetodi
     */
    @Test
    public void writeBitWritesBitsCorrectly() throws FileNotFoundException {
        int i = 0;
        int bitti = input.readBit();

        while (bitti != -1) {
            output.writeBit(bitti);
            i++;
            bitti = input.readBit();
        }
        output.close();

        input = new Input(new File("Testi.txt"));
        Input input2 = new Input(new File("outputTesti.txt"));

        /* Vertaa alkuperäisen luetun tiedoston ja kirjoitetun tiedoston sisältöä
        Ja palauttaa oikein jos ne on samat
         */
        Assert.assertArrayEquals(input.readChars(), input2.readChars());
    }

    /* Testaa, Outputin Close-toimintoa joka kutsuu tarvittaessa
    yksityistä apumetodia fillByte ja täyttää uloskirjoitettavan tavun
    tarvittaessa nollilla
    */
    @Test
    public void closeFillsByte() throws FileNotFoundException {
        output = new Output(new File("filledBits.txt"));
        output.writeBit(0);
        for (int i = 0; i < 3; i++) {
            output.writeBit(1);
        }
        output.close();

        // kirjoitetut bitit nyt 0111 0000 (Huom! täytetyt)
        String writtenBits = "01110000";
        StringBuilder readBackBits = new StringBuilder();
        input = new Input(new File("filledBits.txt"));

        for (int i = 0; i < 8; i++) {
            readBackBits.append(input.readBit());
        }

        Assert.assertEquals(readBackBits.toString(), writtenBits);
    }
}
