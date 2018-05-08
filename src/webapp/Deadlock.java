package webapp;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().join();
        final Thread mainThread = Thread.currentThread();
        Thread newThread = new Thread(() -> {
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });newThread.start();

        mainThread.join();
    }
}
