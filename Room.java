
public class Room 
{
	private Exit[] theExits;
	private int currNumberOfExits;
	private String name;
	private Player thePlayer;
	
	public Room(String name)
	{
		this.name = name;
		this.thePlayer = null;
		this.theExits = new Exit[4];
		this.currNumberOfExits = 0;
	}
	
	
	public Player removeThePlayer() 
	{
		Player playerToReturn = this.thePlayer;
		this.thePlayer = null;
		return playerToReturn;
	}


	public boolean takeExit(String exitName)
	{
		for(int i = 0; i < this.currNumberOfExits; i++)
		{
			if(this.theExits[i].getName().equalsIgnoreCase(exitName))
			{
				this.theExits[i].getDestination().addThePlayer(this.removeThePlayer());
				return true;
			}
		}
		return false;
	}
	
	public void addThePlayer(Player thePlayer) 
	{
		//let the player know about the room they are entering
		thePlayer.setCurrentRoom(this);
		
		//set this room's player to thePlayer and display details to thePlayer
		this.thePlayer = thePlayer;
		this.thePlayer.displayToUser("You have entered: " + this.name);
		this.thePlayer.displayToUser("Possible Exits: ");
		for(int i = 0; i < this.currNumberOfExits; i++)
		{
			this.thePlayer.displayToUser(this.theExits[i].getName());
		}
		this.thePlayer.showPrompt();
	}


	public void addExit(String name, Room destination)
	{
		this.theExits[this.currNumberOfExits] = new Exit(name, destination);
		this.currNumberOfExits++;
	}
}
