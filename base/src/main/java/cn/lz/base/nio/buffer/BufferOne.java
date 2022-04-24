package cn.lz.base.nio.buffer;

import java.io.FileNotFoundException;
import java.nio.IntBuffer;

/**
 * the core of buffer is limit, position and capacity.
 * the limit is depended on the constructor,
 */
public class BufferOne {
    public static void main(String[] args) throws FileNotFoundException {

        IntBuffer intBuffer = IntBuffer.allocate(3);
        intBuffer.put(10);
        intBuffer.put(10086);
        // clear flip 都会清除mark
        intBuffer.mark();
        intBuffer.put(1);
        // ready to read, make limit = position and position = 0
        intBuffer.flip();
        int i = 0;
        while (intBuffer.hasRemaining()) {
            i++;
            System.out.println(intBuffer.get());
            if (i == 1) {
                intBuffer.mark();
            }
        }
        // make position = 1 (the position that be marked before)
        intBuffer.reset();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
        intBuffer.position(0);
        System.out.println("----");
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
        // ready to read
        intBuffer.clear();
        System.out.println(0 == intBuffer.position() ? "current position is 0" : "current position is not 0");
    }
}
