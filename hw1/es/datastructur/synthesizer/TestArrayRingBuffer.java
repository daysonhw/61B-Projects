package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.*/

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue("AAAA");
        arb.enqueue("FDVDD");
        arb.enqueue("slkdfj");
        arb.enqueue("slkdfj");
        arb.enqueue("slkdfj");
        System.out.println(arb.peek());
        System.out.println(arb.dequeue());
        arb.enqueue("dsdf");
        System.out.println(arb.dequeue());
        arb.enqueue("dsdf");
//        arb.enqueue("dsdf");
        System.out.println(arb.capacity());
        System.out.println(arb.fillCount());
    }
}