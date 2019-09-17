package es.datastructur.synthesizer;

import edu.princeton.cs.introcs.StdAudio;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        buffer.initiate((double)0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        //  Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double temp, temp1;
        temp = buffer.dequeue();
        temp1 = (buffer.peek() + temp) *0.5 * 0.996;
        buffer.enqueue(temp1);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }

    public static void main(String[] args) {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        while(true) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }
}
    // TODO: Remove all comments that say TODO when you're done.