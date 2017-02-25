package huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    Node leafNode;
    Node leafNode2;
    Node internalNode;

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
        leafNode = new Node('c', 3, null, null);
        leafNode2 = new Node('b', 2, null, null);
        internalNode = new Node('\0', 0, leafNode, leafNode2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isLeafReturnsTruefIfNoChildren() {
        assertTrue(leafNode.isLeaf());
    }

    @Test
    public void isLeafReturnsFalseIfHasChildren() {
        assertFalse(internalNode.isLeaf());
    }

    @Test
    public void compareToReturnsCorrectly() {
        int expResult = 3;
        int result = leafNode.compareTo(internalNode);
        assertEquals(expResult, result);
    }

    @Test
    public void getFrequencyReturnsCorrectFrequency() {
        assertEquals(leafNode.getFrequency(), 3);
        assertEquals(internalNode.getFrequency(), 0);
    }

    @Test
    public void getCharReturnsCorrectCharacter() {
        assertEquals(leafNode.getChar(), 'c');
        assertEquals(internalNode.getChar(), '\0');
    }

    @Test
    public void getLeftReturnsLeftChild() {
        assertEquals(leafNode.getLeft(), null);
        assertEquals(internalNode.getLeft(), leafNode);
    }

    @Test
    public void getRightReturnsRightChild() {
        assertEquals(leafNode.getRight(), null);
        assertEquals(internalNode.getRight(), leafNode2);
    }
}
