
public class JSONObject 
{
	private JSONVariable[] theVariables;
	private int currSize;
	
	public JSONObject()
	{
		this.theVariables = new JSONVariable[50];
		this.currSize = 0;
	}
	
	public void addVariable(JSONVariable jv)
	{
		if(this.currSize < this.theVariables.length)
		{
			this.theVariables[this.currSize] = jv;
			this.currSize++;
		}
	}
}
