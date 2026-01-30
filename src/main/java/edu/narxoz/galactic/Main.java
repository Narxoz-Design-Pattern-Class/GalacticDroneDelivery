package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Main {
    public static void main(String[] args) {
        // Setup
        Planet earth = new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
        SpaceStation marsStation = new SpaceStation("Mars Station", 100, 200, 5); // Distance ~223.6

        Cargo heavyCargo = new Cargo(50.0, "Heavy Machinery");

        LightDrone lightDrone = new LightDrone("LD-01", 30.0); // Payload 30 < 50
        HeavyDrone heavyDrone = new HeavyDrone("HD-01", 100.0); // Payload 100 > 50

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
