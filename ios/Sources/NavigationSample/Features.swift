import ComposableArchitecture
import SwiftUI

// MARK: - Home Feature
@Reducer
struct HomeFeature {
    @ObservableState
    struct State: Equatable {
        var items: [String] = ["Item 1", "Item 2", "Item 3", "Item 4", "Item 5"]
    }
    
    enum Action {
        case itemTapped(String)
        case navigateToProfile
        case navigateToSettings
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .itemTapped:
                return .none
            case .navigateToProfile:
                return .none
            case .navigateToSettings:
                return .none
            }
        }
    }
}

// MARK: - Profile Feature
@Reducer
struct ProfileFeature {
    @ObservableState
    struct State: Equatable {
        var userName: String = "User Name"
        var email: String = "user@example.com"
    }
    
    enum Action {
        case editProfile
        case navigateToSettings
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .editProfile:
                return .none
            case .navigateToSettings:
                return .none
            }
        }
    }
}

// MARK: - Settings Feature
@Reducer
struct SettingsFeature {
    @ObservableState
    struct State: Equatable {
        var notificationsEnabled: Bool = true
        var darkModeEnabled: Bool = false
    }
    
    enum Action {
        case toggleNotifications
        case toggleDarkMode
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .toggleNotifications:
                state.notificationsEnabled.toggle()
                return .none
            case .toggleDarkMode:
                state.darkModeEnabled.toggle()
                return .none
            }
        }
    }
}

// MARK: - Detail Feature
@Reducer
struct DetailFeature {
    @ObservableState
    struct State: Equatable {
        var itemId: String
        var itemTitle: String
        var itemDescription: String
        
        init(itemId: String) {
            self.itemId = itemId
            self.itemTitle = "Detail for \(itemId)"
            self.itemDescription = "This is a detailed view for item: \(itemId)"
        }
    }
    
    enum Action {
        case backButtonTapped
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .backButtonTapped:
                return .none
            }
        }
    }
}
