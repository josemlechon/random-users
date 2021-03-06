import com.jml.random.users.Config
import com.jml.random.users.Libs


plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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
        "META-INF/MANIFEST.MF"
    )
}

dependencies {

    implementation(project(Config.ProjectModules.COMMON))

    implementation(kotlin("stdlib-jdk8"))

    kapt(Libs.Persistence.ROOM_KAPT)
    kapt(Libs.Utils.GLIDE_COMPILER)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.KOIN)
    testImplementation(Libs.Test.MOCKITO)

    testImplementation(Libs.Test.MOCKITO_INLINE)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)

    androidTestImplementation(Libs.Test.MOCKITO_ANDROID)
}
