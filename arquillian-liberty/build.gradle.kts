import net.wasdev.wlp.gradle.plugins.extensions.ServerExtension

plugins {
    java
    id("net.wasdev.wlp.gradle.plugins.Liberty") version "2.6.5"
}


dependencies {
    testImplementation(project(":application"))
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.jboss.arquillian.junit", "arquillian-junit-container", "1.4.0.Final")
    testImplementation("org.jboss.arquillian.extension", "arquillian-persistence-dbunit", "1.0.0.Alpha7")
    testImplementation("io.openliberty.arquillian", "arquillian-liberty-managed-junit", "1.1.6")
    testCompile(files("${System.getProperty("java.home")}/../lib/tools.jar"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Test> {
    testLogging.showStandardStreams = true
    include("**/*")
    dependsOn ("installApps", "testClasses", "configureArquillian")
}

liberty {
    server = ServerExtension("default").apply{
        serverName = "${project.name}Server"
        configDirectory = file("src/liberty/config")
    }
}

arquillianConfiguration {
    arquillianProperties = mapOf("outputToConsole" to "true", "serverStartTimeout" to "60")
    skipIfArquillianXmlExists = true
}
