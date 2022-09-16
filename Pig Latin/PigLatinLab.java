//Jason Zhou
//AP CS
//Period 7
// 10/21/20

//establishing libraries
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
public class PigLatinLab {

	//periods are treated as consonants, if they are at the end of a sentence, put them back after translation
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//establishing variables for future reference
		String original = ""; //records the original word
		String translated = ""; //records the translated word
		Scanner input = new Scanner(System.in);
		String fileName = "";
		System.out.println("What File Would You Like To Translate Into Pig Latin(.txt will be automatically added after): "); //prompts the user for a file
		fileName = input.nextLine();
		Scanner fileInput = new Scanner(new File(fileName + ".txt"));
		PrintWriter output = new PrintWriter(new File("piglatin.txt"));
		
		while (fileInput.hasNext()) //loops as long as there is still a word that needs to be scanned in the text file
		{
			String temp = fileInput.next(); //assigns the scanned word to a string variable
			original += temp + " "; //adds it onto the original
			temp = translate(temp) + " "; //translates it into pig latin and saves it
			translated += temp; //adds it onto the translated
			output.print(temp); //prints it to the output file
		}
		output.close(); //closes the output file so that the user can view it
		
		//prints the original and translated text into the console
		System.out.println("\nThe Original Text From The File Was: " + original);
		System.out.println("The Newly Translated Text Now Reads: " + translated);
	}
	
	public static String translate(String word) //translates the words into pig latin and then returns them
	{
		//establishing variables for future reference
		boolean periodRemoved = false;
		boolean semicolonRemoved = false;
		boolean colonRemoved = false;
		boolean commaRemoved = false;
		String cutOff;
		int vowelLocation = -1; //records the value of the first vowel, if it stays as a negative one, then it is word with all consonants
		
		if (word.equals(".")) //if the word is just a period, then immediately return it
			return ".";//returns a period
		
		for (int i = 0; i < word.length(); i++) //finds the location of the first vowel
		{
			//System.out.println(i);
			//System.out.println(word.charAt(i));
			if (i == 0) //if the loop is looking at the first letter, include 'y' as one of the vowels to look for
			{
				if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'y' || word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U' || word.charAt(i) == 'Y')
				{
					//as soon as the loop finds a vowel, break and record the location of the vowel
					vowelLocation = i;
					//System.out.println(vowelLocation);
					i = word.length() - 1;
					
				}	
			}
			else //otherwise only look for the standard vowels(a,e,i,o,u)
			{
				if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U')
				{
					//as soon as the loop finds a vowel, break and record the location of the vowel
					vowelLocation = i;
					//System.out.println(vowelLocation);
					i = word.length() - 1;
				}	
			}
		}
		
		if (word.charAt(word.length() - 1) == '.') //if there was a period at the end, remove it and switch a variable to true
		{
			word = word.substring(0, word.length() - 1);
			periodRemoved = true; //for future reference to add the period back in
		}
		else if (word.charAt(word.length() - 1) == ':')
		{
			word = word.substring(0, word.length() - 1);
			colonRemoved = true; //for future reference to add the colon back in
		}
		else if (word.charAt(word.length() - 1) == ';')
		{
			word = word.substring(0, word.length() - 1);
			semicolonRemoved = true; //for future reference to add the semicolon back in
		}
		else if (word.charAt(word.length() - 1) == ',')
		{
			word = word.substring(0, word.length() - 1);
			commaRemoved = true; //for future reference to add the comma back in
		}
		
		if (vowelLocation > 0) //if the first letter was not a vowel, then take the consonant(s) and add it to the back with 'ay'
		{
			cutOff = word.substring(0, vowelLocation); //take the word without the first two letters
			word = word.substring(vowelLocation) + cutOff + "ay"; //takes off the cut off part and adds it back on and adds 'ay'
		}
		else if (vowelLocation == 0)//if it does, just add 'yay' to the end
			word += "yay";
		else
			word += "ay";
		if (periodRemoved) //if true, add the period back
			word += ".";
		else if (colonRemoved)//if true, add the colon back
			word += ":";
		else if (semicolonRemoved)//if true, add the semicolon back
			word += ";";
		else if (commaRemoved)//if true, add the comma back
			word += ",";
		return word;
	}
}
