package com.github.bddcoverage.exampleproject.bdd;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.github.bddcoverage.exampleproject.HelloWorld;

public class HelloWorldSteps {

    private HelloWorld helloWorld;
    private String generatedGreeting;

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

}