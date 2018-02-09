package assignment2;

public class Deque<Item> implements Iterable<Item> {
private int size = 0;
private Node first = null;
private Node last = null;
	
	private class Node {
		Item item;
		Node next;
	}
	public boolean isEmpty() {return false;}
	public int size() {return 1;}
	public void addFirst(Item item) {}
	public void addLast(Item item) {}
	public Item removeFirst() {}
	public Item removeLast() {}
	public Iterator<Item> iterator() {}	
}
