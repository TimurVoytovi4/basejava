package webapp;


public class Deadlock {
    public static void main(String[] args) {

        final Object lock1 = new Object();
        final Object lock2 = new Object();


        new Thread(() -> {
            try {
                synchronized (lock1){
                    Thread.sleep(50);
                    synchronized (lock2){
                        System.out.println("First thread");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronized (lock2){
                    Thread.sleep(50);
                    synchronized (lock1){
                        System.out.println("Second thread");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
