package _12_concurrency._05_synchronisation;

public class Main {
    public static void main(String[] args) {
        Countdown countdown1 = new Countdown();
        // Countdown countdown2 = new Countdown();

        CountdownThread thread1 = new CountdownThread(countdown1);
        CountdownThread thread2 = new CountdownThread(countdown1);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();
    }
}

class Countdown {
    private int i; // define i here
    private int z;

    // synchronized a method
    // public synchronized void doCountdown() {
    //     String color;
    //
    //     switch (Thread.currentThread().getName()) {
    //         case "Thread 1":
    //             color = ThreadColor.ANSI_CYAN;
    //             break;
    //         case "Thread 2":
    //             color = ThreadColor.ANSI_PURPLE;
    //             break;
    //         default:
    //             color = ThreadColor.ANSI_GREEN;
    //     }
    //
    //     for (i = 10; i > 0; i--) {
    //         System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
    //     }
    // }

    public void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        // for (z = 10; z > 0; z--) {
        //     System.out.println(color + Thread.currentThread().getName() + ": z = " + z);
        // }
        // System.out.println("--");

        // acquire the object's intrinsic lock
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}


class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}
