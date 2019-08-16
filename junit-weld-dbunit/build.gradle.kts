plugins {
    java
}


dependencies {
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.hamcrest:hamcrest:2.1")
    testImplementation("org.jboss.weld", "weld-junit4", "2.0.0.Final")
    testImplementation("org.eclipse.persistence", "org.eclipse.persistence.jpa", "2.7.4")
    testImplementation("com.h2database", "h2", "1.4.199")
    testImplementation("org.dbunit", "dbunit", "2.6.0")
    testImplementation(project(":application"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
