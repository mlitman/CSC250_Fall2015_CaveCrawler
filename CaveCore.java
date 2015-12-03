
public class CaveCore 
{
	static Cave theCave;
	static Room[] theRooms;
	
	static int addRoomToCave(String roomName, String returnExitName, int returnRoomID)
	{
		Room[] tempRooms = new Room[CaveCore.theRooms.length+1];
		for(int i = 0; i < CaveCore.theRooms.length; i++)
		{
			tempRooms[i] = CaveCore.theRooms[i];
		}
		//unique room id assumes that we also build rooms with linear ids
		tempRooms[tempRooms.length-1] = new Room(roomName, CaveCore.theRooms.length);
		tempRooms[tempRooms.length-1].addExit(returnExitName, returnRoomID);
		CaveCore.theRooms = tempRooms;
		return CaveCore.theRooms[CaveCore.theRooms.length-1].getId();
	}
}
