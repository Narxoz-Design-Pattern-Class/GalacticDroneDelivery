package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.LightDrone;

public class LightDroneFactory extends DroneFactory {
    @Override
    protected Drone createDroneInternal(String id, double maxPayloadKg) {
        return new LightDrone(id, maxPayloadKg);
    }
}
