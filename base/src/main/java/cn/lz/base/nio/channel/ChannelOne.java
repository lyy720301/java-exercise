package cn.lz.base.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class ChannelOne {
    public static void main(String[] args) {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("application.yml", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert aFile != null;
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = 0;
        try {
            bytesRead = inChannel.read(buf);
            String byteReadLog = "byteRead " + bytesRead;
            log.info(byteReadLog);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                aFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write() {
        FileChannel channel = null;
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("nio.txt", "rw");
            channel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            byteBuffer.clear();
            String msg = "hello, nio";
            byteBuffer.put(msg.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert channel != null;
                channel.close();
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
