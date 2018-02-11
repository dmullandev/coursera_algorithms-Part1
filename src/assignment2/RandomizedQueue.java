

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] arr;
	private int size;
	private int first;
	private int last;

	public RandomizedQueue() {
		arr = (Item[]) new Object[2];
		size = 0;
		first = 0;
		last = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Null item");
		}
		if (size == (arr.length / 2)) {
			Item[] temp = (Item[]) new Object[arr.length * 2];
			for (int i = 0; i <= size; i++) {
				temp[i] = arr[first + i % arr.length];
			}
			arr = temp;
			first = 0;
			last = size;
		}
		arr[last++] = item;
		if (last == arr.length) {
			last = 0;
		}
		++size;
	}
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty RandomizedQueue");
		}
		if (size == (arr.length / 4)) { //resize
			Item[] temp = (Item[]) new Object[arr.length / 2];
			for(int i = 0;i <= size;i++) {
				temp[i] = arr[first + i % arr.length];
			}
			arr = temp;
			first = 0;
			last = size;
		}
		Item temp;
		
		if (size == 1) { // one item
			temp = arr[first];
			first = 0;
			last = 0;
			size = 0;
			return temp;
		}
		
		int rnd = StdRandom.uniform(size);
		
		if ((first + rnd) > arr.length) { // queue wraps?
			rnd = rnd - (arr.length - first);
			temp = arr[rnd];
			if (rnd == last-1) { // if last item randomed
				arr[last-1] = null;
				last--;
			} else {
				arr[rnd] = arr[last-1];
				arr[last-1] = null;
				last--;
			}
		} else {
			temp = arr[first + rnd];
			arr[first + rnd] = arr[last-1];
			arr[last-1] = null;
			last--;
		}
		--size;
		return temp;
	}
	
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty RandomizedQueue");
		}
		int rnd = StdRandom.uniform(size);
		if ((first + rnd) > arr.length) {
			rnd = rnd - (arr.length - first);
		} else {
			rnd = first + rnd;
		}
		return arr[rnd];
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private int current = first;
		public boolean hasNext() {
			if (size != 0) {
				return true;
			}
			return false;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Not a support implementation");
		}
		
		public Item next() {
			if(!hasNext()) {
				throw new java.util.NoSuchElementException("All items visited");
			}
			Item temp;
			int rnd = StdRandom.uniform(size);
			if (size == 1) { //one item
				temp = arr[first];
				first = 0;
				last = 0;
				size = 0;
				return temp;
			}
			if ((first + rnd) > arr.length) { //queue wraps?
				rnd = rnd - (arr.length - first);
				temp = arr[rnd];
				if (rnd == last-1) { //if last item randomed
					arr[last-1] = null;
					last--;
				}else {
					arr[rnd] = arr[last-1];
					arr[last-1] = null;
					last--;
				}
			}else {
				temp = arr[first + rnd];
				arr[first + rnd] = arr[last-1];
				arr[last-1] = null;
				last--;
			}
			--size;
			return temp;

		}
	}
}
