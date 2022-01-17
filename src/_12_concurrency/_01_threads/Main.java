package _12_concurrency._01_threads;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from the main thread!");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("__Another_Thread__");
        anotherThread.start();
        // anotherThread.run();

        System.out.println("Hello again from the main thread!");

        // calling start() multiple times
        // anotherThread.start(); // IllegalThreadStateException

        new Thread() {
            public void run() {
                System.out.println("Hello");
            }
        }.start();
    }
}
