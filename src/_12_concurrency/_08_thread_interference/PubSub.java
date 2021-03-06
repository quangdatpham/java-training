package _12_concurrency._08_thread_interference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PubSub {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        // List<String> buffer = new Vector<>();

        new Thread(new MyProducer(buffer, ThreadColor.ANSI_BLUE)).start();
        // multiple consumers
        new Thread(new MyConsumer(buffer, ThreadColor.ANSI_RED)).start();
        new Thread(new MyConsumer(buffer, ThreadColor.ANSI_YELLOW)).start();
    }
}

class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color) {
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
                synchronized (buffer) {
                    buffer.add(num);
                }
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(color + "Adding EOF and exiting...");
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }

                if (buffer.get(0).equals("EOF")) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }
        }
    }
}
