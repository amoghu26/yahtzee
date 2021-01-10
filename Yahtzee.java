/**
 * A dice game where the objecive is to score more points than your opponent
 *
 * Requires the Prompt, YahtzeeScoreCard, YahtzeePlayer, Dice, and DiceGroup
 * classes
 *
 * @author Amogh Upadhyaya
 * @since October 27 2020
 */

public class Yahtzee {
	private final YahtzeeScoreCard YahtzeeScoreCard; // object of YahtzeeScoreCard
	private final YahtzeeScoreCard YahtzeeScoreCard2; // object of YahtzeeScoreCard
	private final DiceGroup DiceGroup; // object of DiceGroup
	private final Dice Dice; // Object of Dice
	private final Prompt Prompt; // Object of Prompt
	private final YahtzeePlayer p1;// Object of YahtzeePlayer
	private final YahtzeePlayer p2;// Object of YahtzeePlayer

	// * Constructor */
	public Yahtzee() {
		YahtzeeScoreCard = new YahtzeeScoreCard();
		YahtzeeScoreCard2 = new YahtzeeScoreCard();
		DiceGroup = new DiceGroup();
		Dice = new Dice();
		Prompt = new Prompt();
		p1 = new YahtzeePlayer();
		p2 = new YahtzeePlayer();
	}

	// * main method */
	public static void main(String[] args) {
		Yahtzee y = new Yahtzee();
		y.printHeader();
	}

	// * prints header and calls run method */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
		run();
	}

	// * runs the game */
	public void run() {
		int[] values = new int[5]; // stores values for player 1 to decide who starts
		int[] values2 = new int[5];// stores values for player 2 to decide who starts
		int roundNum = 0; // holds the round number
		boolean player1first = false; // determines if player 1 goes first or not
		boolean player2first = false; // determines if player 2 goes first or not
		boolean player1 = false; // determines if it is player 1 turn or not
		boolean player2 = false; // determines if it is player 2 turn or not
		int[] player1Scores = new int[13]; // used to hold the scoreboard scores of player 1
		int[] player2Scores = new int[13]; // used to hold the scoreboard scores of player 2
		int player1total = 0; // the total amount of points player 1 earns
		int player2total = 0; // the total amount of points player 2 scores

		for (int i = 0; i < player1Scores.length; i++) {
			player1Scores[i] = -1;
			player2Scores[i] = -1;
		}

		String name1 = Prompt.getString("Player 1, please enter your first name");
		System.out.println();
		p1.setName(name1);

		String name2 = Prompt.getString("Player 2, please enter your first name");
		System.out.println();
		p2.setName(name2);

		String ask1 = "Let's see who will go first. " + name1 + ", please hit enter to roll the dice";
		Prompt.getString(ask1);

		DiceGroup.rollDice();
		DiceGroup.printDice();
		values = DiceGroup.getValues();

		System.out.println();
		String ask2 = name2 + ", it's your turn. Please hit enter to roll the dice";
		Prompt.getString(ask2);

		DiceGroup.rollDice();
		DiceGroup.printDice();
		values2 = DiceGroup.getValues();

		int sum1 = 0, sum2 = 0;

		for (int i = 0; i < values.length; i++) {
			sum1 += values[i];
			sum2 += values2[i];
		}

		// sum1 = 10;
		// dsum2 = 9;

		System.out.println("\n" + name1 + ", you rolled a sum of " + sum1 + ", and " + name2 + ", you rolled a sum of "
				+ sum2 + ".");
		if (sum1 > sum2) {
			System.out.println(name1 + ", since your sum was higher, you'll roll first.");
			player1first = true;
		} else if (sum1 < sum2) {
			System.out.println(name2 + ", since your sum was higher, you'll roll first.");
			player2first = true;
		} else if (sum1 == sum2) {
			System.out.println("It was a tie. You need to reroll.\n");
			while (sum1 == sum2) {
				ask1 = "Let's see who will go first. " + name1 + ", please hit enter to roll the dice";
				Prompt.getString(ask1);

				DiceGroup.rollDice();
				DiceGroup.printDice();
				values = DiceGroup.getValues();

				System.out.println();
				ask2 = name2 + ", it's your turn. Please hit enter to roll the dice";
				Prompt.getString(ask2);

				DiceGroup.rollDice();
				DiceGroup.printDice();
				values2 = DiceGroup.getValues();

				sum1 = 0;
				sum2 = 0;

				for (int i = 0; i < values.length; i++) {
					sum1 += values[i];
					sum2 += values2[i];
				}

				System.out.println("\n" + name1 + ", you rolled a sum of " + sum1 + ", and " + name2
						+ ", you rolled a sum of " + sum2 + ".");
				if (sum1 > sum2) {
					System.out.println(name1 + ", since your sum was higher, you'll roll first.");
					player1first = true;
				} else if (sum1 < sum2) {
					System.out.println(name2 + ", since your sum was higher, you'll roll first.");
					player2first = true;
				} else
					System.out.println("It was a tie. You need to reroll.\n");
			}

		}

		if (player1first) {
			player1 = true;
			player2 = false;
			while (roundNum < 13) {
				roundNum++;
				YahtzeeScoreCard.printCardHeader();
				YahtzeeScoreCard.printPlayerScore(p1);
				YahtzeeScoreCard2.printPlayerScore(p2);
				System.out.println("\nRound " + roundNum + " of 13 rounds.\n\n");
				if (player1) {
					String ask = name1 + ", it's your turn to play. Please hit enter to roll the dice";
					Prompt.getString(ask);

					DiceGroup.rollDice();
					DiceGroup.printDice();
					int[] vals = DiceGroup.getValues();

					System.out.println(
							"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
									+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					int response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

					int[] arr = new int[5];
					for (int i = 0; i < arr.length; i++)
						arr[i] = 0;
					String str = Integer.toString(response);

					if (response != -1 && response != 0) {
						for (int i = 0; i < str.length(); i++) {
							arr[(int) str.charAt(i) - 49] = 1;
						}
						vals = DiceGroup.changeVals(arr, vals);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							int[] a = { 1, 2, 3, 4, 5 };
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					} else if (response == 0) {
						int[] a = { 1, 2, 3, 4, 5 };
						vals = DiceGroup.changeVals(arr, a);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					}

					System.out.println();
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);
					YahtzeeScoreCard.printChoices();

					/*
					 * vals[0] = 1; vals[1] = 5; vals[2] = 3; vals[3] = 4; vals[4] = 4;
					 */
					String s = name1 + ", now you need to make a choice. Pick a valid integer from the list above";
					int response2 = Prompt.getInt(s);

					if (player1Scores[response2 - 1] != -1) {
						while (player1Scores[response2 - 1] != -1) {
							response2 = Prompt.getInt(s);
						}
					}
					YahtzeeScoreCard.changeScore(response2, vals, name1);
					player1Scores[response2 - 1] = 0;
					player1 = false;
					player2 = true;

				}
				if (player2) {
					System.out.println("\n");
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);

					String s2 = name2 + ", it's your turn to play. Please hit enter to roll the dice";
					Prompt.getString(s2);

					DiceGroup.rollDice();
					DiceGroup.printDice();
					int[] vals = DiceGroup.getValues();

					System.out.println(
							"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
									+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					int response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

					int[] arr = new int[5];
					for (int i = 0; i < arr.length; i++)
						arr[i] = 0;
					String str = Integer.toString(response);

					if (response == 0) {
						int[] a = { 1, 2, 3, 4, 5 };
						vals = DiceGroup.changeVals(arr, a);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					} else if (response != -1) {

						for (int i = 0; i < str.length(); i++) {
							arr[(int) str.charAt(i) - 49] = 1;
						}
						vals = DiceGroup.changeVals(arr, vals);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							int[] a = { 1, 2, 3, 4, 5 };
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					}

					System.out.println();
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);
					YahtzeeScoreCard.printChoices();

					String s = name2 + ", now you need to make a choice. Pick a valid integer from the list above";
					int response2 = Prompt.getInt(s);

					if (player2Scores[response2 - 1] != -1) {
						while (player2Scores[response2 - 1] != -1) {
							response2 = Prompt.getInt(s);
						}
					}
					YahtzeeScoreCard2.changeScore(response2, vals, name2);
					player2Scores[response2 - 1] = 0;

					player2 = false;
					player1 = true;
				}
			}
		} else if (player2first) {
			player1 = true;
			player2 = false;
			while (roundNum < 13) {
				roundNum++;
				YahtzeeScoreCard.printCardHeader();
				YahtzeeScoreCard.printPlayerScore(p1);
				YahtzeeScoreCard2.printPlayerScore(p2);
				System.out.println("\nRound " + roundNum + " of 13 rounds.\n\n");
				if (player1) {
					String ask = name2 + ", it's your turn to play. Please hit enter to roll the dice";
					Prompt.getString(ask);

					DiceGroup.rollDice();
					DiceGroup.printDice();
					int[] vals = DiceGroup.getValues();

					System.out.println(
							"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
									+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					int response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

					int[] arr = new int[5];
					for (int i = 0; i < arr.length; i++)
						arr[i] = 0;
					String str = Integer.toString(response);

					if (response != -1 && response != 0) {
						for (int i = 0; i < str.length(); i++) {
							arr[(int) str.charAt(i) - 49] = 1;
						}
						vals = DiceGroup.changeVals(arr, vals);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							int[] a = { 1, 2, 3, 4, 5 };
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					} else if (response == 0) {
						int[] a = { 1, 2, 3, 4, 5 };
						vals = DiceGroup.changeVals(arr, a);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					}

					System.out.println();
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);
					YahtzeeScoreCard.printChoices();

					/*
					 * vals[0] = 5; vals[1] = 5; vals[2] = 5; vals[3] = 5; vals[4] = 5;
					 */
					String s = name2 + ", now you need to make a choice. Pick a valid integer from the list above";
					int response2 = Prompt.getInt(s);

					if (player1Scores[response2 - 1] != -1) {
						while (player1Scores[response2 - 1] != -1) {
							response2 = Prompt.getInt(s);
						}
					}
					YahtzeeScoreCard2.changeScore(response2, vals, name1);
					player1Scores[response2 - 1] = 0;
					player1 = false;
					player2 = true;

				}
				if (player2) {
					System.out.println("\n");
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);

					String s2 = name1 + ", it's your turn to play. Please hit enter to roll the dice";
					Prompt.getString(s2);

					DiceGroup.rollDice();
					DiceGroup.printDice();
					int[] vals = DiceGroup.getValues();

					System.out.println(
							"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
									+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
					int response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

					int[] arr = new int[5];
					for (int i = 0; i < arr.length; i++)
						arr[i] = 0;
					String str = Integer.toString(response);

					if (response == 0) {
						int[] a = { 1, 2, 3, 4, 5 };
						vals = DiceGroup.changeVals(arr, a);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					} else if (response != -1) {

						for (int i = 0; i < str.length(); i++) {
							arr[(int) str.charAt(i) - 49] = 1;
						}
						vals = DiceGroup.changeVals(arr, vals);
						DiceGroup.printDice();
						System.out.println(
								"Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without"
										+ "\nspaces.  For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
						response = Prompt.getInt("(enter -1 if you'd like to end the turn and 0 to completely reroll)");

						if (response == 0) {
							int[] a = { 1, 2, 3, 4, 5 };
							vals = DiceGroup.changeVals(arr, a);
							DiceGroup.printDice();
						} else if (response != -1) {
							for (int i = 0; i < arr.length; i++)
								arr[i] = 0;
							str = Integer.toString(response);

							for (int i = 0; i < str.length(); i++) {
								arr[(int) str.charAt(i) - 49] = 1;
							}
							vals = DiceGroup.changeVals(arr, vals);
							DiceGroup.printDice();
						}
					}

					System.out.println();
					YahtzeeScoreCard.printCardHeader();
					YahtzeeScoreCard.printPlayerScore(p1);
					YahtzeeScoreCard2.printPlayerScore(p2);
					YahtzeeScoreCard.printChoices();

					String s = name1 + ", now you need to make a choice. Pick a valid integer from the list above";
					int response2 = Prompt.getInt(s);

					if (player2Scores[response2 - 1] != -1) {
						while (player2Scores[response2 - 1] != -1) {
							response2 = Prompt.getInt(s);
						}
					}
					YahtzeeScoreCard.changeScore(response2, vals, name2);
					player2Scores[response2 - 1] = 0;

					player2 = false;
					player1 = true;
				}
			}
		}

		if (player1first) {
			player1total = YahtzeeScoreCard.returnTotal();
			player2total = YahtzeeScoreCard2.returnTotal();

			System.out.println("\n\n" + name1 + ", you earned a total of " + player1total + " points and " + name2
					+ ", you earned a total of " + player2total + " points");

			if (player1total > player2total) {
				System.out.println(name1 + ", YOU WON!!!");
			} else if (player2total > player1total) {
				System.out.println(name2 + ", YOU WON!!!");
			} else
				System.out.println("It was a tie!");
		} else {
			player2total = YahtzeeScoreCard.returnTotal();
			player1total = YahtzeeScoreCard2.returnTotal();

			System.out.println("\n\n" + name1 + ", you earned a total of " + player2total + " points and " + name2
					+ ", you earned a total of " + player1total + " points");

			if (player1total > player2total) {
				System.out.println(name2 + ", YOU WON!!!");
			} else if (player2total > player1total) {
				System.out.println(name1 + ", YOU WON!!!");
			} else
				System.out.println("It was a tie!");
		}
	}

}
