import org.gradle.api.tasks.testing.logging.TestLogEvent

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        testLogging {
            events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)
        }
    }
}

