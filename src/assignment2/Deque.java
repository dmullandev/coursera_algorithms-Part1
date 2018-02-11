

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
private int size = 0;
private Node first = null;
private Node last = null;
	
	public Deque() {
		
	}
	
	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Empty deque");
		}
		Node newNode = new Node();
		newNode.item = item;
		if (isEmpty()) {
			first = newNode;
			last = first;
			first.next = null;
		} else {
			Node oldFirst = first;
			first = newNode;
			oldFirst.previous = first;
			first.next = oldFirst;
		}
		++size;
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Empty deque");
		}
		Node newNode = new Node();
		newNode.item = item;
		if (isEmpty()) {
			first = newNode;
			last = first;
			first.next = null;
		} else {
			Node oldLast = last;
			last = newNode;
			oldLast.next = last;
			last.previous = oldLast;
		}	
		++size;
	}
	
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty deque");
		}
		Node oldFirst;
		if (first == last) {
			oldFirst = first;
			first = null;
			last = null;
			--size;
			return oldFirst.item;
		}
		oldFirst = first;
		first = first.next;
		--size;
		return oldFirst.item;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty deque");
		}
		Node oldLast;
		if (first == last) {
			oldLast = last;
			first = null;
			last = null;
			--size;
			return oldLast.item;
		}
		oldLast = last;
		last = last.previous;
		last.next = null;
		--size;
		return oldLast.item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Implementation not supported");
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException("No more items to return");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
