#!/bin/bash

echo "Choose the mode of operation:"
echo "1. GUI (requires local environment with Java)"
echo "2. CLI (runs in Docker)"
read -p "Enter your choice (1 or 2): " choice

# Compile the project if needed
echo "Compiling the project with Maven..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "Maven build failed. Please check the output for errors."
    exit 1
fi

if [ "$choice" -eq 1 ]; then
    echo "Launching GUI mode..."
    export APP_MODE=GUI
    java -jar target/BuildingControls-1.0-SNAPSHOT.jar
elif [ "$choice" -eq 2 ]; then
    echo "Launching CLI mode in Docker..."
    export APP_MODE=CLI
    docker build -t building-controls-cli .
    docker run -it -e APP_MODE=CLI building-controls-cli
else
    echo "Invalid choice. Exiting."
    exit 1
fi
