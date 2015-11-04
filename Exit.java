
public class Exit 
{
	private String name;
	private Room destination;
	
	public Exit(String name, Room destination)
	{
		this.name = name;
		this.destination = destination;
	}

	public String getName() 
	{
		return name;
	}

	public Room getDestination() 
	{
		return destination;
	}
	
	
}
