
public class JSONObjectVariable extends JSONVariable
{
	private JSONObject value;
	
	public JSONObjectVariable(String name, JSONObject value)
	{
		super(name);
		this.value = value;
	}

	
	public JSONObject getValue() {
		return value;
	}


	void display() 
	{
		System.out.println(this.name +" -> ");
		this.value.display();
	}

	@Override
	String exportToJSON() 
	{
		String answer = "\"" + this.name + "\":";
		answer += this.value.exportToJSON();
		return answer;
	}
}
