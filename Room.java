
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
	
	public Room(String name, int id)
	{
		this.name = name;
		this.id = id;
		this.thePlayer = null;
		this.theExits = new Exit[0];
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject theObj = new JSONObject();
		theObj.addVariable(new JSONNumberVariable("id", this.id));
		theObj.addVariable(new JSONStringVariable("name", this.name));
		JSONArrayVariable theExits = new JSONArrayVariable("exits");
		for(Exit e : this.theExits)
		{
			theExits.addJSONObject(e.getJSONObject());
		}
		theObj.addVariable(theExits);
		return theObj;
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
				for(Room r : CaveCore.theRooms)
				{
					if(r.getId() == this.theExits[i].getDestinationID())
					{
						r.addThePlayer(this.thePlayer);
						this.thePlayer = null;
						return true;
					}
				}
			}
		}
		this.thePlayer.displayToUser("Destination Room not found!!!");
		return false;
	}
	
	public void displayDetailsToUser()
	{
		this.thePlayer.displayToUser("You have entered: " + this.name);
		this.thePlayer.displayToUser("Possible Exits: ");
		for(int i = 0; i < this.theExits.length; i++)
		{
			this.thePlayer.displayToUser(this.theExits[i].getName());
		}
		this.thePlayer.showPrompt();
	}
	
	public void addThePlayer(Player thePlayer) 
	{
		//let the player know about the room they are entering
		thePlayer.setCurrentRoom(this);
		
		//set this room's player to thePlayer and display details to thePlayer
		this.thePlayer = thePlayer;
		this.displayDetailsToUser();
	}


	public void addExit(String name, int destinationID)
	{
		Exit[] tempExits = new Exit[this.theExits.length+1];
		for(int i = 0; i < this.theExits.length; i++)
		{
			tempExits[i] = this.theExits[i];
		}
		tempExits[tempExits.length-1] = new Exit(name, destinationID);
		this.theExits = tempExits;
	}
}
