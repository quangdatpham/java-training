package _12_concurrency._11_array_blocking_queue;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
        // ReentrantLock bufferLock = new ReentrantLock();
        // List<String> buffer = new Vector<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Thread producer = new Thread(new MyProducer(buffer, ThreadColor.ANSI_BLUE));
        Thread consumer1 = new Thread(new MyConsumer(buffer, ThreadColor.ANSI_RED));
        Thread consumer2 = new Thread(new MyConsumer(buffer, ThreadColor.ANSI_YELLOW));

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
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
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding num " + num);
                buffer.put(num);
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(color + "Adding EOF and exiting...");
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }

                    if (buffer.peek().equals("EOF")) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
