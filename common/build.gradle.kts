import com.jml.random.users.Config
import com.jml.random.users.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
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

    api("androidx.recyclerview:recyclerview:1.0.0")
    api("androidx.cardview:cardview:1.0.0")

    api("androidx.constraintlayout:constraintlayout:1.1.3")
    api("com.google.android.material:material:1.0.0")
    api("androidx.appcompat:appcompat:1.0.2")

    //viewmodel
    api("androidx.lifecycle:lifecycle-viewmodel:2.0.0")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0")
    api("androidx.lifecycle:lifecycle-extensions:2.0.0")

    //Network
    api("com.google.code.gson:gson:2.8.2")
    api("com.squareup.retrofit2:retrofit:2.4.0")
    api("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    api("com.squareup.retrofit2:converter-gson:2.4.0")
    api("com.squareup.okhttp3:okhttp:3.10.0")
    api("com.squareup.okhttp3:logging-interceptor:3.10.0")

    //RX
    api(Libs.Config.KOTLIN)
    api(Libs.Rx.JAVA)
    api(Libs.Rx.ANDROID)
    api(Libs.Rx.KOTLIN)


    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")


}
