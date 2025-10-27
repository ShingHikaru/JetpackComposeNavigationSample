import ComposableArchitecture
import SwiftUI

@Reducer
struct NavigationFeature {
    @ObservableState
    struct State: Equatable {
        var selectedTab: AppTab = .home
        
        enum AppTab: String, CaseIterable, Identifiable {
            case home = "Home"
            
            var id: String { self.rawValue }
        }
    }
    
    enum Action {
        case tabSelected(State.AppTab)
    }
    
    var body: some ReducerOf<Self> {
        Reduce { state, action in
            switch action {
            case let .tabSelected(tab):
                state.selectedTab = tab
                return .none
            }
        }
    }
}