import com.jml.random.users.Libs
import com.jml.random.users.Config


plugins {
    id("com.android.library")
}

apply {
    plugin("kotlin-android")
    plugin("kotlin-android-extensions")
}

android {

    compileSdkVersion(Config.AndroidSdk.COMPILE)

    defaultConfig {
        minSdkVersion(Config.AndroidSdk.MIN)
        targetSdkVersion(Config.AndroidSdk.TARGET)

        versionCode = 1
        versionName = "1.0"
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")

}

dependencies {
    implementation( Libs.Config.KOTLIN)
    api(Libs.Network.GSON)
    api(Libs.Network.RETROFIT)
    api(Libs.Network.RETROFIT_RX_ADAPTER)
    api(Libs.Network.RETROFIT_GSON_CONVERTER)
    api(Libs.Network.OKHTTP)
    api(Libs.Network.OKHHTP_LOGGER)
}
repositories {
    mavenCentral()
}