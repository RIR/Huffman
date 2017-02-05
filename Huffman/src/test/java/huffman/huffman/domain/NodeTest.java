package huffman.huffman.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raine Rantanen
 */
public class NodeTest {

    Node leafNode = new Node('c', 3, null, null);
    Node internalNode = new Node('\0', 0, leafNode, null);

    public NodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // Testaa onko puun solmu lehti, eli sellainen jolla ei ole alisolmuja
    @Test
    public void isLeafReturnsTruefIfNoChildren() {
        assertTrue(leafNode.isLeaf());
    }

    @Test
    public void isLeafReturnsFalseIfHasChildren() {
        assertFalse(internalNode.isLeaf());
    }

    /**
     * Testaa solmujen keskinäistä vertailua joka perustuu merkkien
     * esiintymiskertoihin
     */
    @Test
    public void compareToReturnsCorrectly() {
        int expResult = 3;
        int result = leafNode.compareTo(internalNode);
        assertEquals(expResult, result);
    }

}
