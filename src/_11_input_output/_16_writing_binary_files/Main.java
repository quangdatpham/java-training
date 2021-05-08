package _11_input_output._16_writing_binary_files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) {
        try (FileOutputStream binFile = new FileOutputStream("data.dat")) {
            FileChannel binChannel = binFile.getChannel();

            byte[] outputBytes = "Hello World!".getBytes();
            // ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
            buffer.put(outputBytes);
            buffer.flip();

            int numBytes = binChannel.write(buffer);
            System.out.println("numBytes written was: " + numBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(123);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("numBytes written was: " + numBytes);

            intBuffer.flip();
            intBuffer.putInt(-9999);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("numBytes written was: " + numBytes);

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();
            outputBytes[0] = 'a';
            outputBytes[1] = 'b';
            buffer.flip();
            long numBytesRead = channel.read(buffer);
            if (buffer.hasArray()) {
                System.out.println("byte buffer = " + new String(buffer.array()));
                System.out.println("outputBytes = " + new String(outputBytes));
            }

            // Relative read
            // intBuffer.flip();
            // numBytesRead = channel.read(intBuffer);
            // intBuffer.flip();
            // System.out.println(intBuffer.getInt());
            // intBuffer.flip();
            // numBytesRead = channel.read(intBuffer);
            // intBuffer.flip();
            // System.out.println(intBuffer.getInt());

            // Absolute read
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt(0)); // buffer's position will not change
            System.out.println(intBuffer.getInt());
            // System.out.println(intBuffer.getInt()); // BufferUnderflowException

            channel.close();
            ra.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
