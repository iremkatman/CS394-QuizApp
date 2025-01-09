# Quiz App

## Project Title
**Quiz App** - A dynamic, category-based trivia application.

---

## Project Description
The Quiz App is an interactive trivia platform that allows users to test their knowledge across a wide range of categories, including Sports, Math, History, and more. The app integrates the **Trivia API** for real-time question fetching, ensuring updated content for every quiz attempt. It also incorporates **Firebase** for secure user authentication, global leaderboard rankings, and persistent user data.

With its clean, modern UI following **Material Design principles** and robust backend integration, the Quiz App provides a smooth and engaging experience. From selecting difficulty levels to answering questions and reviewing results, users can immerse themselves in a competitive and educational environment.

---

## Features

### Core Features
- **Category-Based Quizzes**: Select categories such as Sports, Math, History, etc.
- **Difficulty Levels**: Choose from Easy, Medium, or Hard levels before starting the quiz.
- **Dynamic Question Fetching**: Questions are fetched in real-time from the Trivia API.

### User Management
- **Login/Registration**: Users can create accounts and securely log in using Firebase Authentication.
- **Persistent Sessions**: Sessions are maintained, ensuring a seamless experience.

### Advanced UI
- **Material Design Implementation**: Consistent, modern, and adaptive UI components, including RecyclerView with CardView.
- ** Animations**: Transitions between fragments and animations enhance user experience.

---

## Technology Stack

- **Trivia API**: Real-time question fetching for dynamic quiz content.
- **Firebase**: User authentication and secure data storage.
- **Advanced RecyclerView**: Uses ListAdapter with DiffUtil for efficient and smooth list updates.
- **Material Design**: UI components styled with rounded corners, shadows, and modern animations.
![image](https://github.com/user-attachments/assets/7f184519-872f-48d6-ba96-92f43d1d8023)

---

## Steps to Set Up and Run the Project

### 1. Set Up Firebase
- Create a project in the [Firebase Console](https://console.firebase.google.com/).
- Add an Android app to the Firebase project.
- Download the `google-services.json` file and place it in the `app/` directory.

### 2. Configure Trivia API
- Sign up and obtain an API key from the [Trivia API](https://triviaapi.com).
- Add the API key to a secure file or constants file in your project.

### 3. Open in Android Studio
- Open the project in Android Studio.
- Sync Gradle files.

### 4. Build and Run
- Build the project and run it on an emulator or physical device.

---

## Contributions

| Team Member      | Contribution                                |
|------------------|--------------------------------------------|
| **İrem Katman**     | Trivia API Integration, Quiz Fragment       |
| **Can Önder**     | Firebase Authentication, Login/Register Flow |
| **Ayberk Ceylan**     | Advanced RecyclerView, Category Adapter     |
| **Ceren Ayverdi**     | Leaderboard, Animations, Results Fragment   |

---



