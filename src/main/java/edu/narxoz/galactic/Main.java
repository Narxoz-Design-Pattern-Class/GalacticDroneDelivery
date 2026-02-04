package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.factory.DeliveryFactory;
import edu.narxoz.galactic.factory.HeavyDeliveryFactory;
import edu.narxoz.galactic.factory.LightDeliveryFactory;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Main {
    public static void main(String[] args) {
        // Setup
        DeliveryFactory lightFactory = new LightDeliveryFactory();
        DeliveryFactory heavyFactory = new HeavyDeliveryFactory();

        CelestialBody earth = lightFactory.createOrigin();
        CelestialBody marsStation = lightFactory.createDestination(); // Distance ~223.6

        Cargo heavyCargo = heavyFactory.createCargo();

        Drone lightDrone = lightFactory.createDrone("LD-01"); // Payload 30 < 50
        Drone heavyDrone = heavyFactory.createDrone("HD-01"); // Payload 100 > 50

        Dispatcher dispatcher = new Dispatcher();

        // 1. Failure to assign overweight cargo to LightDrone
        System.out.println("--- 1. Attempting to assign heavy cargo to LightDrone ---");
        DeliveryTask task1 = new DeliveryTask(earth, marsStation, heavyCargo);
        Result result1 = dispatcher.assignTask(task1, lightDrone);
        System.out.println("Result: " + result1.ok() + (result1.ok() ? "" : ", Reason: " + result1.reason()));

        // 2. Success with HeavyDrone
        System.out.println("\n--- 2. Attempting to assign heavy cargo to HeavyDrone ---");
        Result result2 = dispatcher.assignTask(task1, heavyDrone);
        System.out.println("Result: " + result2.ok());
        if (result2.ok()) {
            System.out.println("Task Status: " + task1.getState());
            System.out.println("Drone Status: " + heavyDrone.getStatus());
        }

        // 3. Estimated time
        System.out.println("\n--- 3. Estimated Time ---");
        if (task1.getState() == TaskState.ASSIGNED) {
            double time = task1.estimateTime();
            System.out.println("Estimated Time: " + time + " minutes");
        }

        // 4. Completion result
        System.out.println("\n--- 4. Completing Task ---");
        Result result3 = dispatcher.completeTask(task1);
        System.out.println("Completion Result: " + result3.ok());
        System.out.println("Final Task State: " + task1.getState());
        System.out.println("Final Drone Status: " + heavyDrone.getStatus());
    }
}
