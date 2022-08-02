Connect Four is a two-player board game in which the players alternately drop colored disks into a seven-column, six-row vertically suspended grid. The object of the game is to connect four same-colored disks in a row, column, or diagonal before your opponent can do so. Please see http://www.ludoteka.com/connect-4.html

The program should prompt two players to drop a red or yellow disk alternately in a column of their choosing (i.e. 0-6). Whenever a disk is dropped, the program should display the board on the console and determine the status of the game (win, draw, or continue). Here is a sample run:

0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

-------------------------

Please choose a column (0-6) to drop the RED disk:  0 // where the user entered 0

0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

| R  |    |    |    |     |    |    |

-----------------------------

Please choose a column (0-6) to drop the YELLOW disk:  0 // where the user 2 also entered 0

0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|  Y  |    |    |    |     |    |    |

| R  |    |    |    |     |    |    |

-----------------------------

Please choose a column (0-6) to drop the RED disk:  1 // where the user 1 entered 1

0   1   2   3   4   5   6

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

|     |    |    |    |     |    |    |

| Y  |    |    |    |     |    |    |

| R  | R  |    |    |     |    |    |

-----------------------------

Please choose a column (0-6) to drop the YELLOW disk: ....



... And so on until a user (either Red or Yellow) connects 4 of their characters.

The program must contain:

- User input.

- A multidimensional array as the game board.

- Winning conditions: 4 Horizontal pieces of the same kind ,4 vertical pieces of the same kind , or 4 diagonal pieces of the same kind.

- Ensure your program does not terminate if there is bad input and can recover gracefully.

Submission

You can submit your solution in many ways:

Zip up your project and upload your whole java project
Upload the relevant *.java files only (make sure to include your pom.xml if using Maven)
Submit your GitLab link so I can clone your project