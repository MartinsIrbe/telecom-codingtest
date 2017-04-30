```
   $$$$$$$\             $$\     $$\     $$\                     $$\       $$\                     
   $$  __$$\            $$ |    $$ |    $$ |                    $$ |      \__|                    
   $$ |  $$ | $$$$$$\ $$$$$$\ $$$$$$\   $$ | $$$$$$\   $$$$$$$\ $$$$$$$\  $$\  $$$$$$\   $$$$$$$\ 
   $$$$$$$\ | \____$$\\_$$  _|\_$$  _|  $$ |$$  __$$\ $$  _____|$$  __$$\ $$ |$$  __$$\ $$  _____|
   $$  __$$\  $$$$$$$ | $$ |    $$ |    $$ |$$$$$$$$ |\$$$$$$\  $$ |  $$ |$$ |$$ /  $$ |\$$$$$$\  
   $$ |  $$ |$$  __$$ | $$ |$$\ $$ |$$\ $$ |$$   ____| \____$$\ $$ |  $$ |$$ |$$ |  $$ | \____$$\ 
   $$$$$$$  |\$$$$$$$ | \$$$$  |\$$$$  |$$ |\$$$$$$$\ $$$$$$$  |$$ |  $$ |$$ |$$$$$$$  |$$$$$$$  |
   \_______/  \_______|  \____/  \____/ \__| \_______|\_______/ \__|  \__|\__|$$  ____/ \_______/ 
                                                                              $$ |                
                                                                              $$ |                
                                                                              \__|                
```

A basic Battleships game where the player has 20 moves to destroy ships on the field.

#### Ship sizes
Battleship = 5 squares  
Destroyer = 4 squares

#### Launching the game & running tests
First, need to build the maven project by executing the command in the project root:  
`mvn clean install`

Launching the game from console by executing the generated jar from the target directory:  
`java -cp battleships-1.0-SNAPSHOT-jar-with-dependencies.jar battleships/Application`

To run tests, execute the following command in the project root directory:  
`mvn test`

#### Requirements
* Maven
* JDK 8

