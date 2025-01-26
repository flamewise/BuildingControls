# Building Controls Application 🏢💻

## Overview
The Building Controls Application is a Java-based project designed to manage buildings, rooms, and their temperature controls. It allows users to interact with the system via either a Graphical User Interface (GUI) or a Command-Line Interface (CLI). The application provides features such as:

- 🏠 Adding and managing buildings.
- 🛏️ Adding rooms or apartments to buildings.
- 🌡️ Adjusting building-wide temperature setpoints.
- 🔄 Dynamically updating room temperatures based on heating and cooling states.

---

## Diagrams 📊

### Domain Diagram
The domain diagram illustrates the key entities and relationships in the application.
![Domain Diagram](diagrams/png/domain_diagram.png)

---

### Interaction Diagram
The interaction diagram shows how the CLI interacts with the `BuildingController` and other components.
![Interaction Diagram](diagrams/png/interaction_diagram.png)

---

## Features ✨

- 🏢 Manage multiple buildings with unique IDs.
- 🛋️ Support for different room types (e.g., apartments, common rooms).
- 🔄 Dynamic temperature updates using background processes.
- 🌡️ Configurable building-wide temperature setpoints.
- ✅ Graceful application lifecycle management.

---

## Requirements ⚙️

- ☕ Java 17 or higher.
- 🛠️ Maven for build management.
- 🐳 Docker (required only for CLI mode).

---

## How to Start the Application 🚀
The application can be run in either GUI mode or CLI mode. Use the provided `run.sh` script to select the desired mode of operation.

### Steps to Start the Application 🔧

1. Open a terminal and navigate to the project directory. 📂
2. Run the `run.sh` script:

```bash
./run.sh
```

3. Select the mode of operation when prompted:
- Enter `1` to launch the GUI mode. 🖥️
- Enter `2` to launch the CLI mode in a Docker container. 🐳

### GUI Mode 🖥️

The GUI mode provides a graphical interface for interacting with the application. Features include:

- A user-friendly interface to manage buildings and rooms.
- Easy navigation through menus to add buildings, adjust temperatures, and view room details.
- Dynamic updates for room heating and cooling states displayed visually.
- Designed for local environments with Java installed.

### CLI Mode 🐳

- Runs in a Docker container, making it portable and easy to deploy.
- After selecting CLI mode, the script will:
- 🏗️ Build the Docker image.
- 🎛️ Run the CLI interface in the container.
- Ensure Docker is installed and running on your system.

---

## Bash Script Details 📜

The `run.sh` script automates the following tasks:

- 🛠️ Compiles the project using Maven.
- 🚀 Runs the application in the selected mode.