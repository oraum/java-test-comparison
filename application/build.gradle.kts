plugins {
    java
    war
}

dependencies {
    providedCompile("javax", "javaee-api", "8.0")
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:5.4.4.Final")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
