import com.jml.random.users.Config
import com.jml.random.users.Libs


//TODO READ https://kotlinlang.org/docs/reference/using-gradle.html
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {

    compileSdkVersion(Config.AndroidSdk.COMPILE)

    defaultConfig {
        minSdkVersion(Config.AndroidSdk.MIN)
        targetSdkVersion(Config.AndroidSdk.TARGET)

        applicationId = "com.jml.random.users"
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    packagingOptions.excludes = mutableSetOf(
        "META-INF/main.kotlin_module",
        "META-INF/LICENSE",
        "META-INF/NOTICE.txt",
        "META-INF/NOTICE",
        "META-INF/ASL2.0",
        "oMETA-INF/MANIFEST.MF"
    )
}

dependencies {

    implementation(project(":common"))
    implementation(project(":network"))

    implementation(kotlin("stdlib-jdk8"))

    testImplementation(Libs.Test.JUNIT)

}

repositories {
    mavenCentral()
    maven("http://repository.jetbrains.com/all")
}
