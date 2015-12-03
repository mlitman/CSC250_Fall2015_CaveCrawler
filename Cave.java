
public class Cave 
{
	private String name;
	private int startRoomID;
	
	public Cave(String name, String filename)
	{
		this.name = name;
		CaveParser cp = new CaveParser(filename);
		JSONObject theObject = cp.parse();
		//this.theObject.display();
		
		//get the startRoomID
		this.startRoomID = ((JSONNumberVariable)theObject.getVariableForName("startRoomID")).getValue();
		
		//get the Rooms array
		JSONArrayVariable av = (JSONArrayVariable)theObject.getVariableForName("rooms");
		//av.display();
		
		//build all of the rooms
		CaveCore.theRooms = new Room[av.getCurrSize()];
		JSONObject[] theObjects = av.getValue();
		for(int i = 0; i < av.getCurrSize(); i++)
		{
			CaveCore.theRooms[i] = new Room(theObjects[i]);
		}
		
		//Let CaveCore know about this Cave
		CaveCore.theCave = this;
	}
	
	public JSONObject toJSON()
	{
		JSONObject theObj = new JSONObject();
		theObj.addVariable(new JSONNumberVariable("startRoomID", this.startRoomID));
		JSONArrayVariable theRooms = new JSONArrayVariable("rooms");
		//fill our theRooms variable
		for(Room r : CaveCore.theRooms)
		{
			theRooms.addJSONObject(r.getJSONObject());
		}
		theObj.addVariable(theRooms);
		return theObj;
	}
	
	public void addPlayer(Player p)
	{
		//find the Room object in our array of Rooms that has an
		//id that matches the startRoomID of this cave.  Then
		//add the Player to that Room
		for(int i = 0; i < CaveCore.theRooms.length; i++)
		{
			if(CaveCore.theRooms[i].getId() == this.startRoomID)
			{
				CaveCore.theRooms[i].addThePlayer(p);
				return;
			}
		}
	}
}
