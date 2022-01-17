package _12_concurrency._02_runnable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from main thread!");
        Runnable runnable = new MyRunnable();
        new Thread(runnable).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        }).start();
    }
}
