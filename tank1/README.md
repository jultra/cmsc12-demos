To compile:

`javac -d "bin" tank1/core/*.java tank1/entity/*.java tank1/test/*.java`

To run:

`java -cp "bin" tank1.test.GameTester`


package organization
- core, main game logic, game loop, controller
- test, test files
- main, gui, main frame, other non-functional featuers
- entity, all things displayed, moving in the game