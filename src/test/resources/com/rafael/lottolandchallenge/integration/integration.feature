@playerService
Feature: Players service integration test
  This feature contains all integration tests related to the GameRPSResources API 

	@playersInfo
  Scenario Outline: Play a round with two valid players
    Given I have two players: "<Player1>" and "<Player2>"
    When I send a "POST" to "play a Round"
    And I send a "GET" to "get the rounds"
    And I send a "GET" to "get the stats"
    Then I get a status code 200 for each reponse from GameRPSResources service
    And I get valid round with the "<HandP1>", the "<HandP2>" and the "<Winner>"
    And I get a list of rounds with one round with data: "<HandP1>", the "<HandP2>" and the "<Winner>"
    And I get valid stats for "<WinnerName>"

    Examples: 
      | Player1     | Player2     | Winner | HandP1   | HandP2   | WinnerName  |
      | TheRock     | TheScissors | 1      | ROCK     | SCISSORS | TheRock     |
      | TheRock     | ThePapper   | 2      | ROCK     | PAPPER   | ThePapper   |
      | TheRock     | TheRock     | 0      | ROCK     | ROCK     | SKIP        |
      | ThePapper   | TheRock     | 1      | PAPPER   | ROCK     | ThePapper   |
      | ThePapper   | TheScissors | 2      | PAPPER   | SCISSORS | TheScissors |
      | ThePapper   | ThePapper   | 0      | PAPPER   | PAPPER   | SKIP        |
      | TheScissors | ThePapper   | 1      | SCISSORS | PAPPER   | TheScissors |
      | TheScissors | TheRock     | 2      | SCISSORS | ROCK     | TheRock     |
      | TheScissors | TheScissors | 0      | SCISSORS | SCISSORS | SKIP        |
      | Rnd         | Rnd         | SKIP   | SKIP     | SKIP     | SKIP        |

	@playersInfo
  Scenario Outline: Play a round with invalid players
    Given I have two players: "<Player1>" and "<Player2>"
    When I send a "POST" to "play a Round"
    Then I get a status code 404 for each reponse from GameRPSResources service
    
    Examples: 
      | Player1     | Player2     |
      | FOO         | TheScissors |
      | TheScissors | BAR         |
      | FOO         | BAR         |

  @playersInfo
  Scenario: Try to play with the wrong number of players
    Given I have only one players
    When I send a "POST" to "play a Round"
    Then I get a status code 404 for each reponse from GameRPSResources service
    
	@playersInfo
  Scenario: Play a round and after reset the played rounds 
    Given I have two players: "TheRock" and "TheScissors"
    When I send a "POST" to "play a Round"
    And I send a "DELETE" to "reset the rounds"
    And I send a "GET" to "get the rounds"
    And I send a "GET" to "get the stats"
    Then I get a status code 200 for each reponse from GameRPSResources service
    And I get valid round with the "ROCK", the "SCISSORS" and the "1"
    And I get a list of rounds with no data
    And I get valid stats for "TheRock"
 
