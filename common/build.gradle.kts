import com.jml.random.users.Config
import com.jml.random.users.Libs
import com.jml.random.users.Versions

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt") //version Versions.KOTLIN
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

    api(project(Config.ProjectModules.NETWORK))

    implementation(Libs.Config.KOTLIN)
    api(Libs.Extensions.KTX)

    api(Libs.UI.RECYCLERVIEW)
    api(Libs.UI.CARDVIEW)

    api(Libs.UI.CONSTRAINTLAYOUT)
    api(Libs.UI.DESIGN_COMPAT)
    api(Libs.UI.APPCOMPAT)

    //viewmodel
    api(Libs.ViewModel.VIEWMODEL)
    api(Libs.ViewModel.LIFECICLY_VIEMODEL_KTX)
    api(Libs.ViewModel.LIFECYCLE_EXTENSIONS)

    //Network
    api(Libs.Network.GSON)
    api(Libs.Network.RETROFIT)
    api(Libs.Network.RETROFIT_RX_ADAPTER)
    api(Libs.Network.RETROFIT_GSON_CONVERTER)
    api(Libs.Network.OKHTTP)
    api(Libs.Network.OKHHTP_LOGGER)

    //DI
    api(Libs.DI.KOIN_ANDROID)
    api(Libs.DI.KOIN_LIFECYCLE)
    api(Libs.DI.KOIN_VIEWMODEL)

    //RX
    api(Libs.Config.KOTLIN)
    api(Libs.Rx.JAVA)
    api(Libs.Rx.ANDROID)
    api(Libs.Rx.KOTLIN)

    //ROOM
    api(Libs.Persistence.ROOM)
    api(Libs.Persistence.ROOM_RX)
    kapt(Libs.Persistence.ROOM_KAPT)

    //UTILS
    api(Libs.Utils.GLIDE)

    testImplementation(Libs.Test.JUNIT)
}
