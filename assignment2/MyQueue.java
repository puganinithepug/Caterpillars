package assignment2;

public class MyQueue<E> {
    private MyDoublyLinkedList<E> store1;
    public MyQueue(){
        this.store1= new MyDoublyLinkedList<E>();
    }
    public void enqueue(E e){store1.addLast(e);}

    public E dequeue(){
        E elem= store1.peekFirst();
        store1.removeFirst();
        return elem;}

    public boolean isEmpty(){return store1.isEmpty();}

    public void clear(){store1.clear();}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyQueue<?>){return store1.equals(((MyQueue<?>)obj).store1);}
        return false;
    }
}
