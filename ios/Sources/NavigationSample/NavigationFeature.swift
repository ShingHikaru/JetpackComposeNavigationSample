import ComposableArchitecture
import SwiftUI

// MARK: - Navigation State
@Reducer
struct NavigationFeature {
    @ObservableState
    struct State: Equatable {
        var selectedTab: Tab = .home
        var path = StackState<Path.State>()
        var lastTabTapTime: Date?
        
        enum Tab: String, CaseIterable {
            case home = "Home"
        }
    }
    
    enum Action {
        case tabSelected(Tab)
        case path(StackAction<Path.State, Path.Action>)
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case let .tabSelected(tab):
                if state.selectedTab == tab {
                    // 二回タップでClear BackStack機能
                    let now = Date()
                    if let lastTap = state.lastTabTapTime,
                       now.timeIntervalSince(lastTap) < 0.5 {
                        // バックスタックをクリア
                        state.path = StackState<Path.State>()
                    }
                    state.lastTabTapTime = now
                } else {
                    state.selectedTab = tab
                    state.lastTabTapTime = nil
                }
                return .none
                
            case .path:
                return .none
            }
        }
        .forEach(\.path, action: \.path)
    }
}

// MARK: - Path Reducer
@Reducer
struct Path {
    @ObservableState
    enum State: Equatable {
        case home(HomeFeature.State = .init())
        case profile(ProfileFeature.State = .init())
        case settings(SettingsFeature.State = .init())
        case detail(DetailFeature.State)
    }
    
    enum Action {
        case home(HomeFeature.Action)
        case profile(ProfileFeature.Action)
        case settings(SettingsFeature.Action)
        case detail(DetailFeature.Action)
    }
    
    var body: some ReducerOf<Self> {
        Scope(state: \.home, action: \.home) {
            HomeFeature()
        }
        Scope(state: \.profile, action: \.profile) {
            ProfileFeature()
        }
        Scope(state: \.settings, action: \.settings) {
            SettingsFeature()
        }
        Scope(state: \.detail, action: \.detail) {
            DetailFeature()
        }
    }
}
