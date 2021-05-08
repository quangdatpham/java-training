package _11_input_output._17_chained_put_methods;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(String[] args) {
        // read(ByteBuffer) - reads bytes beginning at the channel's current position, and after the read,
        //                    updates the position accordingly.
        //                    Note that now we're talking about the channel's position, not the byte buffer's position.
        //                    Of course, the bytes will be placed into the buffer starting at its current position.
        // write(ByteBuffer) - the same as read, except it writes. There's one exception.
        //                   If a datasource is opened in APPEND mode, then the first write will take place starting
        //                   at the end of the datasource, rather than at position 0. After the write, the position
        //                   will be updated accordingly.
        // position() - returns the channel's position.
        // position(long) - sets the channel's position to the passed value.
        // truncate(long) - truncates the size of the attached datasource to the passed value.
        // size() - returns the size of the attached datasource

        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            byte[] outputBytes = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer
                    .put(outputBytes)
                    .putInt(999999995)
                    .putInt(-99999)
                    .put(outputBytes2)
                    .putInt(1000)
                    .flip();

            binChannel.write(buffer);

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            readBuffer.flip();

            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("inputString = " + new String(inputString));
            System.out.println("int1 = " + readBuffer.getInt());
            System.out.println("int2 = " + readBuffer.getInt());
            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("inputString2 = " + new String(inputString2));
            System.out.println("int3 = " + readBuffer.getInt());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
