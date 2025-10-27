# iOS Navigation Sample with TCA

This iOS project demonstrates navigation management using **The Composable Architecture (TCA)** with **Reducer Macro**, providing a comparison with the Android Jetpack Compose implementations.

## Features

- **The Composable Architecture (TCA)**: Modern state management for SwiftUI
- **Reducer Macro**: Compile-time code generation for reducers
- **TabView**: Single Home tab with double-tap Clear BackStack functionality
- **NavigationStack**: Modern iOS navigation with path-based routing
- **SwiftUI**: Declarative UI framework

## Architecture

### NavigationFeature
- **State**: Manages selected tab, navigation path, and tap timing
- **Action**: Handles tab selection and navigation actions
- **Reducer**: Implements double-tap detection for Clear BackStack

### Path Reducer
- **State**: Enum-based navigation destinations (Home, Profile, Settings, Detail)
- **Action**: Actions for each destination
- **Reducer**: Scoped reducers for each feature

### Features
- **HomeFeature**: Manages home screen state and actions
- **ProfileFeature**: Handles user profile data
- **SettingsFeature**: Manages app settings with toggles
- **DetailFeature**: Displays detailed item information

## Double-Tap Clear BackStack

The app implements the same double-tap functionality as the Android versions:

1. **First tap**: Navigate to Home tab
2. **Second tap** (within 0.5 seconds): Clear navigation stack and return to Home

## Project Structure

```
ios/
├── Package.swift                 # Swift Package Manager configuration
├── Sources/NavigationSample/
│   ├── NavigationFeature.swift   # Main navigation state and reducer
│   ├── Features.swift            # Individual feature reducers
│   ├── Views.swift               # SwiftUI views for each screen
│   └── ContentView.swift         # Main app and content view
└── README.md                     # This file
```

## Requirements

- iOS 16.0+
- Xcode 15.0+
- Swift 5.9+

## Dependencies

- [The Composable Architecture](https://github.com/pointfreeco/swift-composable-architecture): State management and architecture

## Usage

1. Open the project in Xcode
2. Build and run on iOS Simulator or device
3. Test the double-tap functionality on the Home tab

## Comparison with Android

This iOS implementation provides the same functionality as the Android versions but uses:

- **TCA** instead of Jetpack Compose Navigation
- **Reducer Macro** for compile-time safety
- **SwiftUI** instead of Compose UI
- **TabView** instead of Material3 Tabs

The architecture demonstrates how different platforms can achieve similar navigation patterns while leveraging platform-specific technologies and best practices.
