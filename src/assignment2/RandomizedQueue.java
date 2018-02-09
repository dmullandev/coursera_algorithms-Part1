package assignment2;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	private int size = 0;
	private Node first = null;
	private Node last = null;
	public RandomizedQueue() {}
	public boolean isEmpty() {
		return first==null;
	}
	public int size() {return 1;}
	public void enqueue(Item item) {}
	public Item dequeue() {}
	public Item sample() {}
	public Iterator<Item> iterator() {}
}
