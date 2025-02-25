import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	private class Node<T, U>{
		Node(T key, U data){
			next = null;
			prev = null;
			this.key = key;
			this.data = data;
		}
		
		Node<T, U> next;
		Node<T, U> prev;
		T key;
		U data;
	}
	
	Node<T, U> head;
	Node<T, U> tail;
	DataProvider<T, U> provider;
	final int CAPACITY;
	int numMisses;
	
	HashMap<T, Node<T, U>> data;
	
	public LRUCache (DataProvider<T, U> provider, int capacity) {
		head = null;
		tail = null;
		numMisses = 0;
		this.provider = provider;
		this.CAPACITY = capacity;
		data = new HashMap<T, Node<T, U>>();
	}

	public U get (T key) {
		Node<T, U> result = null;
		if (data.containsKey(key)){
			result = data.get(key);
			
			if (result == head)		//No need to do anything else!
				return result.data; //Also fixes a bug that we'd rather not discuss
			
			//Splice it out!
			if (data.size() > 1){
				if (result.prev != null)
					result.prev.next = result.next;
				if (result.next != null)
					result.next.prev = result.prev;
			}
		}
		else{
			numMisses++;
			result = new Node<T, U>(key, provider.get(key));
			data.put(key, result);
		}
		
		//Edge case where cached number is at end of the list
		if (result == tail && data.size() > 1){
			tail = tail.prev;
			tail.next = null;
		}
		
		//Move result to beginning of list
		if (data.size() > 1) {
			head.prev = result;
			result.prev = null;
			result.next = head;
			head = result;
		}
		else {
			head = result;
			tail = result;
		}
		
		//Remove tail if necessary
		if (data.size() > CAPACITY){
			//No nullptr check since size can't be > 0 and tail be null
			data.remove(tail.key);
			tail.prev.next = null;
			tail = tail.prev;
		}
		
		return result.data;
	}
	
	public int getNumMisses () {
		return numMisses;
	}
	
	protected boolean inCache(T key){
		//Used for testing, should be invisible to the client
		return data.containsKey(key);
	}
}
