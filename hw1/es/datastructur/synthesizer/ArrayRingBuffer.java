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

    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
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
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (last == capacity) {
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
        return capacity;
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
        if (first == capacity - 1) {
            first = 0;
            fillCount --;
            return rb[capacity - 1];
        }
        first += 1;
        fillCount -= 1;
        return rb[first - 1];
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
     * Fill array with item
     */
    @Override
    public void initial(T item) {
        for (int i = 0; i < capacity; i++) {
            rb[i] = item;
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    }
/* Index for the next enqueue. */
// TODO: Remove all comments that say TODO when you're done.