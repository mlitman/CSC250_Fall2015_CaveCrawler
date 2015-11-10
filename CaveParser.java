import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CaveParser 
{
	private String theJSON;
	private int currPos;
	
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
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	private void advanceToNextChar(char c)
	{
		while(this.theJSON.charAt(this.currPos) != c)
		{
			this.currPos++;
		}
	}
	
	private void advancePastNextChar(char c)
	{
		this.advanceToNextChar(c);
		this.currPos++;
	}
	
	private JSONVariable getVariable()
	{
		int pos;
		
		//read in name
		this.advancePastNextChar('"');
		pos = this.currPos; //remember the pos of the beginning of the name
		this.advanceToNextChar('"');
		String name = this.theJSON.substring(pos, this.currPos);
		
		//move to the separator
		this.advanceToNextChar(':');
		
		//read in value
		this.advancePastNextChar('"');
		pos = this.currPos; //remember the pos of the beginning of the value
		this.advanceToNextChar('"');
		String value = this.theJSON.substring(pos, this.currPos);
		
		JSONVariable theVariable = new JSONVariable(name, value);
		return theVariable;
	}
	
	public JSONObject parse()
	{
		JSONObject theObject = null;
		this.currPos = 0;
		while(this.currPos < this.theJSON.length())
		{
			this.advanceToNextChar('{');
			theObject = new JSONObject();
			theObject.addVariable(this.getVariable());
			if(this.exists(','))
			{
				this.advanceToNextChar(',');
				theObject.addVariable(this.getVariable());
			}
			//How do we get an unlimited number of variables? (ie 50 in our case)
		}
		
		return theObject;
	}
}
