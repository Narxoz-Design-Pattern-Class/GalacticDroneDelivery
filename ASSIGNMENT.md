# Homework: Factory Method & Abstract Factory in Galactic Drone Delivery

## Goal
Design a small console-based Java 17 application that teaches **Factory Method** and
**Abstract Factory** by modeling a drone delivery system with different drone families
and payload types. You will implement two patterns in separate modules (or packages)
and compare their flexibility.

## Learning Outcomes
By the end of this homework, students should be able to:
- Explain when to use Factory Method vs. Abstract Factory.
- Implement both patterns in Java with clean interfaces and tests.
- Extend the system with new product types without modifying core logic.

## Problem Context (Shared for Both Parts)
The company **Galactic Drone Delivery** ships payloads using different drone families.
Each family supports specific payloads and uses its own navigation system.

### Drone Families
- **Orbital** family: optimized for long-range deliveries.
- **Atmospheric** family: optimized for short-range deliveries.

### Payload Types
- **Medical** payload: requires a temperature-controlled container.
- **Commerce** payload: standard container for parcels.

### Core Behaviors
Every delivery should be able to:
1. Create a **Drone**.
2. Create a **Payload**.
3. Perform a `deliver()` action that outputs/logs what is being delivered and how.

---

# Part A — Factory Method (Estimated 60–90 minutes)

## Requirements
1. Create a `DeliveryService` base class with a `createDrone()` factory method.
2. Provide two concrete subclasses:
   - `OrbitalDeliveryService`
   - `AtmosphericDeliveryService`
3. Each subclass decides which `Drone` to create (`OrbitalDrone` or `AtmosphericDrone`).
4. Implement `Drone` as an interface with at least:
   - `String getModel()`
   - `void deliver(Payload payload)`
5. Implement two payloads:
   - `MedicalPayload`
   - `CommercePayload`

## Example Flow (Console Output)
```
[OrbitalDeliveryService] created OrbitalDrone
OrbitalDrone delivering MedicalPayload with temperature control
```

## Extension Task (Optional)
Add a `HeavyCargoPayload` and ensure existing logic does not need to change
outside of adding the new class.

## Tests (JUnit Jupiter)
Write tests that:
- Verify each `DeliveryService` returns the correct `Drone` type.
- Verify the `deliver()` output includes both drone model and payload type.

---

# Part B — Abstract Factory (Estimated 60–90 minutes)

## Requirements
1. Create an `AbstractFactory` interface (e.g., `DeliveryFactory`) with methods:
   - `Drone createDrone()`
   - `Payload createPayload()`
   - `NavigationSystem createNavigationSystem()`
2. Create two concrete factories:
   - `OrbitalDeliveryFactory`
   - `AtmosphericDeliveryFactory`
3. Add a new product family: `NavigationSystem` with two implementations:
   - `OrbitalNavigationSystem`
   - `AtmosphericNavigationSystem`
4. Each factory produces a consistent set of products (drone + payload + navigation).

## Example Flow (Console Output)
```
[OrbitalDeliveryFactory] created OrbitalDrone
[OrbitalDeliveryFactory] created MedicalPayload
[OrbitalDeliveryFactory] created OrbitalNavigationSystem
OrbitalDrone delivering MedicalPayload using OrbitalNavigationSystem
```

## Extension Task (Optional)
Add a new family `DeepSpace` with its own `Drone`, `Payload`, and `NavigationSystem`.
No existing factories should be modified (just add the new factory).

## Tests (JUnit Jupiter)
Write tests that:
- Verify each factory creates objects from the same family.
- Ensure `deliver()` uses the correct navigation system.

---

# Suggested Package Structure
```
src/main/java/edu/narxoz/galactic/
  factorymethod/
    DeliveryService.java
    OrbitalDeliveryService.java
    AtmosphericDeliveryService.java
    drones/
      Drone.java
      OrbitalDrone.java
      AtmosphericDrone.java
    payloads/
      Payload.java
      MedicalPayload.java
      CommercePayload.java

  abstractfactory/
    DeliveryFactory.java
    OrbitalDeliveryFactory.java
    AtmosphericDeliveryFactory.java
    drones/...
    payloads/...
    navigation/...
```

---

# Grading Rubric (20 points)
- 6 pts: Correct Factory Method structure and usage.
- 6 pts: Correct Abstract Factory structure and usage.
- 4 pts: Clean code (naming, small methods, formatting).
- 4 pts: Tests pass and cover the key behaviors.

---

# Deliverables
- Source code in the appropriate packages.
- JUnit test classes under `src/test/java`.
- A short `README.md` explaining how to run the program and tests.

Good luck, and enjoy designing with patterns!
