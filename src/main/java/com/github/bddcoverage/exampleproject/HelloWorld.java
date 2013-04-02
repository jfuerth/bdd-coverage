package com.github.bddcoverage.exampleproject;

public class HelloWorld {

  public String greet(String username) {
    if (username.startsWith("Cow")) {
      return "Moooooo!";
    }
    return "Hello, " + username + "!";
  }

}
