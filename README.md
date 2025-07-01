# 🎮GameHub


[The GameHub Presentation_compressed.pdf](https://github.com/user-attachments/files/20995483/The.GameHub.Presentation_compressed.pdf)

## Project Overview

**GameHub** is an open-source, Java-based desktop application developed as a course project for the 3rd Trimester of 2025. This application serves as a practical demonstration of key software design patterns through a collection of nostalgic, classic games. The project emphasizes modular design, reusability, scalability, and the practical application of object-oriented principles to simplify game logic and enhance overall system flexibility.

**Supervised By:** Dr. Hanadi Mardah

## Features

GameHub provides a unified and intuitive graphical user interface (GUI) built using Java Swing, allowing users to easily select and launch various classic games. Each game is implemented as a standalone module, ensuring a clean and responsive user experience.

### Available Games
* **Tetris:** A classic tile-matching puzzle game.
* **Minesweeper:** A logic-based bomb defusal game.
* **Cross-Circles (Tic-Tac-Toe):** A two-player strategy game.
* **Snake Game:** Players navigate a growing snake across a grid, consuming items while avoiding collisions.
* **Cannon Simulator:** An interactive physics-based game where players launch cannonballs with customizable parameters (angle, size, power).
* this is image for the home page:
  
![image](https://github.com/user-attachments/assets/945dfef3-a249-46c8-ad80-e029d6286fd6)

## Design Patterns Implemented

The GameHub project extensively applies several core software design patterns to improve modularity, clarity, and maintainability.

### 1. Singleton Pattern
* **Purpose:** Ensures that only one instance of a class is created and provides a global access point to that instance.
* **Application:** Used to prevent the same game from being opened multiple times, providing central control over game instances, and reducing memory usage. Each game button launches a Singleton instance.

### 2. State Pattern (Initial Snake Game)
* **Purpose:** Allows an object to alter its behavior when its internal state changes.
* **Application:** Initially implemented in the Snake Game to manage behaviors like the snake's growth when it eats food (reflecting a state change) and random food spawning.
* ![image](https://github.com/user-attachments/assets/4311e8a2-85e9-49b3-80e2-5faa001f5f59)
* ![image](https://github.com/user-attachments/assets/86c84002-5552-46cd-b672-96b565b6e5dd)


### 3. Builder Pattern (Initial Cannon Simulator Game)
* **Purpose:** Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
* **Initial Application:** Used for the Cannon Simulator to construct complex components like the cannon, clouds, and slider input controls.
* **Limitations (Before Refactoring):** The initial implementation led to a tightly coupled design where the `GamePanel` class handled object construction, game logic, and UI rendering, violating the principle of separation of concerns and making modifications difficult.
* this before update games' pattern:
![image](https://github.com/user-attachments/assets/44e46351-66b8-4e32-bb4e-ccfa19f6bb51)
![image](https://github.com/user-attachments/assets/7271911b-2a39-4b3c-9e13-c4a5854b79c1)


### 4. Factory Pattern (Refactored Cannon Simulator)
* **Purpose:** Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
* **Application:** Replaced the Builder pattern in the Cannon Simulator for object creation. It provides simple and fast object creation, centralizes creation logic within `CannonFactory`, and is faster at runtime, which is beneficial for real-time games.
* **Trade-offs:** This refactoring resulted in limited customization, as the `CannonFactory` only supports predefined cannon types and loses the flexibility of customizing angle, power, size, and color dynamically.
* this after update the games' pattern to factory:
* ![image](https://github.com/user-attachments/assets/4526ba75-9641-4ac2-b406-a8605207076b)
* ![image](https://github.com/user-attachments/assets/e8dfac72-f1f3-41ed-88d5-1622576201a3)



### 5. Strategy Pattern (Refactored Snake Game)
* **Purpose:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
* **Application:** Refactored from the State pattern in the Snake Game. Each snake movement direction (up, down, left, right) was extracted into its own class implementing a shared `MoveStrategy` interface.
* **Benefits:** This made the snake's movement logic more modular and flexible, allowing dynamic switching of directions without modifying core logic. It improved code readability, facilitated the addition of new behaviors, and adhered to the Open-Closed Principle.
* **Considerations:** It increases the number of classes, which might add complexity for beginners or be an exaggeration for very simple projects.
* ![image](https://github.com/user-attachments/assets/c491c215-a8e9-40fa-b168-981ae909fe1a)
* ![image](https://github.com/user-attachments/assets/05bd8b86-301f-46d7-9e2d-354e8ffcd5b6)





## Team Members

* Layan Alsayed
* Amirah Alghamwy
* Nujood Alreshi
* Bushra Al-Matrafi

## How to Run

We fork this project form https://github.com/shawngonsalves/DesignPatternsTheGameHub and we push here only the updated/and refactored design patten 
Once you downloud it add it to the original package as you like. 
then edit the import section in any `[]Simulator` class to `Game[]_update` then click **`Run`**

## **🎮🏓🕹️Wish you enjoy the Game**

the following is the presentation slides that could be more usefull: 
[The GameHub Presentation_compressed.pdf](https://github.com/user-attachments/files/20995483/The.GameHub.Presentation_compressed.pdf)
