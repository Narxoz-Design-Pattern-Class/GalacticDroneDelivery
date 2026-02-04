package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

public class HeavyDeliveryFactory implements DeliveryFactory {
    private final DroneFactory droneFactory = new HeavyDroneFactory();

    @Override
    public Drone createDrone(String id) {
        return droneFactory.createDrone(id, 100.0);
    }

    @Override
    public Cargo createCargo() {
        return new Cargo(50.0, "Heavy Machinery");
    }

    @Override
    public CelestialBody createOrigin() {
        return new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
    }

    @Override
    public CelestialBody createDestination() {
        return new SpaceStation("Mars Station", 100, 200, 5);
    }
}
