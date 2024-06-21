# Amphibians App

Amphibians App is a sample Android application built with Kotlin and Jetpack Compose, following clean architecture principles. It retrieves amphibian data from a remote API and displays it in a list with expandable items.

## Features

- Fetches amphibian data from a remote server using Retrofit.
- Displays a list of amphibians with expandable details using Jetpack Compose.
- Handles loading, success, and error states using LiveData and State in ViewModel.
- Demonstrates clean architecture with separation of concerns: data, domain, and presentation layers.

## Tech Stack

- Kotlin
- Jetpack Compose
- Retrofit
- ViewModel (Architecture Components)

## Setup

To run this project locally, follow these steps:

1. **Clone the repository:**

  ```
    git clone https://github.com/dharachodvadiya/Amphibians.git
  ```
     
2. **Open in Android Studio:**

- Open Android Studio.
- Select "Open an existing Android Studio project."
- Navigate to the cloned repository and open it.

3. **Run the app:**

- Connect an Android device or emulator.
- Click on the "Run" button in Android Studio.

## Usage

- Upon launching the app, it will fetch amphibian data from the remote server.
- The app will display a loading indicator while fetching data.
- On successful fetch, it will display a list of amphibians.
- Each amphibian item can be expanded to view its description.

## Architecture

The project follows a clean architecture approach:

- **Data Layer:** Responsible for data handling, including API service, data models, and repository implementation.
- **Domain Layer:** Contains business logic and use cases that interact with repositories to fetch data.
- **Presentation Layer:** Implements UI components using Jetpack Compose. The ViewModel manages UI state and business logic interaction.

## Contributing

Contributions are welcome! If you find any bugs or have suggestions for improvements, please open an issue or a pull request. Follow these guidelines for contributions:

1. Fork the repository and clone it locally.
2. Create a new branch for your feature or bug fix.
3. Make your changes and test thoroughly.
4. Commit your changes with descriptive commit messages.
5. Push your changes to your fork and submit a pull request to the `main` branch of the original repository.

## License

The code in this repository is derived from a Google training course.

Please refer to the terms and conditions of the Google training course for usage rights and restrictions.



