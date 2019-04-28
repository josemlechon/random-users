package com.jml.random.users

object Libs {

    object Config {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    }

    object UI {
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.UI.RECYCLER}"
        const val CARDVIEW = "androidx.cardview:cardview:${Versions.UI.CARD}"
        const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.UI.CONSTRAINT}"
        const val DESIGN_COMPAT = "com.google.android.material:material:${Versions.UI.DESIGN}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.UI.APPCOMPAT}"
    }

    object ViewModel {
        const val VIEWMODEL: String = "androidx.lifecycle:lifecycle-viewmodel:${Versions.ViewModel.VIEWMODEL}"
        const val LIFECICLY_VIEMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Extensions.LIFE_CYCLE_VIMODEL_KTX}"
        const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.Extensions.VIEWMODEL}"
    }

    object Extensions {
        const val KTX = "androidx.core:core-ktx:${Versions.Extensions.KTX}"
    }

    object Network {
        const val GSON = "com.google.code.gson:gson:${Versions.Network.GSON}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Network.RETROFIT}"
        const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val OKHHTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}"
    }

    object Dagger {
        const val DAGGER = "com.google.dagger:dagger:${Versions.Dagger.VERSION}"
        const val COMPILER = "com.google.dagger:dagger-compiler:${Versions.Dagger.VERSION}"
        const val ANDROID = "com.google.dagger:dagger-android:${Versions.Dagger.VERSION}"
        const val ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.Dagger.VERSION}"
        const val ANNOTATION_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.Dagger.VERSION}"
    }

    object Rx {
        const val JAVA = "io.reactivex.rxjava2:rxjava:${Versions.Rx.JAVA}"
        const val ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.ANDROID}"
        const val KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.Rx.KOTLIN}"
    }

    object Test {
        const val junit: String = "junit:junit:${Versions.Test.JUNIT}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.MOCKITO}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.Test.MOCKITO}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.MOCKITO}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Test.MOCKITO}"
    }

    object Utils {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.Utils.GLIDE_VERSION}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.Utils.GLIDE_VERSION}"
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.Utils.TIMBER_VERSION}"
        const val DATE = "com.jakewharton.threetenabp:threetenabp:${Versions.Utils.DATE_VERSION}"
    }
}