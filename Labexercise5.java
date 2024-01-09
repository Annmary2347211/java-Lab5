class Appliance {
    private String name;

    public Appliance(String name) {
        this.name = name;
    }

    public synchronized void repair(String technician) {
        System.out.println(technician + " is repairing: " + name);
        // Simulating repair time by sleeping for a random time (1-5 seconds)
        try {
            Thread.sleep((int) (Math.random() * 4000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(technician + " finished repairing: " + name);
    }
}

class TechnicianThread extends Thread {
    private Appliance appliance;
    private String technician;

    public TechnicianThread(Appliance appliance, String technician) {
        this.appliance = appliance;
        this.technician = technician;
    }

    
    public void run() {
        // Change states for demonstration purposes
        appliance.repair(technician);
    }
}

public class Labexercise5 {
    public static void main(String[] args) {
        Appliance fridge = new Appliance("Refrigerator");
        Appliance tv = new Appliance("Television");
        Appliance washer = new Appliance("Washing Machine");

        TechnicianThread technician1 = new TechnicianThread(fridge, "Technician 1");
        TechnicianThread technician2 = new TechnicianThread(tv, "Technician 2");
        TechnicianThread technician3 = new TechnicianThread(washer, "Technician 3");

        // NEW state - Threads are created but not yet started
        System.out.println("Thread State of technician1 before starting: " + technician1.getState());

        technician1.start();
        technician2.start();
        technician3.start();

        // RUNNABLE state - Threads are executing
        System.out.println("Thread State of technician1 while running: " + technician1.getState());

        try {
            // Delay to let the threads execute
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TIMED_WAITING state - Threads are sleeping
        System.out.println("Thread State of technician1 while sleeping: " + technician1.getState());

        try {
            // Wait for threads to finish
            technician1.join();
            technician2.join();
            technician3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TERMINATED state - Threads have finished executing
        System.out.println("Thread State of technician1 after finishing: " + technician1.getState());
    }
}