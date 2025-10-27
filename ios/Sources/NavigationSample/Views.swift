import ComposableArchitecture
import SwiftUI
import Perception

// MARK: - Home View
struct HomeView: View {
    @Perception.Bindable var store: StoreOf<HomeFeature>
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Home Screen")
                .font(.largeTitle)
                .fontWeight(.bold)
            
            Text("Welcome to the centralized navigation demo!")
                .font(.body)
                .multilineTextAlignment(.center)
                .foregroundColor(.secondary)
            
            VStack(spacing: 16) {
                Button(action: {
                    store.send(.incrementButtonTapped)
                }) {
                    HStack {
                        Image(systemName: "plus.circle.fill")
                        Text("Increment Counter")
                    }
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.blue)
                    .cornerRadius(12)
                }
                
                Text("Count: \(store.count)")
                    .font(.title2)
                    .fontWeight(.semibold)
            }
            
            Spacer()
        }
        .padding()
        .navigationTitle("Home")
    }
}

// MARK: - Profile View
struct ProfileView: View {
    @Perception.Bindable var store: StoreOf<ProfileFeature>
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Profile Screen")
                .font(.largeTitle)
                .fontWeight(.bold)
            
            VStack(spacing: 16) {
                HStack {
                    Text("Name:")
                        .fontWeight(.medium)
                    Spacer()
                    Text(store.userName)
                        .foregroundColor(.secondary)
                }
                
                HStack {
                    Text("Email:")
                        .fontWeight(.medium)
                    Spacer()
                    Text(store.userEmail)
                        .foregroundColor(.secondary)
                }
            }
            .padding()
            .background(Color.gray.opacity(0.1))
            .cornerRadius(12)
            
            Spacer()
        }
        .padding()
        .navigationTitle("Profile")
    }
}

// MARK: - Settings View
struct SettingsView: View {
    @Perception.Bindable var store: StoreOf<SettingsFeature>
    
    var body: some View {
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
            .background(Color.gray.opacity(0.1))
            .cornerRadius(12)
            
            Spacer()
        }
        .padding()
        .navigationTitle("Settings")
    }
}

// MARK: - Detail View
struct DetailView: View {
    @Perception.Bindable var store: StoreOf<DetailFeature>
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Detail Screen")
                .font(.largeTitle)
                .fontWeight(.bold)
            
            Text("Item ID: \(store.itemId)")
                .font(.title2)
                .foregroundColor(.secondary)
            
            Spacer()
        }
        .padding()
        .navigationTitle("Detail")
    }
}