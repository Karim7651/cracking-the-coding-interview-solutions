import java.util.NoSuchElementException;
//questions => 109
//answers => 238
public class Queue<T> {
    QueueNode<T> first;
    QueueNode<T> last;
    private static class QueueNode<T>{
        public T data;
        public QueueNode<T> next;
        public QueueNode(T data){
            this.data = data;
        }
    }
    public void add(T item){
        QueueNode<T> t = new QueueNode<>(item);
        if(last != null){
            last.next=t;
        }
        last = t;
        if(first == null){
            first = last;
        }
    }

    public T remove(){
        if(first == null)
            throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if(first == null)
            last = null;
        return data;
    }

    public T peek(){
        if(first == null)
            throw new NoSuchElementException();
        return first.data;
    }
    public boolean isEmpty(){
        return first == null;
    }
}
