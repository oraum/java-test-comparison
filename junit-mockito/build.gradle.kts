plugins {
    java
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("org.hamcrest:hamcrest:2.1")
    compile(project(":application"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
