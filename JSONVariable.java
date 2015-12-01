
public abstract class JSONVariable 
{
	protected String name;
	
	public JSONVariable(String name)
	{
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}


	abstract void display();
	
	abstract String exportToJSON();
}
