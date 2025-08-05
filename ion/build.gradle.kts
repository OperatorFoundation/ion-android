plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "org.operatorfoundation.ion"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // JitPack publishing
    publishing {
        singleVariant("release") {
            // Include sources and javadoc for better developer experience
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// JitPack publishing configuration
afterEvaluate {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                // Set the Maven coordinates
                groupId = "com.github.OperatorFoundation"
                artifactId = "ion-android"
                version = "0.1.0" // Pre-Release

                // Add metadata for better Maven repository experience
                pom {
                    name.set("Ion Android Library")
                    description.set("Android library for the ion protocol, a binary data exchange format.")
                    url.set("https://github.com/OperatorFoundation/ion-android")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("OperatorFoundation")
                            name.set("Operator Foundation")
                            url.set("https://github.com/ion-android")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/OperatorFoundation/ion-android.git")
                        developerConnection.set("scm:git:ssh://github.com:OperatorFoundation/ion-android.git")
                        url.set("https://github.com/OperatorFoundation/ion-android/tree/main")
                    }
                }
            }
        }
    }
}