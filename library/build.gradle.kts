import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.kotlin"
version = "0.1.0"

kotlin {
    jvmToolchain(11)
    jvm()
    androidLibrary {
        namespace = "com.ikaal"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "iKaal"
        description = "A KMP library allowing you to manipulate iCalendar files according to RFC 5545"
        inceptionYear = "2026"
        url = "https://github.com/JulianClemot/iKaal"
        licenses {
            license {
                name = "MIT"
                url = "https://raw.githubusercontent.com/JulianClemot/iKaal/refs/heads/main/LICENSE"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "JulianClemot"
                name = "Julian Clémot"
                url = "https://github.com/JulianClemot"
            }
        }
        scm {
            url = "https://github.com/JulianClemot/iKaal"
            connection = "scm:git:git://github.com/JulianClemot/iKaal.git"
            developerConnection = "scm:git:ssh://git@github.com/JulianClemot/iKaal.git"
        }
    }
}
