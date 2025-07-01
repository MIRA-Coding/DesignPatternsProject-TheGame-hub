package Game5;

public interface SimulationBuilderInterface {

    // Method to build and attach a cannon to the simulation
    void buildCannon();

    // Method to build and attach clouds to the simulation
    void buildClouds();

    // Method to build and attach sliders (angle, size, power) to the simulation
    void buildSliders();

    // Method to return the fully constructed Simulation object
    Simulation getSimulation();
}
