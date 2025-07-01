package Game5;

public class SimulationDirector {

    // Reference to a builder that follows the SimulationBuilderInterface
    private SimulationBuilderInterface builder;

    // Constructor receives a builder instance (e.g., ConcreteSimulationBuilder)
    public SimulationDirector(SimulationBuilderInterface builder) {
        this.builder = builder;
    }

    // Constructs the simulation by calling the builder's build methods
    public void construct() {
        builder.buildCannon();     // Build cannon
        builder.buildClouds();     // Build clouds
        builder.buildSliders();    // Build sliders
    }

    // Returns the fully constructed Simulation object
    public Simulation getSimulation() {
        return builder.getSimulation();
    }
}
