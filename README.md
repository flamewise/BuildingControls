# Building Controls Application

## Overview
The Building Controls Application is a Java-based project designed to manage buildings, rooms, and their temperature controls. It allows users to interact with the system via a CLI and provides features such as:

- Adding and managing buildings.
- Adding rooms or apartments to buildings.
- Adjusting building-wide temperature setpoints.
- Dynamically updating room temperatures based on heating and cooling states.

## Diagrams

### Domain Diagram
The domain diagram illustrates the key entities and relationships in the application.

![Domain Diagram](diagrams/png/domain_diagram.png)

### Interaction Diagram
The interaction diagram shows how the CLI interacts with the `BuildingController` and other components.

![Interaction Diagram](diagrams/png/interaction_diagram.png)

## Features
- Manage multiple buildings with unique IDs.
- Support for different room types (e.g., apartments, common rooms).
- Dynamic temperature updates using background processes.
- Configurable building-wide temperature setpoints.
- Graceful application lifecycle management.

## Requirements
- Java 17 or higher.
- Maven for build management.