package testRunner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
       features = {"C:\\Users\\2361361\\eclipse-workspace\\ApiAssessment\\src\\test\\resources\\Features\\GetRequests.feature"},
       glue = {"stepDefinitions"},
       plugin = {"pretty",
	             "json:target/cucumber-report.json"},
       tags="@GetFunctionality"
)
public class GetRequestTestRunner {
}
