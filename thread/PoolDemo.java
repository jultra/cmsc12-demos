package thread;

import java.util.concurrent.*;

public class PoolDemo {
    // A shared variable that the inner class can access
    private int totalResourcesLoaded = 0;

    // 1. The NON-STATIC Inner Class
    // Because it's not static, it belongs to an instance of PoolDemo
    private class GameTask implements Runnable {
        private final int taskId;

        public GameTask(int id) {
            this.taskId = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(">> [" + name + "] Loading Asset #" + taskId);
            
            try {
                Thread.sleep(1500); // Simulate loading time
                
                // Non-static inner classes can access parent variables!
                totalResourcesLoaded++; 
                
            } catch (InterruptedException e) {
                System.out.println("Task interrupted!");
            }

            System.out.println("<< [" + name + "] Finished Asset #" + taskId);
        }
    }

    // 2. The Instance Method that runs the logic
    public void runDemo() {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        System.out.println("--- Starting Asset Loader ---");

        for (int i = 0; i <= 4; i++) {
            pool.execute(new GameTask(i));
        }

        // 3. The "Exit Strategy"
        // Without this, the program stays open in the background!
        pool.shutdown();
        
        System.out.println("--- All tasks submitted to the pool ---");
    }

    public static void main(String[] args) {
        // Just one line in main to kick everything off
        new PoolDemo().runDemo();
    }
}

