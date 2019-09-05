import java.awt.event.ItemEvent;
import java.util.Objects;

public class ArrayDeque<T> implements LLDInterface {
    private T[] items;
    private int size;

    public ArrayDeque(int x){
        items = (T[]) new Object[x];
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items,0,a,0, size);
        items = a;
    }

    @Override
    public void addFirst(Object item) {
        if (size == items.length) {
            resize(2 * size);
        }
        T[] a = (T[]) new Object[size];
        a[0] = (T) item;
        System.arraycopy(items, 0, a, 1, size);
        items = a;
        size++;
    }

    @Override
    public void addLast(Object item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[size] = (T) item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public Object removeFirst() {
        if (size < items.length / 3) {
            resize(items.length / 2);
        }
        T[] a = (T[]) new Object[size];
        System.arraycopy(items, 1, a, 0, size);
        T temp = items[0];
        items = a;
        size--;
        return temp;
    }

    @Override
    public Object removeLast() {
        if (size < items.length / 3) {
            resize(items.length / 2);
        }
        T temp = items[size - 1];
        items[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public Object get(int index) {
        return items[index];
    }
}
