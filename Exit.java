
public class Exit 
{
	private String name;
	private int destinationID;
	
	public Exit(JSONObject obj)
	{
		this.name = ((JSONStringVariable)obj.getVariableForName("name")).getValue();
		this.destinationID = ((JSONNumberVariable)obj.getVariableForName("destinationID")).getValue();
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
