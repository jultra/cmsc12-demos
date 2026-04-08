// Runnable interface version

package thread;

public class SimpleThreadRace {

    //What should the thread do?
    private class Racer implements Runnable {
        private String name;

        public Racer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Inside " + Thread.currentThread().getName());
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " is at lap " + i);
                
                try {
                    // Slow them down so we can see the "race"
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    System.out.println(name + " crashed!");
                }
            }
            System.out.println("--- " + name + " FINISHED! ---");
        }
    }

    public void startRace() {
        // Create the tasks
        Racer fastCar = new Racer("Motor");
        Racer slowCar = new Racer("E-bike");

        // Create and start the threads
        Thread t1 = new Thread(fastCar);
        Thread t2 = new Thread(slowCar);

        System.out.println("Starting the race...");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        System.out.println("Inside " + Thread.currentThread().getName());
        // new SimpleThreadRace().startRace();
        // Runnable r1 = new SimpleThreadRace().new Racer("Motor");
        // Runnable r2 = new SimpleThreadRace().new Racer("E-bike");
        
        // new Thread(r1).start();
        // new Thread(r2).start();

        // new Thread(new Runnable(){
        //     String name = "Motor";

        //     @Override
        //     public void run() {
        //         System.out.println("Inside " + Thread.currentThread().getName());
        //         for (int i = 1; i <= 5; i++) {
        //             System.out.println(name + " is at lap " + i);
                    
        //             try {
        //                 // Slow them down so we can see the "race"
        //                 Thread.sleep(500); 
        //             } catch (InterruptedException e) {
        //                 System.out.println(name + " crashed!");
        //             }
        //         }
        //         System.out.println("--- " + name + " FINISHED! ---");
        //     }
        // }).start();

        // new Thread(new Runnable(){
        //     String name = "E-bike";

        //     @Override
        //     public void run() {
        //         System.out.println("Inside " + Thread.currentThread().getName());
        //         for (int i = 1; i <= 5; i++) {
        //             System.out.println(name + " is at lap " + i);
                    
        //             try {
        //                 // Slow them down so we can see the "race"
        //                 Thread.sleep(500); 
        //             } catch (InterruptedException e) {
        //                 System.out.println(name + " crashed!");
        //             }
        //         }
        //         System.out.println("--- " + name + " FINISHED! ---");
        //     }
        // }).start();

        new Thread (() -> {
            String name = "Motor";    
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " is at lap " + i);
                
                try {
                    // Slow them down so we can see the "race"
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    System.out.println(name + " crashed!");
                }
            }
            System.out.println("--- " + name + " FINISHED! ---");

        }).start();

        new Thread (() -> {
            String name = "E-bike";    
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " is at lap " + i);
                
                try {
                    // Slow them down so we can see the "race"
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    System.out.println(name + " crashed!");
                }
            }
            System.out.println("--- " + name + " FINISHED! ---");

        }).start();



        System.out.println("The Main Thread is already done!");
    }
}

