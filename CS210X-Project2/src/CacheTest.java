import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	private class NumberProvider implements DataProvider<Integer, Integer>{
		protected int calls;

		NumberProvider(){
			calls = 0;
		}

		public Integer get(Integer key){
			calls++;
			return key + 5;
		}

		public int getCalls(){
			return calls;
		}
	}

	@Test
	public void leastRecentlyUsedIsCorrect () {
		DataProvider<Integer, Integer> provider = new NumberProvider();
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 5);

		assertEquals(cache.get(5), new Integer(10));			//Store result in cache...
		assertEquals(cache.get(5), new Integer(10)); 			//verify that result is stored in the cache
		assertEquals(((NumberProvider)provider).getCalls(), 1);	//Verify that the cache stepped in and didn't call provider
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 1);

		//Evict key 5 from cache
		for (int i = 6; i < 11; i++)
			assertEquals(cache.get(i), new Integer(i + 5));

		//Verify cache misses & no 5 in the list
		assertFalse(((LRUCache<Integer, Integer>)cache).inCache(5));
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 6);
		
		//10,9,8,7,6
		//Get 5 back in the cache
		cache.get(5);
		assertTrue(((LRUCache<Integer, Integer>)cache).inCache(5));
		assertFalse(((LRUCache<Integer, Integer>)cache).inCache(6));
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 7);
		
		//5,10,9,8,7
		//Splice out 9, move to middle
		cache.get(9);
		assertTrue(((LRUCache<Integer, Integer>)cache).inCache(7)); //Make sure 7 wasn't bumped off
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 7);
	
		//9,5,10,8,7
		//Get 7 from the end and move to beginning
		System.out.println("Getting 7");
		cache.get(7);
		assertTrue(((LRUCache<Integer, Integer>)cache).inCache(8));
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 7);

		//7,9,5,10,8
		//Get 25, bumping off 8 and adding a cache miss
		cache.get(25);
		assertFalse(((LRUCache<Integer, Integer>)cache).inCache(8));
		assertEquals(((LRUCache<Integer, Integer>)cache).getNumMisses(), 8);
		
		for (int i = 5; i < 26; i++)
			if (((LRUCache<Integer, Integer>)cache).inCache(i))
				System.out.printf("%d, ", i);
		System.out.printf("\n");
	}
}
