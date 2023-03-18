package assignment2;

public class MyStack<E> {
    private MyDoublyLinkedList<E> store;
    public MyStack(){
        this.store= new MyDoublyLinkedList<E>();
    }
    public void push(E e){store.addFirst(e);}

    public E pop(){return store.removeFirst();}

    public E peek(){return store.peekFirst();}

    public boolean isEmpty(){return store.isEmpty();}

    public void clear(){store.clear();}

    public int getSize(){return store.getSize();}
}

