package huffman.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raine Rantanen
 */
public class MinHeapTest {

    MinHeap minHeap;

    public MinHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        minHeap = new MinHeap();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insertAndRemoveInsertsAndRemovesCorrectlyWithSingleNode() {
        Node node = new Node('a', 0, null, null);
        minHeap.insert(node);
        assertEquals(node, minHeap.remove());
    }

    @Test
    public void insertAndRemoveInsertsAndRemovesCorrectlyWithMultipleNodes() {
        minHeap.insert(new Node('a', 6, null, null));
        minHeap.insert(new Node('b', 2, null, null));
        minHeap.insert(new Node('c', 5, null, null));
        minHeap.insert(new Node('d', 3, null, null));

        Node minNode = new Node('b', 2, null, null);
        assertTrue(minNode.compareTo(minHeap.remove()) == 0);
    }
}
