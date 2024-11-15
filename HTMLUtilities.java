/**
 *	Utilities for handling HTML
 *
 *	@author	Sai Avula
 *	@since	1 November 2024
 */
public class HTMLUtilities {
	boolean isComment;
	boolean isPreformatted;
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars" 
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		str = str + "  ";
		String[] result = new String[10000];
		for(int i = 0; i < 10000; i ++)
		{
			result[i] = "";
		}
		int index = 0;
		boolean bool = false;
		boolean bool2 = false;
		boolean bool3 = false;
		// return the correctly sized array
		
		for(int i = 0; i < str.length(); i ++)
		{
			char let = str.charAt(i);
			
			if (str.charAt(i) == '<') 
			{
				bool = true;
			}
			else if ((Character.isLetterOrDigit(let) || let == '-' || let == '.' || let == ',' || let == ';' || let == ':' || let == '?' || let == '!' 
			|| let == '=' || let == '&'|| let == '~'|| let == '+') && !bool2 && !bool) 
			{
				bool2 = true;
			}
			else if (str.charAt(i) == '>' && !isPreformatted) 
			{
				bool = false;
				result[index] += '>';
				index++;
				char lastLet = ' ';
				if(result[index-1].length() > 0)lastLet = result[index-1].charAt(result[index-1].length()-1);
				if (lastLet == '.' || lastLet == ',' || lastLet == ';' || lastLet == ':' || lastLet == '?' || lastLet == '!' || lastLet == '=' || lastLet == '&'
				|| lastLet == '~'|| lastLet == '+'|| lastLet == '-') 
				{
					String temp = result[index-1];
					result[index-1] = "";
					for(int n = 0; n < temp.length()-1; n++)
					{
						result[index-1] += temp.charAt(n);
					}
					result[index] += lastLet;
					index++;	
				}
				if (result[index-1] == "") 
				{
					index--;
				}
			}
			else if(bool2 && str.charAt(i) == ' ' && !isPreformatted) 
			{
				bool2 = false;
				index++;
				char lastLet = ' ';
				if(result[index-1].length() > 0) lastLet = result[index-1].charAt(result[index-1].length()-1);
				if ((lastLet == '.' || lastLet == ',' || lastLet == ';' || lastLet == ':' || lastLet == '?' || lastLet == '!' || lastLet == '=' || lastLet == '&'
				|| lastLet == '~'|| lastLet == '+'|| lastLet == '-') && result[index-1].trim().length() > 1) 
				{
					String temp = result[index-1];
					result[index-1] = "";
					for(int n = 0; n < temp.length()-1; n++)
					{
						result[index-1] += temp.charAt(n);
					}
					result[index] += lastLet;
					index++;	
				}
				if (result[index-1] == "") 
				{
					index--;
				}
			}
			if (bool && bool2 && !isPreformatted) 
			{
				bool2 = false;
				index++;
				char lastLet = ' ';
				if(result[index-1].length() > 0)lastLet = result[index-1].charAt(result[index-1].length()-1);
				if (lastLet == '.' || lastLet == ',' || lastLet == ';' || lastLet == ':' || lastLet == '?' || lastLet == '!' || lastLet == '=' || lastLet == '&'
				|| lastLet == '~'|| lastLet == '+'|| lastLet == '-') 
				{
					String temp = result[index-1];
					result[index-1] = "";
					for(int n = 0; n < temp.length()-1; n++)
					{
						result[index-1] += temp.charAt(n);
					}
					result[index] += lastLet;
					index++;	
				}
				if (result[index-1] == "") 
				{
					index--;
				}
			}

			if (result[index].equals("<!--")) 
			{
				isComment = true;
				result[index] = "";
			}

			else if(str.length() > i + 2 && str.charAt(i) == '-' && str.charAt(i + 1) == '-' && str.charAt(i + 2) == '>' && isComment)
			{
				isComment = false;
				bool = false;
				bool2 = false;
				i = i +2;
			}

			if(isPreformatted)
			{
				result[index] += str.charAt(i) + "";
			}
			else if((bool || bool2 || bool3) && !isComment)
			{
				result[index] += str.charAt(i) + "";
			}

			if (result[index].equals("<pre") && str.charAt(i+1) == '>')
			{

				isPreformatted = true;	
			}
			if (i < str.length()-6 && str.charAt(i) == '<' && str.charAt(i+1) == '/' && str.charAt(i+2) == 'p' && str.charAt(i+3) == 'r'&& str.charAt(i+4) == 'e'&& str.charAt(i+5) == '>') 
			{
				isPreformatted = false;
				bool = false;
				bool2 = false;
				result[index] += str.substring(i, i+6);
				i = i + 7;
			}

		}
		for(int i = 0; i < 10000; i ++)
		{
			result[i] = result[i].trim();
		}

		return result;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		 if (tokens == null) return;
        for (int a = 0; a < tokens.length; a++) {
            if (a % 5 == 0) System.out.print("\n  ");
            if(tokens[a] != "")System.out.print("[token " + a + "]: " + tokens[a] + " ");
            if(tokens[a+1] == "")
                a = tokens.length;
        }
		System.out.println();
        
	}
}