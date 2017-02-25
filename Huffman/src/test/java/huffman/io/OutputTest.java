package huffman.io;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        input = new Input(new File("testitiedostot/inputTesti.txt"));
        output = new Output(new File("testitiedostot/outputTesti.txt"));

    }

    @After
    public void tearDown() {
    }

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

        input = new Input(new File("testitiedostot/inputTesti.txt"));
        Input input2 = new Input(new File("testitiedostot/outputTesti.txt"));
      
        assertArrayEquals(input.readFile(), input2.readFile());
    }

    /* Testaa, Outputin Close-toimintoa joka kutsuu tarvittaessa
    yksityistä apumetodia fillByte ja täyttää uloskirjoitettavan tavun
    tarvittaessa nollilla
     */
    @Test
    public void closeFillsByte() throws FileNotFoundException {
        output = new Output(new File("testitiedostot/filledBits.txt"));
        output.writeBit(0);
        for (int i = 0; i < 3; i++) {
            output.writeBit(1);
        }
        output.close();

        // kirjoitetut bitit nyt 0111 0000 (Huom! täytetyt)
        String writtenBits = "01110000";
        StringBuilder readBackBits = new StringBuilder();
        input = new Input(new File("testitiedostot/filledBits.txt"));

        for (int i = 0; i < 8; i++) {
            readBackBits.append(input.readBit());
        }

        assertEquals(readBackBits.toString(), writtenBits);
    }

    @Test
    public void writeCharWritesCharOut() throws FileNotFoundException {
        char c = input.readChar();
        assertEquals(c, 'T');

        output.writeChar(c);
        output.close();

        input = new Input(new File("testitiedostot/outputTesti.txt"));
        c = input.readChar();
        assertEquals(c, 'T');

        assertEquals(8, output.getWrittenBitsTotal());
    }

    /*Testaa, annetun kokonaisluvun kirjoittamista binäärinä. Käytössä kun
    tallennetaan luettujen bittien määrä. Bittejä siis int kokonaisluvun verran
    (32). Testaa samalla yksityistä metodia writeLengthToBits() sekä getWrittenBitsTotal()
     */
    @Test
    public void writeLengthWritesLengthCorrectlyInBits() {
        output.writeLength(100);
        output.close();

        assertEquals(32, output.getWrittenBitsTotal());

        try {
            input = new Input(new File("testitiedostot/outputTesti.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei voitu lukea");
        }
        StringBuilder bits = new StringBuilder();
        int i = 0;
        int bit = input.readBit();

        while (bit != -1) {
            bits.append(bit);
            i++;
            bit = input.readBit();
        }
        assertEquals(bits.toString(), "00000000000000000000000001100100");
    }
}
