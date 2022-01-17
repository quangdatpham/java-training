package _12_concurrency._10_thread_pools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        // List<String> buffer = new Vector<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Thread producer = new Thread(new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferLock));
        Thread consumer1 = new Thread(new MyConsumer(buffer, ThreadColor.ANSI_RED, bufferLock));
        Thread consumer2 = new Thread(new MyConsumer(buffer, ThreadColor.ANSI_YELLOW, bufferLock));

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() {
                System.out.println(ThreadColor.ANSI_WHITE + "In Callable");
                return "this is a result";
            }
        });
        try {
            System.out.println(ThreadColor.ANSI_WHITE + future.get());
        } catch (ExecutionException e) {
            System.out.println("Something when wrong");
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        executorService.shutdown();
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

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            if (bufferLock.tryLock()) {
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
