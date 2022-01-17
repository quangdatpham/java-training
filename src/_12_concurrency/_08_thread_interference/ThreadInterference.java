package _12_concurrency._08_thread_interference;

class ThreadInterference {
    public static void main(String[] args) {
        final SharedResource s1 = new SharedResource();
        new Thread() {
            @Override
            public void run() {
                s1.increment();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                s1.decrement();
            }
        }.start();
    }
}

class SharedResource {
    static int counter = 10;
    // private int counter = 10;

    void increment() {
        for (int j = 0; j < 50; j++) {
            counter = counter + 1;
            System.out.println("counter after increment " + counter);
        }
    }

    void decrement() {
        for (int j = 0; j < 50; j++) {
            counter = counter - 1;
            System.out.println("counter after decrement " + counter);
        }
    }
}
