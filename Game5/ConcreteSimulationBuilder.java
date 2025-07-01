package Game5;

public class ConcreteSimulationBuilder implements SimulationBuilderInterface {

    // Instance of the Simulation being built
    private Simulation simulation;

    // Constructor initializes a new Simulation object
    public ConcreteSimulationBuilder() {
        simulation = new Simulation();
    }

    // Builds the cannon component of the simulation
    public void buildCannon() {
        simulation.cannon = new Cannon();
    }

    // Builds the cloud components (three clouds)
    public void buildClouds() {
        simulation.clouds = new Cloud[] {
            new Cloud(),
            new Cloud(),
            new Cloud()
        };
    }

    // Builds the sliders for angle, size, and power inputs
    public void buildSliders() {
        simulation.angleSlider = new SliderInput(50, 155, 157, 0, "Angle");
        simulation.sizeSlider = new SliderInput(50, 225, 0, 75, "Size");
        simulation.powerSlider = new SliderInput(50, 295, 150, 0, "Power");
    }

    // Returns the fully built Simulation object
    public Simulation getSimulation() {
        return simulation;
    }
}
