package _11_input_output._19_copy_files_and_pipes_with_threads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Main {

    public static void main(String[] args) {
        // try {
        //     RandomAccessFile sourceFile = new RandomAccessFile("data.dat", "rwd");
        //     FileChannel sourceChannel = sourceFile.getChannel();
        //
        //     sourceChannel.position(0); // absolutely copy from index 0
        //
        //     RandomAccessFile destFile = new RandomAccessFile("data_copy.dat", "rw");
        //     FileChannel destFile = destFile.getChannel();
        //     // long numTransferred = destFile.transferFrom(sourceChannel, 0, sourceChannel.size());
        //     long numTransferred = sourceChannel.transferTo(0, sourceChannel.size(), destFile);
        //     System.out.println("Num transferred = " + numTransferred);
        //
        //     sourceChannel.close();
        //     sourceFile.close();
        //     destFile.close();
        //     destFile.close();
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        try {
            Pipe pipe = Pipe.open();
            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i = 0; i < 10; i++) {
                            String currentTime = "The time is " + System.currentTimeMillis();

                            buffer.put(currentTime.getBytes());
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }

                            buffer.clear();
                            Thread.sleep(500);
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i = 0; i < 10; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            buffer.clear();
                            System.out.println(i + ". Reader Thread:\n\t" + new String(timeString));
                            Thread.sleep(500);
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            //               ________________________________
            //               |             Pipe             |
            //   Thread A -> | SinkChannel -> SourceChannel | -> Thread B
            //               |______________________________|

            new Thread(writer).start();
            new Thread(reader).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
