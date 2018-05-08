package webapp;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        thread.join();
    }
}
