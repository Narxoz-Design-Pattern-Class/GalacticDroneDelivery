package edu.narxoz.galactic.task;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

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

    // Package-private setters as per requirements
    void setState(TaskState state) {
        this.state = state;
    }

    void setAssignedDrone(Drone drone) {
        this.assignedDrone = drone;
    }
}
