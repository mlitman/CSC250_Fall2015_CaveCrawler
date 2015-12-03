
public class Exit 
{
	private String name;
	private int destinationID;
	
	public Exit(JSONObject obj)
	{
		this.name = ((JSONStringVariable)obj.getVariableForName("name")).getValue();
		this.destinationID = ((JSONNumberVariable)obj.getVariableForName("destinationID")).getValue();
	}

	public Exit(String name, int destinationID)
	{
		this.name = name;
		this.destinationID = destinationID;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject theObj = new JSONObject();
		theObj.addVariable(new JSONStringVariable("name", this.name));
		theObj.addVariable(new JSONNumberVariable("destinationID", this.destinationID));
		return theObj;
	}
	
	public String getName() 
	{
		return name;
	}

	public int getDestinationID() 
	{
		return destinationID;
	}
}
