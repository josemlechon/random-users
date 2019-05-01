package com.jml.random.users

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.BUILD_TOOLS_VERSION}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    object Config {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
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

    object DI {
        const val KOIN_ANDROID = "org.koin:koin-android:${Versions.DI.KOIN}"
        const val KOIN_LIFECYCLE = "org.koin:koin-androidx-scope:${Versions.DI.KOIN}"
        const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.DI.KOIN}"
    }

    object Network {
        const val GSON = "com.google.code.gson:gson:${Versions.Network.GSON}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Network.RETROFIT}"
        const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val OKHHTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}"
    }

    object Rx {
        const val JAVA = "io.reactivex.rxjava2:rxjava:${Versions.Rx.JAVA}"
        const val ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.ANDROID}"
        const val KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.Rx.KOTLIN}"
    }

    object Persistence {
        const val ROOM = "androidx.room:room-runtime:${Versions.Persistence.ROOM}"
        const val ROOM_RX = "androidx.room:room-rxjava2:${Versions.Persistence.ROOM}"
        const val ROOM_KAPT = "androidx.room:room-compiler:${Versions.Persistence.ROOM}"
        const val PAGINATION = "androidx.paging:paging-runtime-ktx:${Versions.Persistence.PAGINATION}"
        const val PAGINATION_RX = "androidx.paging:paging-rxjava2:${Versions.Persistence.PAGINATION}"
    }

    object Utils {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.Utils.GLIDE_VERSION}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.Utils.GLIDE_VERSION}"
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.Utils.TIMBER_VERSION}"
        const val DATE = "com.jakewharton.threetenabp:threetenabp:${Versions.Utils.DATE_VERSION}"
    }

    object Test {
        const val JUNIT: String = "junit:junit:${Versions.Test.JUNIT}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.MOCKITO}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.Test.MOCKITO}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.MOCKITO}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Test.MOCKITO}"
        const val room = "android.arch.persistence.room:testing:${Versions.Persistence.ROOM}"
    }
}