package _11_input_output._18_writing_sequentially;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(String[] args) {
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            byte[] outputBytes = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();

            long str1Pos = 0;
            long int1Pos = outputBytes.length;
            long int2Pos = int1Pos + Integer.BYTES;
            long str2Pos = int2Pos + Integer.BYTES;
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;

            ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);

            binChannel.position(str1Pos);
            binChannel.write(ByteBuffer.wrap(outputBytes));

            buffer.putInt(123);
            buffer.flip();
            binChannel.position(int1Pos);
            binChannel.write(buffer);

            buffer.flip();
            buffer.putInt(-99);
            buffer.flip();
            binChannel.position(int2Pos);
            binChannel.write(buffer);

            binChannel.position(str2Pos);
            binChannel.write(ByteBuffer.wrap(outputBytes2));

            buffer.flip();
            buffer.putInt(9999);
            buffer.flip();
            binChannel.write(buffer);

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(int3Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int3 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(int2Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int2 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(int1Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int1 = " + readBuffer.getInt());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
