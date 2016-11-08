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
		if (data.containsKey(key))
			result = data.get(key);
		else{
			numMisses++;
			result = new Node<T, U>(key, provider.get(key));
		}
		
		//Splice out the existing node (if present) and move to beginning of list
		if (data.containsKey(key)){
			result.prev.next = result.next;
			result.next.prev = result.prev;
		}
		
		//Move result to beginning of list
		head.prev = result;
		result.prev = null;
		result.next = head;
		head = result;
		
		//Remove tail if necessary
		if (data.size() > CAPACITY){
			tail.prev.next = null;
			data.remove(tail.key);
		}
		
		return result.data;
	}
	
	public int getNumMisses () {
		return numMisses;
	}
}
