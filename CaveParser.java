import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CaveParser 
{
	private String theJSON;
	private int currPos;
	
	//builds a JSON String given a fileName
	public CaveParser(String fileName)
	{
		Scanner input;
		this.theJSON = "";
		try 
		{
			input = new Scanner(new File(System.getProperty("user.dir") + "/src/" + fileName));
			while(input.hasNextLine())
			{
				this.theJSON = this.theJSON + input.nextLine();
			}
			this.theJSON = this.theJSON.trim();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//returns true if c exists from currPos as a starting point
	private boolean exists(char c)
	{
		for(int i = this.currPos; i < this.theJSON.length(); i++)
		{
			if(this.theJSON.charAt(i) == c)
			{
				return true;
			}
		}
		return false;
	}
	
	//moves currPos to the position of the next c in theJSON
	private void advanceToNextChar(char c)
	{
		while(this.currPos < this.theJSON.length() && 
				this.theJSON.charAt(this.currPos) != c)
		{
			this.currPos++;
		}
	}
	
	//moves currPos one position past the position of the next c in theJSON
	private void advancePastNextChar(char c)
	{
		this.advanceToNextChar(c);
		this.currPos++;
	}
	
	//returns the type of value we need to read in
	private String getValueType()
	{
		int pos = this.currPos;
		while(this.theJSON.charAt(pos) == ' ')
		{
			pos++;
		}
		if(this.theJSON.charAt(pos) == '"')
		{
			return "String";
		}
		else if(this.theJSON.charAt(pos) == '{')
		{
			return "Object";
		}
		else if(this.theJSON.charAt(pos) == '[')
		{
			return "Array";
		}
		else
		{
			//I'm looking at a number
			return "Number";
		}
	}
	
	//gets the next value as a String
	private String getStringValue()
	{
		//read in value
		this.advancePastNextChar('"');
		int pos = this.currPos; //remember the pos of the beginning of the value
		this.advanceToNextChar('"');
		String value = this.theJSON.substring(pos, this.currPos);
		return value;
	}
	
	//gets the next value as an int
	private int getNumberValue()
	{
		//read in value
		String answer = "";
		while(this.theJSON.charAt(this.currPos) != ',' && 
				this.theJSON.charAt(this.currPos) != '}')
		{
			answer += this.theJSON.charAt(this.currPos);
			this.currPos++;
		}
		answer = answer.trim();
		return Integer.parseInt(answer);
	}
	
	//return true if a occurs next before b
	private boolean charBeforechar(char a, char b)
	{
		for(int i = this.currPos; i < this.theJSON.length(); i++)
		{
			if(this.theJSON.charAt(i) == b)
			{
				return false;
			}
			else if(this.theJSON.charAt(i) == a)
			{
				return true;
			}
		}
		return false;
	}
	
	private JSONObject getObjectValue()
	{	
		while(this.currPos < this.theJSON.length())
		{
			this.advanceToNextChar('{');
			JSONObject theObject = new JSONObject();
			theObject.addVariable(this.getVariable());
			
			while(this.charBeforechar(',', '}'))
			{
				this.advanceToNextChar(',');
				theObject.addVariable(this.getVariable());
			}
			this.advancePastNextChar('}');
			return theObject;
		}
		
		//appeases Java that this function always returns a value
		//we know this line SHOULD never be called
		return null;
	}
	
	//builds a name/value pair and returns a JSONVariable object
	private JSONVariable getVariable()
	{
		int pos;
		
		//read in name
		this.advancePastNextChar('"');
		pos = this.currPos; //remember the pos of the beginning of the name
		this.advanceToNextChar('"');
		String name = this.theJSON.substring(pos, this.currPos);
		
		//move to the separator
		this.advancePastNextChar(':');
		
		//What kind of value do we need to read?
		String type = this.getValueType();
		
		if(type.equals("String"))
		{
			JSONStringVariable theVariable = new JSONStringVariable(name, this.getStringValue());
			return theVariable;
		}
		else if(type.equals("Object"))
		{
			JSONObject theObject = this.getObjectValue();
			JSONObjectVariable theVariable = new JSONObjectVariable(name, theObject);
			return theVariable;
			//we need to get this into a JSONVariable now
		}
		else if(type.equals("Array"))
		{
			JSONArrayVariable theVariable = new JSONArrayVariable(name);
			while(this.currPos < this.theJSON.length())
			{
				this.advanceToNextChar('[');
				theVariable.addJSONObject(this.getObjectValue());
				
				while(this.charBeforechar(',', ']'))
				{
					this.advanceToNextChar(',');
					theVariable.addJSONObject(this.getObjectValue());
				}
				this.advancePastNextChar(']');
				return theVariable;
			}
			
		}
		
		else if(type.equals("Number"))
		{
			JSONNumberVariable theVariable = new JSONNumberVariable(name, this.getNumberValue());
			return theVariable;
		}
		return null;
	}
	
	//parses a theJSON string and ultimately produces a single JSONObject
	public JSONObject parse()
	{
		this.currPos = 0;
		JSONObject theObject = this.getObjectValue();
		return theObject;
	}
}
