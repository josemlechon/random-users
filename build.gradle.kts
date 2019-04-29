buildscript {
    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath(kotlin("gradle-plugin", version = "1.3.31"))
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