package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;

public abstract class DroneFactory {

    public final Drone createDrone(String id, double maxPayloadKg) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Drone id cannot be blank");
        }
        if (maxPayloadKg <= 0) {
            throw new IllegalArgumentException("Max payload must be greater than 0");
        }
        return createDroneInternal(id, maxPayloadKg);
    }

    protected abstract Drone createDroneInternal(String id, double maxPayloadKg);
}
