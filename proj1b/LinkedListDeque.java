public class LinkedListDeque<T> implements Deque{
    private static class DLLNode<T> {
        private T element;
        private DLLNode<Object> prev;
        private DLLNode<Object> next;

        private DLLNode(T e, DLLNode<Object> p, DLLNode<Object> n) {
            element = e;
            prev = p;
            next = n;
        }
    }

    private DLLNode<Object> sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new DLLNode<>(null, null, null);
        size = 0;
    }

    @Override
    public void addFirst(Object item) {
        size += 1;
        if (sentinel.next == null) {
            sentinel.next = new DLLNode<>(item, sentinel, sentinel.next);
            sentinel.prev = sentinel.next;
            return;
        }
        sentinel.next = new DLLNode<>(item, sentinel,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(Object item) {
        size += 1;
        if (sentinel.prev == null) {
            sentinel.next = new DLLNode<>(item, sentinel, sentinel.next);
            sentinel.prev = sentinel.next;
            return;
        }
        sentinel.prev = new DLLNode<>(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**Assert LinkedList doesn't have a loop*/
    @Override
    public void printDeque() {
        DLLNode p = sentinel.next;
        while (p != null && p != sentinel) {
            System.out.println(p.element);
            p = p.next;
        }
    }

    @Override
    public Object removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        size--;
        Object temp = sentinel.next.element;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp;
    }

    @Override
    public Object removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        size--;
        Object temp = sentinel.prev.element;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp;
    }

    /**
     * get must use iteration, not recursion.
     */
    @Override
    public Object get(int index) {
        DLLNode dllNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (dllNode == null) {
                return null;
            }
            dllNode = dllNode.next;
        }
        return dllNode.element;
    }
}