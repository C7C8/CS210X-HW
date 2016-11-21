

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
class SortedList<T>{
	Item<T> head;
	int size;
	
	SortedList(){
		head = null;
		size = 0;
	}
	
	Sortable<T> top(){
		if (head == null)
			return null; //Simple enough! Who needs exceptions anyways?
		
		return head.data;
	}
	
	Sortable<T> pop(){
		if (head == null)
			return null;
		
		size--;
		Sortable<T> data = head.data;
		head = head.next;
		if (head != null)
			head.next = null;
		return data;
	}
	
	void push(Sortable<T> data){
		Item<T> item = new Item<T>();
		item.data = data;
		size++;
		
		//Pre-check
		if (head == null){
			head = item;
			return;
		}
		
		for (Item<T> cur = head; cur != null; cur = cur.next){
			if (item.data.geq(cur.data) && (cur.next == null || cur.next.data.geq(item.data))){
				//Splice in 'item' AFTER cur
				item.prev = cur;
				item.next = cur.next;
				if (cur.next != null)
					cur.next.prev = item;
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
		Sortable<T> data;
		Item<T> next;
		Item<T> prev;
		
		Item(){
			data = null;
			next = null;
			prev = null;
		}
	}
}
