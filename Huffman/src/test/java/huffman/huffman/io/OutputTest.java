/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.huffman.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void setUp() {
        input = new Input(new File("Testi.txt"));
        output = new Output(new File("outputTesti.txt"));

    }

    @After
    public void tearDown() {
    }

    // Testaa että bitit kirjoitetaan tiedostoon
    @Test
    public void writeBitWritesBitsCorrectly() {
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

}
