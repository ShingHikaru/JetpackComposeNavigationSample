import ComposableArchitecture
import Foundation

@Reducer
struct HomeFeature {
    @ObservableState
    struct State: Equatable {
        var count = 0
    }
    
    enum Action {
        case incrementButtonTapped
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .incrementButtonTapped:
                state.count += 1
                return .none
            }
        }
    }
}

@Reducer
struct ProfileFeature {
    @ObservableState
    struct State: Equatable {
        var userName: String = "ShingHikaru"
        var userEmail: String = "fiveh.5h@gmail.com"
    }
    
    enum Action {
        case dummy
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .dummy:
                return .none
            }
        }
    }
}

@Reducer
struct SettingsFeature {
    @ObservableState
    struct State: Equatable {
        var notificationsEnabled: Bool = true
        var darkModeEnabled: Bool = false
    }
    
    enum Action {
        case toggleNotifications(Bool)
        case toggleDarkMode(Bool)
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case let .toggleNotifications(isOn):
                state.notificationsEnabled = isOn
                return .none
            case let .toggleDarkMode(isOn):
                state.darkModeEnabled = isOn
                return .none
            }
        }
    }
}

@Reducer
struct DetailFeature {
    @ObservableState
    struct State: Equatable, Hashable {
        let itemId: String
    }
    
    enum Action {
        case dummy
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case .dummy:
                return .none
            }
        }
    }
}