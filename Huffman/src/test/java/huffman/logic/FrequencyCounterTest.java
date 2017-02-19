package huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrequencyCounterTest {
    FrequencyCounter frequencyCounter;
    
    public FrequencyCounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        frequencyCounter=new FrequencyCounter();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetFrequenciesReturnsCorrectFrequencies() {
       char[] array= {'s','a','t','a','a','1','1','!'};
       int[] freqs=frequencyCounter.getFrequencies(array);
       
        assertEquals(freqs[(int)'s'], 1);
        assertEquals(freqs[(int)'t'], 1);
        assertEquals(freqs[(int)'a'], 3);   
        assertEquals(freqs[(int)'1'], 2);  
        assertEquals(freqs[(int)'!'], 1);  
    }
    
}
