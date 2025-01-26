## Folder Structure 📁

The following is the folder structure of the Building Controls Application:

- **.github/**: GitHub configuration and workflows.
- **.vscode/**: VSCode-specific settings.
- **diagrams/**: Contains project diagrams in PNG format.
- **src/**: Source code directory.
  - **main/java/com/example/buildingcontrols/**:
    - **controllers/**: Application controllers such as `BuildingController` and `CLI`.
    - **models/**: Application models including `Apartment`, `Building`, `CommonRoom`, `Room`, etc.
    - **services/**: Background services like `TemperatureManager`.
    - **ui/**: GUI components such as `MainWindow`, `RoomDetailWindow`, and related dialogs.
    - **utils/**: Utility classes such as `RandomTemperatureGenerator.java`.
  - **test/java/com/example/buildingcontrols/**:
    - **controllers/**: Tests for application controllers like `BuildingControllerTest` and `CLITest`.
    - **models/**: Tests for application models.
    - **services/**: Tests for services like `TemperatureManagerTest`.
- **target/**: Compiled bytecode and build artifacts.
- **.gitignore**: Specifies files to ignore in Git.
- **ASSUMPTION.md**: Assumptions made during development.
- **Contribution Guidelines.md**: Contribution guidelines for the project.
- **Dockerfile**: Docker configuration for running the application.
- **FOLDER_STRUCTURE.md**: Detailed documentation of the folder structure.
- **pom.xml**: Maven configuration file.
- **README.md**: Project readme.
- **run.sh**: Shell script for running the application.

```plaintext
BUILDINGCONTROLS/
├── .github/                  # GitHub configuration and workflows
├── .vscode/                  # VSCode-specific settings
├── diagrams/                 # Project diagrams
│   └── png/                  # PNG format diagrams
├── src/                      # Source code directory
│   ├── main/java/com/example/buildingcontrols/
│   │   ├── controllers/      # Application controllers
│   │   │   ├── BuildingController.java
│   │   │   └── CLI.java
│   │   ├── models/           # Application models
│   │   │   ├── Apartment.java
│   │   │   ├── Building.java
│   │   │   ├── CommonRoom.java
│   │   │   ├── CommonRoomType.java
│   │   │   └── Room.java
│   │   ├── services/         # Background services
│   │   │   └── TemperatureManager.java
│   │   ├── ui/               # GUI components
│   │   │   ├── AddRoomDialog.java
│   │   │   ├── MainWindow.java
│   │   │   ├── RoomDetailWindow.java
│   │   │   └── RoomHistoryChart.java
│   │   └── utils/            # Utility classes
│   │       └── Main.java
│   ├── test/java/com/example/buildingcontrols/
│   │   ├── controllers/      # Controller tests
│   │   │   ├── BuildingControllerTest.java
│   │   │   └── CLITest.java
│   │   ├── models/           # Model tests
│   │   └── services/         # Service tests
│   │       └── TemperatureManagerTest.java
├── target/                   # Compiled bytecode and build artifacts
├── .gitignore                # Git ignore file
├── ASSUMPTION.md             # Assumptions made during development
├── Contribution Guidelines.md # Contribution guidelines
├── Dockerfile                # Docker configuration
├── FOLDER_STRUCTURE.md       # Folder structure documentation
├── pom.xml                   # Maven configuration file
├── README.md                 # Project readme
└── run.sh                    # Shell script for running the application
```