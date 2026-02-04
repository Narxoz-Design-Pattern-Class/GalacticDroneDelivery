package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

public class LightDeliveryFactory implements DeliveryFactory {
    private final DroneFactory droneFactory = new LightDroneFactory();

    @Override
    public Drone createDrone(String id) {
        return droneFactory.createDrone(id, 30.0);
    }

    @Override
    public Cargo createCargo() {
        return new Cargo(15.0, "Medical Supplies");
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
