import ComposableArchitecture
import SwiftUI

// MARK: - Home View
struct HomeView: View {
    @Bindable var store: StoreOf<HomeFeature>
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Text("Home Screen")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                Text("TCA with Reducer Macro")
                    .font(.title2)
                    .foregroundColor(.secondary)
                
                LazyVStack(spacing: 12) {
                    ForEach(store.items, id: \.self) { item in
                        Button(action: {
                            store.send(.itemTapped(item))
                        }) {
                            HStack {
                                Text(item)
                                    .foregroundColor(.primary)
                                Spacer()
                                Image(systemName: "chevron.right")
                                    .foregroundColor(.secondary)
                            }
                            .padding()
                            .background(Color(.systemGray6))
                            .cornerRadius(8)
                        }
                    }
                }
                .padding(.horizontal)
                
                Spacer()
                
                HStack(spacing: 20) {
                    Button("Go to Profile") {
                        store.send(.navigateToProfile)
                    }
                    .buttonStyle(.borderedProminent)
                    
                    Button("Go to Settings") {
                        store.send(.navigateToSettings)
                    }
                    .buttonStyle(.bordered)
                }
                .padding()
            }
            .padding()
            .navigationTitle("Home")
        }
    }
}

// MARK: - Profile View
struct ProfileView: View {
    @Bindable var store: StoreOf<ProfileFeature>
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Text("Profile Screen")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                VStack(alignment: .leading, spacing: 16) {
                    HStack {
                        Text("Name:")
                            .fontWeight(.semibold)
                        Spacer()
                        Text(store.userName)
                    }
                    
                    HStack {
                        Text("Email:")
                            .fontWeight(.semibold)
                        Spacer()
                        Text(store.email)
                    }
                }
                .padding()
                .background(Color(.systemGray6))
                .cornerRadius(12)
                
                Spacer()
                
                Button("Go to Settings") {
                    store.send(.navigateToSettings)
                }
                .buttonStyle(.borderedProminent)
                .padding()
            }
            .padding()
            .navigationTitle("Profile")
        }
    }
}

// MARK: - Settings View
struct SettingsView: View {
    @Bindable var store: StoreOf<SettingsFeature>
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Text("Settings Screen")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                VStack(spacing: 16) {
                    HStack {
                        Text("Notifications")
                        Spacer()
                        Toggle("", isOn: $store.notificationsEnabled.sending(\.toggleNotifications))
                    }
                    
                    HStack {
                        Text("Dark Mode")
                        Spacer()
                        Toggle("", isOn: $store.darkModeEnabled.sending(\.toggleDarkMode))
                    }
                }
                .padding()
                .background(Color(.systemGray6))
                .cornerRadius(12)
                
                Spacer()
            }
            .padding()
            .navigationTitle("Settings")
        }
    }
}

// MARK: - Detail View
struct DetailView: View {
    @Bindable var store: StoreOf<DetailFeature>
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Text(store.itemTitle)
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                Text(store.itemDescription)
                    .font(.body)
                    .multilineTextAlignment(.center)
                    .padding()
                
                Text("Item ID: \(store.itemId)")
                    .font(.caption)
                    .foregroundColor(.secondary)
                
                Spacer()
            }
            .padding()
            .navigationTitle("Detail")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}
