
public class Room 
{
	private Exit[] theExits;
	private String name;
	private Player thePlayer;
	private int id;
	
	public Room(JSONObject obj)
	{
		this.id = ((JSONNumberVariable)obj.getVariableForName("id")).getValue();
		this.name = ((JSONStringVariable)obj.getVariableForName("name")).getValue();
		//get the exits
		JSONArrayVariable av = (JSONArrayVariable)obj.getVariableForName("exits");
		this.theExits = new Exit[av.getCurrSize()];
		JSONObject[] theObjects = av.getValue();
		for(int i = 0; i < av.getCurrSize(); i++)
		{
			this.theExits[i] = new Exit(theObjects[i]);
		}
	}
	
	
	public int getId() 
	{
		return id;
	}


	public Player removeThePlayer() 
	{
		Player playerToReturn = this.thePlayer;
		this.thePlayer = null;
		return playerToReturn;
	}


	public boolean takeExit(String exitName)
	{
		for(int i = 0; i < this.theExits.length; i++)
		{
			if(this.theExits[i].getName().equalsIgnoreCase(exitName))
			{
				this.thePlayer.displayToUser("Nice Try!!!");
//********************
				//write the code to actually find the Room associated with
				//the destiantionID of this Exit and then add the player to
				//that Room
				//We can find our Rooms in CaveCore now!!!!
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
		for(int i = 0; i < this.theExits.length; i++)
		{
			this.thePlayer.displayToUser(this.theExits[i].getName());
		}
		this.thePlayer.showPrompt();
	}


	public void addExit(String name, Room destination)
	{
//*****************
		//write the code to add an additional Exit to this Room
		//which involves making the theExits array one bucket
		//larger and then creating a new Exit and adding it to
		//the end.
	}
}
