# BlindKey

**BlindKey** is a cross-platform touch typing trainer designed for users to improve their typing speed and accuracy, with a focus on accessibility. It is specifically tailored to help users practice blind typing efficiently.

## Features

- Typing exercises and practice tests
- Real-time speed and accuracy tracking
- Support for multiple languages (English and Russian)
- Randomized text and punctuation exercises
- Optional removal of punctuation for adaptive training
- Cross-platform desktop support: Windows, macOS, Linux

## Built With

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) – shared code across desktop platforms
- [Jetpack Compose Desktop](https://www.jetbrains.com/compose/) – modern UI framework for desktop applications
- [Firebase Firestore](https://firebase.google.com/docs/firestore) – cloud database for storing user progress and texts

### Prerequisites

- JDK 17
- Gradle 9+ (wrapper included)
- Git

### Run Locally

```bash
git clone https://github.com/kaniffoll/blind-key.git
cd blind-key
./gradlew run
