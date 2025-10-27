import ComposableArchitecture
import SwiftUI

// MARK: - Main App View
struct NavigationSampleApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(
                store: Store(initialState: NavigationFeature.State()) {
                    NavigationFeature()
                }
            )
        }
    }
}

// MARK: - Content View
struct ContentView: View {
    @Bindable var store: StoreOf<NavigationFeature>
    
    var body: some View {
        TabView(selection: $store.selectedTab.sending(\.tabSelected)) {
            NavigationStack(path: $store.scope(state: \.path, action: \.path)) {
                HomeView(
                    store: store.scope(state: \.path[id: 0]?.home ?? .init(), action: \.path[id: 0]?.home)
                )
                .navigationDestination(for: Path.State.self) { state in
                    switch state {
                    case .home:
                        HomeView(
                            store: store.scope(state: \.path[id: 0]?.home ?? .init(), action: \.path[id: 0]?.home)
                        )
                    case .profile:
                        ProfileView(
                            store: store.scope(state: \.path[id: 0]?.profile ?? .init(), action: \.path[id: 0]?.profile)
                        )
                    case .settings:
                        SettingsView(
                            store: store.scope(state: \.path[id: 0]?.settings ?? .init(), action: \.path[id: 0]?.settings)
                        )
                    case let .detail(detailState):
                        DetailView(
                            store: store.scope(state: \.path[id: 0]?.detail ?? detailState, action: \.path[id: 0]?.detail)
                        )
                    }
                }
            }
            .tabItem {
                Image(systemName: "house.fill")
                Text("Home")
            }
            .tag(NavigationFeature.State.Tab.home)
        }
    }
}

// MARK: - Navigation Extensions
extension NavigationFeature.State.Tab {
    var systemImage: String {
        switch self {
        case .home:
            return "house.fill"
        }
    }
}
