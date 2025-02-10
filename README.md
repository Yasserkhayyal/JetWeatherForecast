# JetWeatherForecast

This repository is a fork of the original [JetWeatherForecast](https://github.com/Felix-Kariuki/JetWeather), a weather application built using Jetpack Compose. The fork introduces several enhancements and modifications to improve functionality and user experience.

## Table of Contents

- [Features](#Features)
- [Installation](#Installation)
- [Contributions by yasserkhayyal](#contributions-by-yasserkhayyal)

## Features

The application provides the following features:

- Current Weather
- 7-day weather forecast (available upon upgrading the OpenWeather API's subscription plan)
- Search functionality for cities
- Favorite locations
- Dynamic theming

## Installation

1. **Clone the repository:**
   
   ```bash
   git clone https://github.com/Yasserkhayyal/JetWeatherForecast.git
   cd JetWeatherForecast
   
3. **Add API Key:**
   
    Obtain your API key from OpenWeatherMap and add it to the local.properties file:
    ```
    API_KEY=your_api_key_here
    ```

3. **Build the project:**
   
    Open the project in Android Studio and build the project to install dependencies.
    
4. **Usage**
   
    Launch the application on your Android device or emulator to explore the features.
    
## Contributions by yasserkhayyal

The following changes and enhancements have been implemented by `yasserkhayyal`:
   
   *Feb 9th, 2025*
   * Upgrade AGP to 8.7.3 and Gradle version to 8.9
   * Upgrade Kotlin plugin to 2.0.0
   * Migrate Hilt to use KSP instead of Kapt plugin and remove Kapt
   * Upgrade app theming to use Material 3
   * Add Splash API for Android 12 or higher
   * Re-architect the app to be clean architecture compliant
   * Integrate Data store to save user's selectec location
   * Migrate to OpenWeather API 3.0
   * Add drop down menu choices to the search location bar using `ExposedDropdownMenuBox` and with the help of Direct Geocoding API's response
  
   
   
