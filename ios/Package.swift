// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "NavigationSample",
    platforms: [
        .iOS(.v16),
        .macOS(.v13)
    ],
    products: [
        .library(
            name: "NavigationSample",
            targets: ["NavigationSample"]),
    ],
    dependencies: [
        .package(url: "https://github.com/pointfreeco/swift-composable-architecture", from: "1.0.0"),
    ],
    targets: [
        .target(
            name: "NavigationSample",
            dependencies: [
                .product(name: "ComposableArchitecture", package: "swift-composable-architecture")
            ]
        ),
        .testTarget(
            name: "NavigationSampleTests",
            dependencies: ["NavigationSample"]),
    ]
)