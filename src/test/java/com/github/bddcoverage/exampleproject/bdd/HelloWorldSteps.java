package com.github.bddcoverage.exampleproject.bdd;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jacoco.agent.rt.internal.Agent;
import org.jacoco.agent.rt.internal.IExceptionLogger;
import org.jacoco.core.runtime.AgentOptions;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.github.bddcoverage.exampleproject.HelloWorld;

public class HelloWorldSteps {

    private HelloWorld helloWorld;
    private String generatedGreeting;
    private Agent agent;

    @Given("a running app")
    public void theAppIsRunning() {
        helloWorld = new HelloWorld();
    }

    @When("the user is $username")
    public void theUserIs(@Named("username") String username) {
        generatedGreeting = helloWorld.greet(username);
    }

    @Then("the app should say $expectedMessage")
    public void theAppShouldSay(@Named("expectedMessage") String expectedMessage) {
        assertThat(generatedGreeting, equalTo(expectedMessage));
    }

    @BeforeScenario
    public void setupCoverageAgent() {
      AgentOptions options = new AgentOptions();
      options.setSessionId("A random sessionid in options"); // FIXME needs to come from the current session
      options.setDestfile("bdd_coverage.exec");
      IExceptionLogger exceptionLogger = new IExceptionLogger() {

        public void logExeption(Exception ex) {
          ex.printStackTrace();
        }
      };
      agent = new Agent(options, exceptionLogger);
      agent.startup();
    }
    @AfterScenario
    public void recordScenarioCoverage() {
      try {
        agent.dump(true);
      } catch (Throwable e) {
        e.printStackTrace();
      } finally {
        agent.shutdown();
      }
    }
}