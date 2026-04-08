// extending the Thread class version

package thread;

public class ThreadRacer extends Thread {
    private String racerName;

    public ThreadRacer(String name) {
        // We can set the thread's name using the super constructor
        super(name); 
        this.racerName = name;
    }

    @Override
    public void run() {
        System.out.println("Inside " + Thread.currentThread().getName());
        for (int i = 1; i <= 5; i++) {
            // We can call getName() directly because we ARE a Thread!
            System.out.println(racerName + " is at lap " + i);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(racerName + " crashed!");
            }
        }
        System.out.println("--- " + getName() + " FINISHED! ---");
    }

    public static void main(String[] args) {
        System.out.println("Inside " + Thread.currentThread().getName());
        // ThreadRacer t1 = new ThreadRacer("Motor");
        // ThreadRacer t2 = new ThreadRacer("E-bike");
        // Thread t1 = new Thread(new Runnable(){
        //     @Override
        //     public void run() {
        //         System.out.println("Inside " + Thread.currentThread().getName());
        //         for (int i = 1; i <= 5; i++) {
        //             // We can call getName() directly because we ARE a Thread!
        //             System.out.println("Motor" + " is at lap " + i);
                    
        //             try {
        //                 Thread.sleep(500);
        //             } catch (InterruptedException e) {
        //                 System.out.println("Motor" + " crashed!");
        //             }
        //         }
        //         System.out.println("--- " + "Motor" + " FINISHED! ---");
        //     }
        // });

        // Thread t2 = new Thread(new Runnable(){
        //     @Override
        //     public void run() {
        //         System.out.println("Inside " + Thread.currentThread().getName());
        //         for (int i = 1; i <= 5; i++) {
        //             // We can call getName() directly because we ARE a Thread!
        //             System.out.println("E-bike" + " is at lap " + i);
                    
        //             try {
        //                 Thread.sleep(500);
        //             } catch (InterruptedException e) {
        //                 System.out.println("E-bike" + " crashed!");
        //             }
        //         }
        //         System.out.println("--- " + "E-bike" + " FINISHED! ---");
        //     }
        // });

        Thread t1 = new Thread(() -> {
            System.out.println("Inside " + Thread.currentThread().getName());
            for (int i = 1; i <= 5; i++) {
                // We can call getName() directly because we ARE a Thread!
                System.out.println("Motor" + " is at lap " + i);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Motor" + " crashed!");
                }
            }
            System.out.println("--- " + "Motor" + " FINISHED! ---");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Inside " + Thread.currentThread().getName());
            for (int i = 1; i <= 5; i++) {
                // We can call getName() directly because we ARE a Thread!
                System.out.println("E-bike" + " is at lap " + i);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("E-bike" + " crashed!");
                }
            }
            System.out.println("--- " + "E-bike" + " FINISHED! ---");
        });

        System.out.println("Starting the race...");
        
        // We just call .start() on our own objects!
        t1.start();
        t2.start();
        
        System.out.println("The Main Thread is already done!");
    }
}
