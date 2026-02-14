# BlindKey

**BlindKey** is a cross-platform touch typing trainer designed for users to improve their typing speed and accuracy, with a focus on accessibility. It is specifically tailored to help users practice blind typing efficiently.

## Features

- Typing practice tests
- Real-time speed and accuracy tracking
- Support for multiple languages (English and Russian)
- Randomized text exercises
- Optional removal of punctuation for adaptive training
- Cross-platform desktop support: Windows, macOS, Linux

## Built With

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) – shared code across desktop platforms
- [Jetpack Compose Desktop](https://kotlinlang.org/docs/multiplatform/compose-multiplatform-and-jetpack-compose.html#jetpack-compose-and-composables) – modern UI framework for desktop applications
- [Firebase Firestore](https://firebase.google.com/docs/firestore) – cloud database for storing texts

### Prerequisites

- JDK 17
- Gradle 9+ (wrapper included)
- Git

### Run Locally

```bash
git clone https://github.com/kaniffoll/blind-key.git
cd blind-key
./gradlew run
