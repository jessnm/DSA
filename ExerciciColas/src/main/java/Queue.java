public interface Queue<E> {
        public void push(E e) throws FullQueueException;
        public E pop() throws EmptyQueueException;
        public int size();
}
