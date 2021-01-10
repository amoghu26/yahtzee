/**
 *	@author	Amogh Upadhyaya
 *	@since	10/25/2020
 */
public class DiceGroup {
	
	private Dice [] die;	// the array of dice
	Dice d = new Dice();
	
	// Create the seven different line images of a die
	private String [] line = {	" _______ ",
								"|       |",
								"| O   O |",
								"|   O   |",
								"|     O |",
								"| O     |",
								"|_______|" };
	
	//Constructor
	public DiceGroup()
	{
		die = new Dice[5];
		for(int i = 0; i<5; i++)
		{
			die[i] = new Dice();
		}
		for(int i = 0; i<5; i++)
		{
			die[i].roll();
		}
	}
	
	/*	rolls dice */
	public void rollDice()
	{
		for(int i = 0; i<5; i++)
		{
			die[i].roll();
		}
	}
	
	/*	assigns values to Dice objects */
	public int [] getValues()
	{
		int [] arr = new int[5];
		for(int i = 0; i<5; i++)
		{
			arr[i] = die[i].getValue();
		}
		return arr;
	}
	
	/**
	 *  Change the dice values based on the player choice.
	 *
	 *  @param arr array of values to change
	 *  @param arr2  array telling the method which values to change
	 *  @return  arr2 modify arr2 to store newly rolled values
	 */
	public int [] changeVals(int [] arr, int [] arr2)
	{
		for(int i = 0; i<die.length; i++)
		{
			if(arr[i] == 0)
			{
				die[i].roll();
				arr2[i] = die[i].getValue();
			}
		}
		return arr2;
	}
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	public void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 6; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public void printDiceLine(int value) {
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
}