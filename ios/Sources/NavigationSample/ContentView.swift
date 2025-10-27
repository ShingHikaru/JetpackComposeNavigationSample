import ComposableArchitecture
import SwiftUI
import Perception

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

struct ContentView: View {
    @Perception.Bindable var store: StoreOf<NavigationFeature>
    
    var body: some View {
        WithPerceptionTracking {
            TabView(selection: $store.selectedTab.sending(\.tabSelected)) {
                NavigationStack {
                    HomeView(
                        store: Store(initialState: HomeFeature.State()) {
                            HomeFeature()
                        }
                    )
                }
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
                .tag(NavigationFeature.State.AppTab.home)
            }
        }
    }
}