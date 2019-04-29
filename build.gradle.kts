buildscript {
    val kotlin_version = "1.3.31"

    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath(kotlin("gradle-plugin", version = kotlin_version))

    }
}

allprojects {
    repositories {
        google()
        jcenter()

        mavenCentral()
        maven("http://repository.jetbrains.com/all")
    }
}

tasks.register("clean").configure {
    delete("build")
}