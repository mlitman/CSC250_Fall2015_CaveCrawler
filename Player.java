import java.util.Scanner;


public class Player 
{
	private String name;
	private Scanner input;
	private Room currentRoom;
	
	public Player(String name)
	{
		this.name = name;
		this.input = new Scanner(System.in);
		this.currentRoom = null;
	}
	
	public void setCurrentRoom(Room r)
	{
		this.currentRoom = r; 
	}
	
	public void displayToUser(String msg)
	{
		System.out.println(msg);
	}
	
	public void showPrompt()
	{
		System.out.print("> ");
		String userResponse = this.input.nextLine();
		System.out.println(userResponse);
		
		//*******
		//We need to process the players command to move to a new room
		this.currentRoom.takeExit(userResponse);
		
	}
}
