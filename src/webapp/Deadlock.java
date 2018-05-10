package webapp;


public class Deadlock {
    public static void main(String[] args) {

        final Object lock1 = new Object();
        final Object lock2 = new Object();

        startThread(lock1, lock2, "First thread");

        startThread(lock2, lock1, "Second thread");

    }

    private static void startThread(Object lock1, Object lock2, String s) {
        new Thread(() -> {
            try {
                synchronized (lock1) {
                    Thread.sleep(50);
                    synchronized (lock2) {
                        System.out.println(s);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
