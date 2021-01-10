public class YahtzeeScoreCard 
{
	int [] arr = new int[13]; // array variable to hold scores
	Prompt Prompt; // instance of prompt
	
	//* constructor /*
	public YahtzeeScoreCard()
	{
		for(int i = 0; i<arr.length; i++)
		{
			arr[i] = -1;
		}
		Prompt = new Prompt();
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  finds the total value of the player's scorecard
	 *  
	 *  @return total The total value
	 */
	public int returnTotal()
	{
		int total = 0;
		for(int i = 0; i<arr.length; i++)
		{
			total+=arr[i];
		}
		return total;
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 0; i < 13; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  prints the number choices for the player to choose from
	 */
	public void printChoices()
	{
		System.out.print("                  ");
		System.out.print("1    2    3    4    5    6    7    8    9   10   11   12   13");
		System.out.println();
	}

	/**
	 *  given an integer i, returns the value of the ith value of the array
	 *  
	 *  @return arr[i] The designated slot of the array
	 */
	public int getScore(int i)
	{
		return arr[i];
	}
	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, int [] arr2, String name)
	{
		if(choice<=6)
		{
			numberScore(choice, arr2, name);
		}
		else if(choice == 7)
		{
			threeOfAKind(arr2);
		}
		else if(choice == 8)
		{
			fourOfAKind(arr2);
		}
		else if(choice == 9)
		{
			fullHouse(arr2);
		}
		else if(choice == 10)
		{
			smallStraight(arr2);
		}
		else if(choice == 11)
		{
			largeStraight(arr2);
		}
		else if(choice == 12)
		{
			chance(arr2);
		}
		else if(choice == 13)
		{
			yahtzeeScore(arr2);
		}
		return false;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, int [] arr2, String name) 
	{
		while(arr[choice-1] != -1)
		{
			String s = name + ", now you need to make a choice. Pick a valid integer from the list above";
			choice = Prompt.getInt(s);
		}
		int counter = 0;
		for(int i = 0; i<arr2.length; i++)
		{
			if(arr2[i] == choice) counter+=choice;
		}
		//arr[choice-1] = 0;
		//if(counter>0)
		arr[choice-1] = counter;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(int [] arr2) 
	{
		int hold = 0;
		int counter = 0;
		boolean exit = false;
		for(int i = 0; i<arr2.length; i++)
		{
			hold = arr2[i];
			for(int j = 0; j<arr2.length; j++)
			{
				if(arr2[j] == hold)
				{
					counter++;
					if(counter == 3)
					{
						arr[6] = 3*arr2[i];
						exit = true;
					}
				}
			}
			if(exit == true)
			{}
			else counter = 0;
		}
		
		if(exit == false)
		{
			arr[6] = 0;
		}
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void fourOfAKind(int [] arr2) 
	{
		int hold = 0;
		int counter = 0;
		boolean exit = false;
		for(int i = 0; i<arr2.length; i++)
		{
			hold = arr2[i];
			for(int j = 0; j<arr2.length; j++)
			{
				if(arr2[j] == hold)
				{
					counter++;
					if(counter == 4)
					{
						arr[7] = 4*arr2[i];
						exit = true;
					}
				}
			}
			if(exit == true)
			{}
			else counter = 0;
		}
		
		if(exit == false)
		{
			arr[7] = 0;
		}
	}
	
	/**
	 *	Updates the scorecard for fullHouse choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void fullHouse(int [] arr2)
	{
		int hold = 0;
		int counter = 0;
		boolean exit = false, exit2 = false;
		int hold2 = 0;
		for(int i = 0; i<arr2.length; i++)
		{
			hold = arr2[i];
			for(int j = 0; j<arr2.length; j++)
			{
				if(arr2[j] == hold)
				{
					counter++;
					if(counter == 3)
					{
						exit = true;
						hold2 = hold;
					}
				}
			}
			if(exit == true)
			{}
			else counter = 0;
		}
		counter = 0;
		
		if(exit == true)
		{
			for(int i = 0;i < arr2.length; i++)
			{
				hold = arr2[i];
				for(int j = 0; j<arr2.length; j++)
				{
					if(arr2[j] == hold)
					{
						counter++;
						if(counter == 2 && hold != hold2)
						{
							exit2 = true;
						}
					}
				}
				if(exit2 == true) {}
				else counter = 0;
			}
		}
		arr[8] = 0;
		if(exit2 == true)
		{
			arr[8] = 25;
		}
		
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void smallStraight(int [] arr2) 
	{
		int previous = 0;
		boolean up = false, down = false, invalid = false;;
		
		for(int i = 0; i<4; i++)
		{
			if(i==1)
			{
				if(previous+1 == arr2[i])
				{
					up = true;
				}
				else if(arr2[i] == previous-1)
				{
					down = true;
				}
				else 
				{
					invalid = true;
					break;
				}
			}
			if((i!=0 && i!=1) && (up == true || down == true))
			{
				if(up)
				{
					if(previous+1 == arr2[i]) {}
					else up = false;
				}
				if(down)
				{
					if(previous-1 == arr2[i]){}
					else down = false;
				}
			}
			previous = arr2[i];
		}
		
		arr[9] = 0;
		if(up||down)
		{
			arr[9] = 30;
		}
		else
		{
			previous = 0;
			up = false;
			down = false;
			invalid = false;
			for(int i = 1; i<5; i++)
			{
				if(i==2)
				{
					if(previous+1 == arr2[i])
					{
						up = true;
					}
					else if(arr2[i] == previous-1)
					{
						down = true;
					}
					else 
					{
						invalid = true;
						break;
					}
				}
				if((i!=1 && i!=2) && (up == true || down == true))
				{
					if(up)
					{
						if(previous+1 == arr2[i]) {}
						else up = false;
					}
					if(down)
					{
						if(previous-1 == arr2[i]){}
						else down = false;
					}
				}
				previous = arr2[i];
			}
			if(up||down)
			{
				arr[9] = 30;
			}
		}
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void largeStraight(int [] arr2) 
	{
		int previous = 0;
		boolean up = false, down = false, invalid = false;;
		
		for(int i = 0; i<arr2.length; i++)
		{
			if(i==1)
			{
				if(previous+1 == arr2[i])
				{
					up = true;
				}
				else if(arr2[i] == previous-1)
				{
					down = true;
				}
				else 
				{
					invalid = true;
					break;
				}
			}
			if((i!=0 && i!=1) && (up == true || down == true))
			{
				if(up)
				{
					if(previous+1 == arr2[i]) {}
					else up = false;
				}
				if(down)
				{
					if(previous-1 == arr2[i]){}
					else down = false;
				}
			}
			previous = arr2[i];
		}
		
		
		arr[10] = 0;
		if(up||down)
		{
			arr[10] = 40;
		}
	}
	
	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void chance(int [] arr2)
	{
		arr[11] = 0;
		for(int i = 0; i<arr2.length; i++)
		{
			arr[11]+=arr2[i];
		}
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param arr2	The DiceGroup to score
	 */	
	public void yahtzeeScore(int [] arr2) 
	{
		int previousScore = arr2[0];
		boolean b = false;
		for(int i = 0; i<arr2.length; i++)
		{
			if(previousScore!=arr2[i]) 
			{
				arr[12] = 0;
				b = true;
				break;
			}
		}
		if(b == false)
		{
			arr[12] = 50;
		}
	}

}
