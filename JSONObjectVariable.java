
public class JSONObjectVariable extends JSONVariable
{
	private JSONObject value;
	
	public JSONObjectVariable(String name, JSONObject value)
	{
		super(name);
		this.value = value;
	}
}
