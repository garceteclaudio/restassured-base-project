plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0") // Asegúrate de que esta versión coincida con tu JUnit BOM

    // JUnit BOM for consistent versions
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // JUnit 4
        testImplementation("junit:junit:4.13.2")
    // Or a later version
    // Hamcrest Core and Library (often pulled in by JUnit, but explicit can help)
        testImplementation("org.hamcrest:hamcrest-core:1.3")
    // Or a compatible version
        testImplementation("org.hamcrest:hamcrest-library:1.3")
// Or a compatible version

    // Rest Assured (for API testing)
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:json-path:5.3.0") // Needed for JSON parsing
    testImplementation("io.rest-assured:xml-path:5.3.0") // Optional, if you deal with XML
    implementation("io.rest-assured:rest-assured:5.3.0")


    // Gson (for JSON serialization/deserialization)
    implementation("com.google.code.gson:gson:2.7")

    // Cucumber-JVM dependencies
    testImplementation("io.cucumber:cucumber-java:7.18.0")
    testImplementation("io.cucumber:cucumber-junit:7.18.0") // For JUnit 4 runner
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.0") // For JUnit 5 integration

    testImplementation ("io.rest-assured:json-schema-validator:5.3.0")
}

tasks.test {
    useJUnitPlatform() // This is crucial for JUnit 5 and Cucumber-JUnit-Platform-Engine
}