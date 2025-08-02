package org.example.runners;// src/test/java/org/example/runners/org.example.runners.TestRunner.java


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

// This runner uses JUnit 5's @Suite to run Cucumber tests.
// It's the recommended way for modern Cucumber-JVM setups.
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Points to the directory where your .feature files are located
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example.stepdefs") // Points to your step definitions package
public class TestRunner {
    // This class remains empty, it's just a placeholder for the annotations
    // that configure the JUnit 5 test suite for Cucumber.
}
