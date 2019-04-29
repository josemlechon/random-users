import com.jml.random.users.Config
import com.jml.random.users.Libs
import com.jml.random.users.Versions


//TODO READ https://kotlinlang.org/docs/reference/using-gradle.html
plugins {
    id("com.android.application")// version Versions.BUILD_TOOLS_VERSION
    kotlin("android")// version Versions.KOTLIN
    kotlin("android.extensions")
    kotlin("kapt")// version Versions.KOTLIN
}

android {

    compileSdkVersion(Config.AndroidSdk.COMPILE)

    defaultConfig {
        minSdkVersion(Config.AndroidSdk.MIN)
        targetSdkVersion(Config.AndroidSdk.TARGET)

        applicationId = "com.jml.random.users"
        versionCode = 1
        versionName = "1.0"


        buildConfigField("String", "SERVER_BASE_URL", "\"https://randomuser.me\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")

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

    implementation(project(Config.ProjectModules.COMMON))

    implementation(kotlin("stdlib-jdk8"))

    kapt(Libs.Persistence.ROOM_KAPT)
    kapt(Libs.Utils.GLIDE_COMPILER)

    testImplementation(Libs.Test.JUNIT)

}
