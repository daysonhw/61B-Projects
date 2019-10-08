package es.datastructur.synthesizer;

import edu.princeton.cs.introcs.StdAudio;

import java.util.ArrayList;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

/* Index for the next dequeue or peek. */
public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /**
     * Variable stores oldest data
     */
    private int first;
    /**
     * Variable stores latest data
     */
    private int last;
    /**
     * Variable for the fillCount.
     */
    private int fillCount;
    /**
     * Array for storing the buffer data.
     */
    private T[] rb;
    /**
     * Iterator position
     */
    private int position;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update
        //  last.
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (last == rb.length) {
            last = 0;
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
    }

    /**
     * return size of the buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * return number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first == capacity() - 1) {
            first = 0;
            fillCount --;
            return rb[capacity() - 1];
        }
        first += 1;
        fillCount -= 1;
        return rb[first - 1];

//        if (first == capacity()) {
//            first = 0;
//        }
//        T temp = rb[first];
//        fillCount--;
//        first ++;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }


    /**
     * Initiate Array with item
     */
    @Override
    public void initiate(T item) {
        for (int i = 0; i < rb.length; i++) {
            rb[i] = item;
        }
    }

     private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        private int hasNext;

         public ArrayRingBufferIterator() {
             wizPos = first;
             hasNext = fillCount;
         }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */

        @Override
        public boolean hasNext() {
            return hasNext != 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (wizPos == capacity()) {
                wizPos = 0;
            }
            fillCount--;
            return rb[++wizPos];
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
/* Index for the next enqueue. */
// TODO: Remove all comments that say TODO when you're done.