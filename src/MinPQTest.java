/**
 * @author Ekta Arora 
 * 
 *         Description of the file: 
 *         	This Java file is the test suit for MinPQ.java file. 
 *         	It tests for:
 *        		- insertion of a new element in the queue
 * 				- detecting minimum element from the queue
 * 				- extracting the minimum element from the queue
 * 
 *        Requirements for the file:
 * 			To execute this file, following two jars must be included in the classpath:
 * 				- junit.jar
 * 				- hamcrest-core.jar
 * 
 */

import org.junit.Test;

import junit.framework.TestCase;

public class MinPQTest extends TestCase {

	MinPQ minPQ = null;

	/**
	 * Test case to test the insertion of a new element into the min queue
	 */
	@Test
	public void testInsertion() {

		// When a new object is created, the maximum size of the queue must be
		// passed to the constructor.
		// If the maximum size passed as parameter is less than equal to zero,
		// the maximum size of the queue is set to zero and queue is not created
		minPQ=new MinPQ(0);
		assertEquals(0, minPQ.getMaxSize());
		
		minPQ=new MinPQ(-5);
		assertEquals(0, minPQ.getMaxSize());
		
		minPQ = new MinPQ(5);
		assertNotNull(minPQ);
		assertEquals(5, minPQ.getMaxSize());
		assertEquals(-1, minPQ.getCurrentSize());
		assertTrue(minPQ.insert(5));
		assertEquals(0, minPQ.getCurrentSize());
		assertTrue(minPQ.insert(2));
		assertEquals(1, minPQ.getCurrentSize());
		assertTrue(minPQ.insert(10));
		assertEquals(2, minPQ.getCurrentSize());
		assertTrue(minPQ.insert(0));
		assertEquals(3, minPQ.getCurrentSize());
		assertFalse(minPQ.insert(Integer.MIN_VALUE));
		assertEquals(3, minPQ.getCurrentSize());
		assertTrue(minPQ.insert(2));
		assertEquals(4, minPQ.getCurrentSize());
		assertFalse(minPQ.insert(3));
		assertEquals(4, minPQ.getCurrentSize());

	}

	/**
	 * Test case to test the minimum element of the min queue. Queue head is
	 * returned, which hold the minimum element of the min queue
	 */
	@Test
	public void testFindMinimum() {
		minPQ = createAndInsert();

		assertEquals(0, minPQ.minimum());
		assertNotSame(2, minPQ.minimum());
	}

	/**
	 * Test case to test the extract the minimum element of the min queue. Queue
	 * head is returned, which hold the minimum element of the min queue and the
	 * size of the min queue is reduced by 1 element.
	 */
	@Test
	public void testGetMinimum() {
		minPQ = createAndInsert();

		assertEquals(0, minPQ.extractMin());
		assertEquals(2, minPQ.extractMin());
		assertEquals(2, minPQ.extractMin());
		assertEquals(2, minPQ.extractMin()); 
		assertNotSame(10, minPQ.extractMin());
		assertEquals(10, minPQ.extractMin());
		assertEquals(-1, minPQ.extractMin());
		assertNotSame(2, minPQ.extractMin());
	}

	/**
	 * Local function to create and return a new object of the min queue with
	 * elements inserted into the queue. This function is used is a helper
	 * function for testFindMinimum() and testGetMinimum() test cases.
	 */
	MinPQ createAndInsert() {
		minPQ = new MinPQ(6);
		assertNotNull(minPQ);
		assertTrue(minPQ.insert(5));
		assertTrue(minPQ.insert(2));
		assertTrue(minPQ.insert(2)); 
		assertTrue(minPQ.insert(10));
		assertTrue(minPQ.insert(0));
		assertTrue(minPQ.insert(2));

		return minPQ;
	}

}
