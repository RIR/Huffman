package huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharArrayBuilderTest {

    CharArrayBuilder chars;

    public CharArrayBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        chars = new CharArrayBuilder();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addCharAddsCharAndGetCharsGetChars() {
        chars.add('ä');
        char[] charsCopy = chars.getChars();
        for (int i = 0; i < charsCopy.length; i++) {
            System.out.print(charsCopy[i]);

        }
        assertEquals(chars.getChars().length, 1);
        assertEquals(charsCopy[0], 'ä');

        for (int i = 0; i < 10; i++) {
            char c = (char) i;
            chars.add(c);
        }
        assertEquals(chars.getChars().length, 11);
    }
}
