package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

public interface DeliveryFactory {
    Drone createDrone(String id);

    Cargo createCargo();

    CelestialBody createOrigin();

    CelestialBody createDestination();
}
