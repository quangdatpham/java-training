package _12_concurrency._03_interrupt_and_join;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from the " + currentThread().getName());
        try {
            Thread.sleep(3000);
            // Thread.sleep(3000, 100);
            System.out.println("It's time to wake up!");
        } catch (InterruptedException e) {
            System.out.println("Someone interrupt me?");
            return;
        }
    }
}
