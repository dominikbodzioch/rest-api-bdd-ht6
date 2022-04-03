package org.epam.poland.aqa.course.stepdef;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.epam.poland.aqa.course.stepdef"})
public class Runner extends AbstractTestNGCucumberTests {

}
