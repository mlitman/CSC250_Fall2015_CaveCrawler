
public class JSONStringVariable extends JSONVariable
{
	private String value;
	
	public JSONStringVariable(String name, String value)
	{
		super(name);
		this.value = value;
	}
	public void display()
	{
		System.out.println("String : " + this.name + " -> " + this.value);
	}
}
