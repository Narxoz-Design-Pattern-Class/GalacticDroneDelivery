package edu.narxoz.galactic.drones;

public abstract class Drone {
    private String id;
    private DroneStatus status;
    private double maxPayloadKg;

    protected Drone(String id, double maxPayloadKg) {
        if (maxPayloadKg <= 0) {
            throw new IllegalArgumentException("Max payload must be greater than 0");
        }
        this.id = id;
        this.maxPayloadKg = maxPayloadKg;
        this.status = DroneStatus.IDLE;
    }

    public String getId() {
        return id;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public double getMaxPayloadKg() {
        return maxPayloadKg;
    }

    public abstract double speedKmPerMin();

    protected void setStatus(DroneStatus status) {
        this.status = status;
    }

    public void markInFlight() {
        if (status != DroneStatus.IDLE) {
            throw new IllegalStateException("Drone must be IDLE to start flight");
        }
        setStatus(DroneStatus.IN_FLIGHT);
    }

    public void markIdle() {
        if (status != DroneStatus.IN_FLIGHT) {
            throw new IllegalStateException("Drone must be IN_FLIGHT to become IDLE");
        }
        setStatus(DroneStatus.IDLE);
    }
}
