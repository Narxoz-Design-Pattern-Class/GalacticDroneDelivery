package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.HeavyDrone;

public class HeavyDroneFactory extends DroneFactory {
    @Override
    protected Drone createDroneInternal(String id, double maxPayloadKg) {
        return new HeavyDrone(id, maxPayloadKg);
    }
}
