package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or Drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone is not IDLE");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo weight exceeds drone payload");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task state is not CREATED");
        }

        task.assignTo(drone);
        drone.markInFlight();
        return new Result(true, null);
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task is not ASSIGNED");
        }
        if (task.getAssignedDrone() == null) {
            return new Result(false, "No assigned drone");
        }
        if (task.getAssignedDrone().getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Drone is not IN_FLIGHT");
        }

        task.markDone();
        task.getAssignedDrone().markIdle();
        return new Result(true, null);
    }
}
