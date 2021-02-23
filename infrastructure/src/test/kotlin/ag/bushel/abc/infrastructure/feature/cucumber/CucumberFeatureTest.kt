package ag.bushel.abc.infrastructure.feature.cucumber

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

/**
Cucumber uses jUnit 4, requiring RunWith to indicate how to run the features.  CucumberOptions configures the folder locations for the *.feature file locations.
Empty class only utilized to configure cucumber.  This class is required for cucumber features tests to be recognized while running test suite.
 **/

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources/cucumber/features"])
class CucumberFeatureTest
