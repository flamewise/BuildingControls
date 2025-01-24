# Folder Structure for BuildingControls

This document outlines the folder structure for the Building Controls application, along with reasoning for each directory and file.

## Folder Structure

```plaintext
BuildingControls/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── buildingcontrols/
│   │   │               ├── models/          # Data models for core entities
│   │   │               │   ├── Room.java
│   │   │               │   ├── Apartment.java
│   │   │               │   ├── CommonRoom.java
│   │   │               │   └── Building.java
│   │   │               ├── controllers/     # Business logic and UI controllers
│   │   │               │   ├── CLI.java
│   │   │               │   └── BuildingController.java
│   │   │               ├── services/        # Services for core business logic
│   │   │               │   └── TemperatureService.java
│   │   │               ├── ui/              # GUI components for desktop interface (Java Swing)
│   │   │               │   ├── MainWindow.java
│   │   │               │   ├── AddRoomDialog.java
│   │   │               │   └── TemperaturePanel.java
│   │   │               ├── utils/           # Utility classes for reusable functionality
│   │   │               │   └── RandomTemperatureGenerator.java
│   │   │               └── App.java         # Main entry point
│   │   ├── resources/                       # Configuration and non-code resources
│   │   │   └── application.properties
│   └── test/                                # Unit and integration tests
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── buildingcontrols/
│       │               ├── services/        # Tests for services
│       │               │   └── TemperatureServiceTest.java
│       │               └── controllers/     # Tests for controllers
│       │                   └── BuildingControllerTest.java
│       └── resources/                       # Test-specific configurations
├── .gitignore                               # Git ignore rules for unnecessary files
├── CONTRIBUTING.md                          # Contribution guidelines
├── LICENSE                                  # License information
├── pom.xml                                  # Maven project configuration
└── README.md                                # Project documentation

```
## **Directory and File Reasoning**

### **`src/main/java/com/example/buildingcontrols/`**
This is the main directory for all Java source files. It follows the standard Maven directory structure and organizes the code using the **MVC architecture**:

#### **`models/`**
- Contains core data classes (e.g., `Room`, `Apartment`) representing the entities in the application.
- Keeps data attributes and simple methods (e.g., getters, setters).

#### **`controllers/`**
- Handles business logic and interaction between models and UI.
- Example: `BuildingController` processes user inputs (via CLI or GUI) and updates the models accordingly.

#### **`services/`**
- Encapsulates reusable business logic, such as temperature calculations and scheduling.
- Keeps logic separate from controllers to follow **Separation of Concerns**.

#### **`ui/`**
- Dedicated to GUI components for a desktop-based Swing interface.
- Includes classes for windows, dialogs, and other graphical elements.

#### **`utils/`**
- Holds utility classes or methods that are shared across the application.
- Example: `RandomTemperatureGenerator` initializes random room temperatures.

#### **`App.java`**
- The main entry point of the application.
- Can switch between CLI and GUI depending on user needs or application arguments.
