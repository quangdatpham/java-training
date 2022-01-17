package _12_concurrency._06_memory_consistency_errors;

import java.util.Random;

public class MemoryConsistencyErrors {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new MessageWriter(message)).start();
        new Thread(new MessageReader(message)).start();
    }

    static class Message {
        private String message;
        // private boolean empty;
        private volatile boolean empty; // always read from memory instead of CPU cache

        public Message() {
            this.empty = true;
        }

        public String read() {
            System.out.println("Reading");
            while (empty) {
            }
            empty = true;
            System.out.println("Read");
            return this.message;
        }

        public void write(String message) {
            System.out.println("Writing");
            while (!empty) {
            }
            empty = false;
            this.message = message;
            System.out.println("Written");
        }
    }

    static class MessageWriter implements Runnable {
        private Message message;

        public MessageWriter(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            String[] texts = {"a", "b", "c", "d", "e"};

            Random random = new Random();

            for (String text : texts) {
                message.write(text);
                try {
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            message.write("Finished");
        }
    }

    static class MessageReader implements Runnable {
        private Message message;

        public MessageReader(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
                System.out.println(latestMessage);
                try {
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Read completed!");
        }
    }
}

