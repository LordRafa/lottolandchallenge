# lottolandchallenge

This repository contains an implementation of the Rock, Paper and Scisors game.

It is a maven project and it uses Grizzly as web server to provide access to
the static content and a rest api.

The rest api has been implemented with jersey/jax-rs and provides access to resources:   


| resource     | Action | Description                                   | Parameters                                                                   |
|--------------|--------|-----------------------------------------------|------------------------------------------------------------------------------|
| rps2p/rounds | POST   | Adds/Play a round                             | ``` {      "players" : [          "PLAYER1NAME" ,          "PLAYER2NAME"     ] }``` |
| rps2p/rounds | GET    | Get a list with all rounds played by the user |                                                                              |
| rps2p/rounds | DELETE | Clear all round played by the user            |                                                                              |
| rps2p/stats  | GET    | Get the server stats                          |                                                                              |

This server stores the rounds per http session in memory, in order to do so, a ComponentProvider
was implemented to modify Jerseys's per-lookup behaviour. This was done following jersey doc.

On the other hand the stats are stored in memory in a singleton object accesible for any user.

The user interface consist in two html files, that uses bootstrap and jQuery to communicate with
the rest api and present the information.

The unit testing was implemented with JUnit. For the integration testing Cucumber/JUnit Was used.

Also I am using Github actions, Docker Hub and Docker on a Lightsail instance create a CI/CD flow.

The project is avaiable at:
https://lottolandchallenge.rawapro.com

# How to run the proyect

Run the following cmd on the root directory to run the testing and execute the app

```bash
mvn clean test exec:java
```
