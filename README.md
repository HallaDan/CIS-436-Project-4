# Weather Info App

## Description

This Android application uses the [WeatherAPI.com](https://www.weatherapi.com/) service to fetch and display real-time weather data based on a user's zip code. The app includes dynamic features such as updating weather conditions and displaying relevant GIFs based on the temperature range.

### Features
- Fetches real-time weather data using the WeatherAPI.com API.
- Displays current temperature, "feels like" temperature, weather condition, wind speed, and humidity.
- Dynamically updates a GIF based on the observed temperature.
- Allows users to enter a zip code to view the weather for that location.

### How it Works
1. **Zip Code Input**: Users can enter their zip code in the `EditText` field in `fragment_zipcode.xml` and press the "Enter" button to fetch the weather data.
2. **API Call**: The `MainViewModel.kt` class handles fetching weather data from the API using the `getWeatherInfo()` method. This method accepts a zip code as an input and fetches weather details.
3. **LiveData Observation**: The app uses LiveData to manage UI updates. Once weather data is fetched, LiveData variables are updated and observed by the UI components in `GifFragment.kt`.
4. **GIF Updates**: Depending on the temperature, the app dynamically updates the displayed GIF in `GifFragment.kt`. The GIFs change based on temperature ranges (e.g., below 32°F, 50°F-65°F, etc.).
5. **UI Updates**: In the `onViewCreated()` method of `GifFragment.kt`, weather information is dynamically set in the UI using TextViews.

### Temperature Ranges and GIFs:
- **< 32°F**: GIF shows cold weather animation.
- **32°F - 50°F**: GIF shows cool weather animation.
- **51°F - 65°F**: GIF shows mild weather animation.
- **66°F - 79°F**: GIF shows warm weather animation.
- **80°F+**: GIF shows hot weather animation.

## Project Structure
- `MainActivity.kt`: The main activity for the app, which manages the navigation between fragments.
- `MainViewModel.kt`: Handles API requests, processes the weather data, and exposes it via LiveData.
- `ZipcodeFragment.kt`: A fragment that allows the user to input a zip code and fetch weather data.
- `GifFragment.kt`: A fragment that displays the weather information and the appropriate GIF based on the temperature.

## API Usage
The app uses the [WeatherAPI.com](https://www.weatherapi.com/) to fetch current weather information. You will need to include your API key in the `weatherURL` variable in `MainViewModel.kt`.

```kotlin
val weatherURL = "https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=$zipcode&aqi=no"
```

## Screenshots
Below are some example screenshots showing the different fragments:

![Picture7](https://github.com/user-attachments/assets/6b09b596-d66f-44b1-aa93-00896bad33f5)

![Picture8](https://github.com/user-attachments/assets/37f85f2d-28c4-4fc4-b1e2-91990a086fa0)

 

## How to Run
1. Clone this repository.
2. Open the project in Android Studio.
3. Add your WeatherAPI.com API key to the `weatherURL` in `MainViewModel.kt`.
4. Build and run the app on an emulator or physical device.
5. Enter a zip code to fetch and display the weather information.

## Requirements
- Android Studio
- WeatherAPI.com API key
