package _12_concurrency._13_thread_starvation;

public class Main {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 08");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 06");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 04");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_WHITE), "Priority 02");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                    // try {
                    //     if (Thread.currentThread().getName().equals("Priority 10")) {
                    //         Thread.sleep(1);
                    //     } else {
                    //         Thread.sleep(5);
                    //     }
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                }
            }
        }
    }
}
