package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
	private DNode head;
	private DNode tail;

	public MyDoublyLinkedList(){
		clear();
	}

	public void addFirst(E e){
		DNode newNode = new DNode();
		newNode.element=e;

		if (isEmpty()){
			tail = newNode;
		}
		else{
			head.prev=newNode;
			newNode.next = head;}
		head=newNode;
		size++;
	}


	public E removeFirst(){
		//edge case if isEmpty and edge case if size 1
		if(isEmpty()){throw new NullPointerException();}
		E first= head.element;
		if(this.size==1){
			clear();
			return first;
			}
		head=head.next;
		head.prev=null;
		size--;
		return first;
	}

	public E removeLast(){
		//edge case if isEmpty and edge case if size 1-> clear/ save element
		E last= tail.element;
		tail=tail.prev;
		tail.next=null;
		size--;
		return last;
	}

	public E peekFirst(){
		if(this.head==null){throw new NoSuchElementException();}
		return head.element;
	}
	public E peekLast(){
		if(this.tail==null){throw new NoSuchElementException();}
		return tail.element;
	}

	public boolean equals(Object other){
		if(!(other instanceof MyDoublyLinkedList) || this.size!=((MyDoublyLinkedList<?>) other).size){return false;}

		Iterator<E> iter1= this.iterator();
		Iterator<?> iter2= ((MyDoublyLinkedList<?>) other).iterator();
		if(iter1.hasNext()&&iter2.hasNext()){
			if(!(iter1.next().equals(iter2.next()))){
				return false;}
		}
		return true;}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}
	@Override
	public void add(E e) {
		addLast(e);
	}

	public void addLast(E e){
		DNode newNode = new DNode();
		newNode.element=e;

		if (isEmpty()){
			head = newNode;
		}
		else{
			tail.next=newNode;
			newNode.prev = tail;}
		tail=newNode;
		size++;
	}

	@Override
	public void clear() {
		head=null;
		tail=null;
		size=0;
	}

	@Override
	public E remove() {
		return null;
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}

	public static void main(String[]args){
		MyDoublyLinkedList list = new MyDoublyLinkedList<>();
		list.addLast(2);
		System.out.print(list.peekLast());
	}
}
