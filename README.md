# Bookify 📖

Bookify is a modern Android application built with Jetpack Compose that allows users to search, browse, and discover books using the Open Library API. This project showcases modern Android development best practices, including a clean, multi-layered architecture and a reactive UI.

## ✨ Features

*   **Book Search**: A real-time search bar to find books by title.
*   **Search Suggestions**: Saves previous searches and provides them as suggestions.
*   **Book Details**: View detailed information for each book, including its cover, description, authors, and publish date.
*   **Subject Browsing**: Explore books categorized by subjects.
*   **Clean, Modern UI**: A responsive and intuitive user interface built entirely with Jetpack Compose and Material 3 design principles.

## 📸 Screenshots
(This is a great section to add later. Once you have the app running, take some screenshots of the main screens and add them here to visually showcase your work.)

## 🛠️ Built With

This project is built with a 100% Kotlin stack and leverages the latest Android development tools and libraries.

*   **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android's modern, declarative UI toolkit.
*   **Architecture**: Follows official Google-recommended architecture guidelines.
    *   **UI Layer**: Jetpack Compose, `ViewModel`, `StateFlow`, Unidirectional Data Flow (UDF).
    *   **Domain Layer**: Encapsulates business logic using `UseCases`.
    *   **Data Layer**: `Repository` pattern for abstracting data sources.
*   **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for managing dependencies and decoupling components.
*   **Networking**: [Retrofit 2](https://square.github.io/retrofit/) for making type-safe HTTP requests to the Open Library API.
*   **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) and `Flow` for managing background threads and handling streams of data.
*   **Image Loading**: [Coil](https://coil-kt.github.io/coil/) for loading book covers efficiently.
*   **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) for navigating between screens.
*   **API**: [Open Library API](https://openlibrary.org/developers/api)

## 🏛️ Architecture

Bookify is built with a scalable multi-layered architecture designed to separate concerns and ensure the codebase is maintainable and testable.

*   **Presentation (UI) Layer**: Contains Composable screens, ViewModels, and UI state holders. Handles all UI-related logic and user interactions.
*   **Domain Layer**: Contains the core business logic of the application. It consists of Use Cases that are reusable and independent of the other layers.
*   **Data Layer**: Manages the application's data. It includes Repositories that abstract the data sources (in this case, the remote Open Library API) and DTOs (Data Transfer Objects) for parsing the API responses.

## 🚀 Getting Started

To build and run this project locally, you will need:

1.  Android Studio (latest stable version recommended).
2.  An internet connection to fetch the Gradle dependencies and book data.

Simply clone the repository and open the project in Android Studio.

## 🤝 Contributing
Contributions are welcome! If you find a bug or have a suggestion for a new feature, please open an issue.
## 📄 License
**Bookify** is distributed under the terms of the Apache License (Version 2.0). See the [license](https://github.com/Superpanda23/bookify?tab=Apache-2.0-1-ov-file) for more information.
