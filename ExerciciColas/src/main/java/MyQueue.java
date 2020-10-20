import java.util.ArrayList;

public class MyQueue<E> implements Queue<E>{
    private E[] data;
    private int position;//Primera posició buida.

    public MyQueue(int len){
        this.data = (E[]) new Object[len];
        this.position = 0;
    }

    public void push(E e) throws FullQueueException {
        if(this.IsFull()) throw new FullQueueException();
        this.data[this.position++] =e;
    }

    private boolean IsFull() {

        return this.position >= this.data.length;
    }

    public E pop() throws EmptyQueueException {
        if (this.IsEmpty()) throw new EmptyQueueException();
        E ret = this.data[0];
        //mover toodas las posiciones del vector a la izquierda
        for(int i=0;i<this.position;i++){
            this.data[i] = this.data[i+1];
        }
        this.position--;
        return ret;
    }

    private boolean IsEmpty() {
        return this.position == 0;
    }

    public int size() {
        return this.position;
    }
}
