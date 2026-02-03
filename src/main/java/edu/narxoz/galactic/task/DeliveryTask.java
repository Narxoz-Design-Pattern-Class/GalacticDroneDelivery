package edu.narxoz.galactic.task;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;

public class DeliveryTask {
    private CelestialBody origin;
    private CelestialBody destination;
    private Cargo cargo;
    private TaskState state;
    private Drone assignedDrone;

    public DeliveryTask(CelestialBody origin, CelestialBody destination, Cargo cargo) {
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.state = TaskState.CREATED;
        this.assignedDrone = null;
    }

    public CelestialBody getOrigin() {
        return origin;
    }

    public CelestialBody getDestination() {
        return destination;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public TaskState getState() {
        return state;
    }

    public Drone getAssignedDrone() {
        return assignedDrone;
    }

    public double estimateTime() {
        if (assignedDrone == null) {
            throw new IllegalStateException("Drone not assigned");
        }
        if (assignedDrone.speedKmPerMin() <= 0) {
            throw new IllegalStateException("Drone speed must be positive");
        }
        return origin.distanceTo(destination) / assignedDrone.speedKmPerMin();
    }

    public void assignTo(Drone drone) {
        if (drone == null) {
            throw new IllegalArgumentException("Drone cannot be null");
        }
        if (state != TaskState.CREATED) {
            throw new IllegalStateException("Task state is not CREATED");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            throw new IllegalStateException("Drone is not IDLE");
        }
        if (cargo.getWeightKg() > drone.getMaxPayloadKg()) {
            throw new IllegalStateException("Cargo weight exceeds drone payload");
        }
        this.assignedDrone = drone;
        this.state = TaskState.ASSIGNED;
        drone.markInFlight();
    }

    public void complete() {
        if (state != TaskState.ASSIGNED) {
            throw new IllegalStateException("Task is not ASSIGNED");
        }
        if (assignedDrone == null) {
            throw new IllegalStateException("No assigned drone");
        }
        if (assignedDrone.getStatus() != DroneStatus.IN_FLIGHT) {
            throw new IllegalStateException("Drone is not IN_FLIGHT");
        }
        assignedDrone.markIdle();
        state = TaskState.DONE;
    }
}
