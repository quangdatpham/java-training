package _12_concurrency._07_deadlocks_and_wait_notify;

import java.util.Random;

public class WaitNotify {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new MessageWriter(message)).start();
        new Thread(new MessageReader(message)).start();
    }

    static class Message {
        private String message;
        private boolean empty;

        public Message() {
            this.empty = true;
        }

        public synchronized String read() {
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            empty = true;
            notifyAll();
            return this.message;
        }

        public synchronized void write(String message) {
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            empty = false;
            this.message = message;
            notifyAll();
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
        }
    }
}
