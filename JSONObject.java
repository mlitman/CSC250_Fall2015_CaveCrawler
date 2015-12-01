
public class JSONObject 
{
	private JSONVariable[] theVariables;
	private int currSize;
	
	public JSONObject()
	{
		this.theVariables = new JSONVariable[50];
		this.currSize = 0;
	}
	
	
	public JSONVariable getVariableForName(String name)
	{
		for(int i = 0; i < this.currSize; i++)
		{
			if(this.theVariables[i].getName().equals(name))
			{
				return this.theVariables[i];
			}
		}
		return null;
	}


	public int getCurrSize() {
		return currSize;
	}


	public void addVariable(JSONVariable jv)
	{
		if(this.currSize < this.theVariables.length)
		{
			this.theVariables[this.currSize] = jv;
			this.currSize++;
		}
	}
	
	public void display()
	{
		System.out.println("JSON Object - Num Vars: " + this.currSize);
		for(int i = 0; i < this.currSize; i++)
		{
			this.theVariables[i].display();
		}
	}
	
	public String exportToJSON()
	{
		String answer = "{";
		for(int i = 0; i < this.currSize; i++)
		{
			answer += this.theVariables[i].exportToJSON();
			if(i != this.currSize-1)
			{
				answer += ",";
			}
		}
		answer += "}";
		return answer;
	}
}
