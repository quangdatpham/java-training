package _12_concurrency._09_reentrant_lock_and_unlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        // List<String> buffer = new Vector<>();
        new Thread(new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferLock)).start();
        new Thread(new MyConsumer(buffer, ThreadColor.ANSI_RED, bufferLock)).start();
        new Thread(new MyConsumer(buffer, ThreadColor.ANSI_YELLOW, bufferLock)).start();
    }
}

class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding num " + num);
                bufferLock.lock();
                try {
                    buffer.add(num);
                } finally { // ensure the lock will be released when bad things happen
                    bufferLock.unlock();
                }
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(color + "Adding EOF and exiting...");
        bufferLock.lock();
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }
    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    // @Override
    // public void run() {
    //     while (true) {
    //         bufferLock.lock();
    //         try {
    //             if (buffer.isEmpty()) {
    //                 // bufferLock.unlock();
    //                 continue;
    //             }
    //
    //             if (buffer.get(0).equals("EOF")) {
    //                 System.out.println(color + "Exiting");
    //                 // bufferLock.unlock();
    //                 break;
    //             } else {
    //                 System.out.println(color + "Removed " + buffer.remove(0));
    //             }
    //             // bufferLock.unlock();
    //         } finally {
    //             bufferLock.unlock();
    //         }
    //     }
    // }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            if (bufferLock.tryLock()) { // bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }

                    if (buffer.get(0).equals("EOF")) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            } else {
                counter++;
                // do something else when not be able to hold the lock
            }
        }

        System.out.println("Counter: " + counter);
    }
}
