# Quiz App - Setup and Installation Guide

The **Quiz App** is an interactive trivia platform that allows users to test their knowledge across a wide range of categories, including Sports, Math, History, and more. The app integrates the **Trivia API** for real-time question fetching, ensuring updated content for every quiz attempt. It also incorporates **Firebase** for secure user authentication, global leaderboard rankings, and persistent user data.

With its clean, modern UI following **Material Design principles** and robust backend integration, the Quiz App provides a smooth and engaging experience. From selecting difficulty levels to answering questions and reviewing results, users can immerse themselves in a competitive and educational environment.

---

## Features

### Core Features
- **Category-Based Quizzes**: Select categories such as Sports, Math, History, etc.
- **Difficulty Levels**: Choose from Easy, Medium, or Hard levels before starting the quiz.
- **Dynamic Question Fetching**: Questions are fetched in real-time from the Trivia API.

### User Management
- **Login/Registration**: Users can create accounts and securely log in using Firebase Authentication.

### Advanced UI
- **Material Design Implementation**: Consistent, modern, and adaptive UI components, including RecyclerView with CardView.
- **Animations**: Transitions between fragments and animations enhance user experience.

---

## Technology Stack

- **Trivia API**: Real-time question fetching for dynamic quiz content.
- **Firebase**: User authentication and secure data storage.
- **Advanced RecyclerView**: Uses ListAdapter with DiffUtil for efficient and smooth list updates.
- **Material Design**: UI components styled with rounded corners, shadows, and modern animations.

---

## Repository Link

Clone the project from the GitHub repository:  
[Quiz App GitHub Repository](https://github.com/iremkatman/CS394-QuizApp.git)

---

## Steps to Set Up and Run the Project

### 1. Clone the Repository
```bash
git clone https://github.com/iremkatman/CS394-QuizApp.git
cd CS394-QuizApp
```

### 2. Set Up Firebase
1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Create a new Firebase project or use an existing one.
3. Add an Android app to the Firebase project:
   - Register your app using the package name (found in the `app/build.gradle` file).
   - Download the `google-services.json` file provided by Firebase.
4. Place the `google-services.json` file in the `app/` directory of your project.
5. Enable **Email/Password Authentication** in the Firebase Authentication section.

### 3. Configure Trivia API
1. Sign up and obtain an API key from the [Trivia API](https://triviaapi.com).
2. Add the API key to a secure location in the project (e.g., a constants file).

### 4. Open in Android Studio
1. Open the project folder in [Android Studio](https://developer.android.com/studio).
2. Allow Android Studio to sync the Gradle files automatically.

### 5. Build and Run
1. Connect a physical device or set up an emulator in Android Studio.
2. Click on **Run** or use the `Shift + F10` shortcut to build and deploy the app.

---

## Contributions

| Team Member      | Contribution                                |
|------------------|--------------------------------------------|
| **İrem Katman**     | Trivia API Integration, Quiz Fragment       |
| **Can Önder**     | Firebase Authentication, Login/Register Flow |
| **Ayberk Ceylan**     | Advanced RecyclerView, Category Adapter     |
| **Ceren Ayverdi**     | Leaderboard, Animations, Results Fragment   |

---
