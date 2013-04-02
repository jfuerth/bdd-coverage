Scenario: Regular Greetings
Given a running app
When the user is Marius
Then the app should say Hello, Marius!

Scenario: Easter Egg
Given a running app
When the user is [username]
Then the app should say [expectedMessage]

Examples:
|username|expectedMessage|
|Coward|Moooooo!|
|Cow|Moooooo!|
