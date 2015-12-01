import java.io.File;
import java.util.Scanner;


public class Driver 
{
	public static void main(String[] args)
	{
		Cave theCave = new Cave("Level 1", "caveJSON");
		Player thePlayer = new Player("Prof Locklair");
		theCave.addPlayer(thePlayer);
	}
	
}
