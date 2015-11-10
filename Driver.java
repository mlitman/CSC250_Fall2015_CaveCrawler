import java.io.File;
import java.util.Scanner;


public class Driver 
{
	public static void main(String[] args)
	{
		CaveParser cp = new CaveParser("simpleJSON");
		cp.parse();
		/*
		Room caveEntrance = new Room("The Cave Entrance");
		Room room2 = new Room("Room #2");
		caveEntrance.addExit("north", room2);
		room2.addExit("south", caveEntrance);
		
		Player thePlayer = new Player("The Follower");
		caveEntrance.addThePlayer(thePlayer);
		*/
	}
	
}
