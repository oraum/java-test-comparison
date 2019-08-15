plugins {
    java
    war
}

group = "com.github.oraum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("javax", "javaee-api", "8.0")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
