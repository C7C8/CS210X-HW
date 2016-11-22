

/**
 * Java port of a much less stupid class I wrote named "SortedStack" in another language.
 * It's not actually a stack, despite a similar interface. It will sort
 * items upon insertion, so the item you pop off the top will always be
 * the least-value item in the list. Since Java is stupid and doesn't
 * allow for operator overloads, it depends on the interface "Sortable"
 * to function.
 * 
 * @author sourec
 * 
 * @param <T> Type of object that will be stored. This is stupid.
 */
class SortedList<T extends Comparable<T>>{ //Holy s**t this actually works
	Item<T> head;
	int size;
	
	SortedList(){
		head = null;
		size = 0;
	}
	
	T top(){
		if (head == null)
			return null; //Simple enough! Who needs exceptions anyways?
		
		return head.data;
	}
	
	T pop(){
		if (head == null)
			return null;
		
		size--;
		T data = head.data;
		head = head.next;
		if (head != null)
			head.next = null;
		return data;
	}
	
	void push(T data){
		Item<T> item = new Item<T>();
		item.data = data;
		size++;
		
		//Pre-check
		if (head == null){
			head = item;
			return;
		}
		
		for (Item<T> cur = head; cur != null; cur = cur.next){
			if (item.data.compareTo(cur.data) >= 0 && (cur.next == null || cur.next.data.compareTo(item.data) >= 0)){
				item.next = cur.next;
				if (cur.next != null) {
				}
				cur.next = item;
				return;
			}
		}
		
		//GAH! THIS WAS SO CLEAN IN A LANGUAGE WITH OPERATOR OVERLOADS!
	}
	
	void clear(){
		head = null;
		size = 0;
	}
	
	int size(){
		return this.size;
	}
	
	private static class Item<T>{
		T data;
		Item<T> next;
		Item(){
			data = null;
			next = null;
		}
	}
}
