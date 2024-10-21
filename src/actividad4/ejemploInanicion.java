package actividad4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ejemploInanicion {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            Thread lowPriorityThread = new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " got the lock.");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            });
            lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
            lowPriorityThread.setName("LowPriorityThread-" + i);
            lowPriorityThread.start();
        }

        Thread highPriorityThread = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " got the lock.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        highPriorityThread.setName("HighPriorityThread");
        highPriorityThread.start();
    }
}
