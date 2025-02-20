# Food Checker Application

## Overview
Food Checker is a JavaFX application that helps users manage and check recipes and food items. It allows users to register, login, store food items, check recipes, and manage their accounts.

## Features
- User Authentication (Register/Login)
- Food Item Management
- Recipe Checking and Management
- Account Management

## Structure

### Main Components
- **Main.java**: The entry point of the application that handles stage control and initialization
- **Account Model**: Manages user account data
- **Recipe Model**: Handles recipe information and management
- **Food Checker UML**: Contains the class diagram and relationships

### Key Classes
- `Main`: Controls the application flow and maintains global variables
- `Account`: Handles user account information
- `Recipe`: Manages recipe data
- `RecipeInfor`: Stores detailed recipe information

### User Interface
The application uses FXML for the user interface with multiple views:
- Registration View
- Login View
- Main View
- Recipe View
- Add Food View

## Getting Started

### Prerequisites
- Java Development Kit (JDK)
- JavaFX SDK
- Any IDE that supports JavaFX (e.g., Eclipse, IntelliJ IDEA)

### Installation
1. Clone the repository
2. Import the project into your IDE
3. Ensure JavaFX is properly configured in your development environment
4. Run `Main.java` to start the application

### Configuration
- The application uses CSV files for data storage:
  - `login.csv`: Stores user login information
  - `food_data.csv`: Stores food items
  - Custom CSV files for individual user data

## Dependencies
- JavaFX for the GUI
- CSV file handling for data storage
- FXMLLoader for view management

## Authors
- Travis Weber
- Khoa Nguyen 
- UTSA CS 3443 - Lab 6
- Fall 2022
