/*
 * Name: Joshua Chang
 * Date: Jan 20/19
 * Purpose: The game of Hangman
 * Filename: Hangman
 */
package Hangman;
import java.util.*;
public class Hangman {
  
	/*
	 * purpose: to display the instructions to player 1 and 2
	 * @param: No Parameters
	 * @return: No return
	 */
	
	public static void intsructions(){
		System.out.println("Intsructions\n");
		System.out.println("Objective of the Game");
		System.out.println("Player 1: to input a word/phrase that is difficult for player 2 to guess");
		System.out.println("Player 2: to guess the word/phrase that player 1 has entered by guessing a letter each time in less than 10 attempts (if confident, player 2 can guess the entire word/phrase\n");
		System.out.println("How the Game Works");
		System.out.println("Player 1 will enter a word/phrase and the the topic related to it (to give a clue for player 2)");
		System.out.println("Player 1 MUST NOT use capital letters or any special characters");
		System.out.println("Player 2 is allowed to guess 1 letter each round");
		System.out.println("Each round, player 2 is shown the topic of the word/phrase, the word/phrase that they have so far completed, the number of unsuccessful attempts left, and the letters they have already used");
		System.out.println("If the letter corresponds to the word/phrase, the spaces of the letter will be revealed. If not, player 2 will lose an attempt");
		System.out.println("Player 2 is allowed 10 unsuccessful attempts before thye lose the game");
		System.out.println("If at any point player 2 is confident in guessing the word/phrase, they will enter the letter 1");
		System.out.println("If player 2 does not guess the correct word/phrase, player 2 AUTOMATICALLY LOSES the game and player 1 wins\n");
	}
	
	/*
    * purpose: to convert the word/phrase into asterisks (spaces are not converted into asterisks)
    * @param: word, String, the word/phrase player 1 inputs
    * @return: word, String, the word/phrase that is displayed to the user (letters are converted into asterisks)
    */
	
	public static String displayWord(String word){
        
		/*
		 * Variable Table
		 * wordArray, char, array of the String word
		 * spaceArray, int, stores the indexes of the spaces
		 * counter, int, number of spaces found in the word/phrase
		 * z, int, used in the indexOf function, variable is used as the starting position for the indexOf search
		 * y, int, represents the index value of the space
		 */
		
        char[] wordArray = word.toCharArray(); //converts the String word to an array
            int counter=0;
            
            for(int x=0; x<wordArray.length; x++){
                if(wordArray[x] == ' ') //if the character is a space
                    counter++;
            }
            
            int spaceArray[] = new int[counter];
            int z = -1; //represents where the previous space was
            for(int x=0; x<counter; x++){
                int y = word.indexOf(' ',z+1); //finds the index value of the space
                z = y; 
                spaceArray[x] = y;
            }
            //replaces all characters in array to *
            for(int x=0; x<wordArray.length; x++){
                wordArray[x] = '*';
            }
            
            //replaces the * with a space
            for(int x=0; x<counter; x++){
                wordArray[spaceArray[x]] = ' ';  
            }
            
            word = String.valueOf(wordArray); //converts character array back into string
            
        return word;
    }

   /*
    * purpose: to display player 2's progress throughout the game
    * @param: topic, String, the topic/category of the word/phrase
    *         wordDisplay, String, the word/phrase that player 2 sees
    *         numOfAttempts, int, the number of unsuccessful attempts player 2 has left
    *         lettersUsed, String, the letters player 2 has already used 
    * @return: No Return
    */
	
    public static void display(String topic, String wordDisplay, int numOfAttempts, String lettersUsed){
    	System.out.println("*******************************************************************");
    	System.out.printf("%s%s\n", "Topic: ", topic);
        System.out.printf("%s%s\n", "Word/Phrase: ", wordDisplay);
        System.out.printf("%s%2d\n", "Number of Unsuccessful Attempts left: ", numOfAttempts);
        System.out.printf("%s%s\n", "Letters Used: ", lettersUsed);
        System.out.println("*******************************************************************");
    }
 
    /*
     * purpose: to add a letter to the letters guessed String
     * @param: lettersUsed, String, displays the letters player 2 has guessed 
     * 		   letter, char, the letter player 2 has guessed  
     * @return: newLettersUsed, String, updated string with player 2's letter that they just guessed
     */
    
    public static String lettersGuessed (String lettersUsed, char letter){
    	
    	/*
    	 * Variable Table
    	 * newLettersUsed, String, updates the string with the letter that was just guessed by player 2
    	 */
    	
    	String newLettersUsed;
    	newLettersUsed = lettersUsed+" "+letter;
    	return newLettersUsed;
    }
    
    /*
     * purpose: to determine if the player has guessed the right letter (corresponding to the phrase/word)
     * @param: word, String, the word/phrase player 1 inputs
     * 		   letter, char, the letter player 2 has guessed 
     * @return: letterSearch, boolean, returns true if letter is found
     */
    
    public static boolean letterSearch (String word, char letter){
    	if (word.indexOf(letter) > -1) //if letter corresponds to the word/phrase
    		return true;
    	else
    		return false;
    }
    
    /*
     * purpose: subtracts the number of unsuccessful attempts player 2 has left by 1
     * @param: numOfAttempts, int, the number of unsuccessful attempts player 2 has 
     * @return: int, the the updated number of unsuccessful attempts player 2 has left
     */
    public static int numOfAttempts (int numOfAttempts){
    	numOfAttempts--;
    	return numOfAttempts;
    }
    
    /*
     * purpose: replaces the word displayed to player 2 by updating the word/phrase with the correctly guessed letter
     * @param: word, String, the word/phrase player 1 inputs
     * 		   wordDisplay, String, the word/phrase that player 2 sees
     * 		   letter, char, the letter player 2 has guessed 
     * @return: wordDisplay, String, updated String with the correctly guessed letter replacing the corresponding asterisk
     */
    
    public static String replaceWord (String word, String wordDisplay, char letter){
    	
    	/*
		 * Variable Table
		 * wordDisplayArray, char, array of the String word
		 * charArray, int, stores the indexes of the letter
		 * counter, int, number of letters found in the word/phrase
		 * z, int, used in the indexOf function, variable is used as the starting position for the indexOf search
		 * y, int, represents the index value of the space
		 */
    	
    	 char[] wordDisplayArray = wordDisplay.toCharArray(); //converts the String word to an array
    	 char[] wordArray = word.toCharArray();
         int counter=0;
         
         for(int x=0; x<wordArray.length; x++){
             if(wordArray[x] == letter) //if the character is a letter
                 counter++;
         }
         
         int charArray[] = new int[counter];
         int z = -1; //represents where the previous space was
         for(int x=0; x<counter; x++){
             int y = word.indexOf(letter,z+1); //finds the index value of the space
             z = y; 
             charArray[x] = y;
         }
         
         //replaces the * with the letter
         for(int x=0; x<counter; x++){
             wordDisplayArray[charArray[x]] = letter;  
         }
         
         wordDisplay = String.valueOf(wordDisplayArray); //converts character array back into string
         
     return wordDisplay;
    }
    
    /*
     * purpose: to determine if the phrase/word is solved by player 2
     * @param: wordDisplay, String, the word/phrase that player 2 sees
     * @return: ifSolved, boolean, determines if the word is solved by player 2 
     */
    
    public static boolean ifSolved (String wordDisplay){
    	if (wordDisplay.indexOf('*') == -1)
    		return true;
    	else
    		return false;
    }
    
    /*
     * purpose: to check if the letter player 2 guessed has already been guessed
     * @param: lettersUsed, String, the letters player 2 has guessed 
     *  	   letter, char, the letter player 2 has guessed
     * @return: letterCheck, boolean, determines if the letter player 2 guessed has already been guessed 
     */
    
    public static boolean letterCheck (String lettersUsed, char letter){
    	if (lettersUsed.indexOf(letter) == -1)//if letter is not found in lettersUsed string
    		return true;
    	else
    		return false;
    }
    
    /*
     * purpose: to check if the word/phrase guessed by player 2 matches the word/phrase guessed by player 1
     * @param: wordGuessed, String, word/phrase guessed by player 2
     * 		   word, String, the word/phrase player 1 inputs
     * @return: wordCheck, boolean, to determine if player 2 has guessed the correct word/phrase
     */
    
    public static boolean wordCheck (String wordGuess, String word){
    	if(wordGuess.equalsIgnoreCase(word) == true)//if the word/phrase player 2 guesses matches the word player 1 inputed  
    		return true;
    	else
    		return false;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /*
         * Variable Table
         * <name> <type> <description>
         * word, String, the word/phrase player 1 inputs for the other player to solve
         * topic, String, the topic of the word/phrase
         * ifSolved, boolean, determines if the word is solved by player 2
         * numOfAttempts, int, the number of attempts player 2 has left before they lose the game
         * lettersUsed, String, the letters player 2 has already guessed
         * letter, char, the letter player 2 has guessed
         * wordDisplay, String, the word/phrase player 2 sees (starts off in all asterisks)
         * letterCheck, boolean, determines if the letter player 2 guessed has already been guessed 
         * wordGuess, String, the word/phrase guessed by player 2
         * wordCheck, boolean, to determine if player 2 has guessed the correct word/phrase
         * solveWordAttempt, int, to make sure the else if statement does not run when player 2 attempts to guess the word/phrase
         */
        
        Scanner input = new Scanner(System.in);
        String word;
        String topic;
        boolean ifSolved = false;
        int numOfAttempts = 10;
        String lettersUsed = " ";
        char letter;
        String wordDisplay = null;
        boolean letterCheck;
        String wordGuess;
        boolean wordCheck;
        int solveWordAttempt = 0;
        
        intsructions();
     
        
        System.out.println("\nPlease input your word/phrase");
        word = input.nextLine();
        System.out.println("Please input the topic of the word/phrase");
        topic = input.nextLine();
        
        //creates a large amount of space between the word/phrase player 1 has entered so that player 2 will not be able to see the word/phrase
        for(int x=0; x<100; x++)
        	System.out.println();
        
        wordDisplay = displayWord(word);
        
        while(ifSolved == false && numOfAttempts > 0){ //if player 2 has not solved the word/phrase and still has attempts left
            display(topic, wordDisplay, numOfAttempts, lettersUsed);
            
            do{
            System.out.println("Please input a letter (To guess the word/phrase enter '1')");
            letter = input.next().charAt(0);
            if(letter == '1'){//if player 2 wants to guess word/phrase
            	System.out.println("Enter the word/phrase");
            	input.nextLine();
            	wordGuess = input.nextLine();
            	wordCheck = wordCheck(wordGuess, word);
            	if(wordCheck == true)//if player 2 guessed right
            		ifSolved = true;
            	else
            		numOfAttempts = 0;
            	
            	solveWordAttempt++;
            }
            letterCheck = letterCheck(lettersUsed, letter);
            if (letterCheck == false)//if letter was already guessed
            	System.out.println("This letter was already used. Please input another letter");
            }while(letterCheck == false);
            
            lettersUsed = lettersGuessed(lettersUsed, letter);
            
            if(letterSearch(word, letter) == true){ //letter is found
            	System.out.println("You have guessed the right character");
            	wordDisplay = replaceWord(word, wordDisplay, letter);
            	ifSolved = ifSolved(wordDisplay);
            }
            else if(letterSearch(word, letter) == false && solveWordAttempt == 0){ //letter is not found
            	System.out.println("Sorry, the letter you have guessed does not match the word/phrase");
            	numOfAttempts = numOfAttempts(numOfAttempts);
            }
        }
        
        if (ifSolved == true){//Player 2 solves word/phrase
        	System.out.println("Word/Phrase: "+word);
        	System.out.println("Congratulations! Player 2 has won the game.");
        }
        
        else{//Player 2 does not solve the word/phrase
        	System.out.println("Player 2 has lost. Player 1 wins!");
        	System.out.println("The word/phrase was "+word);
        }
        
        input.close();
        
	}

}
