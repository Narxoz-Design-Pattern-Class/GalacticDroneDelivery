package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or Drone is null");
        }
        try {
            task.assignTo(drone);
            return new Result(true, null);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new Result(false, e.getMessage());
        }
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        try {
            task.complete();
            return new Result(true, null);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new Result(false, e.getMessage());
        }
    }
}
