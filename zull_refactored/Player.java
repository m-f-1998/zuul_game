public class Player {
		private String name;
		private static Room currentRoom;
		private static Room previousRoom;
		

	public Player(String playerName, Room currentLocation){
		this.name = playerName;
		currentRoom=currentLocation;
	}
	
	public static Room getCurrentRoom(){
		return currentRoom;
	}
	
	public static Room enterRoom (Room room){
		currentRoom = room;
		return currentRoom;
	}
	
	public static Room getPreviousRoom(){
		return previousRoom;
	}
	
	public static Room setPreviousRoom (Room room){
		previousRoom = room;
		return previousRoom;
	}
	
	public static String getLongDescription(){
		String returnString = currentRoom.getLongDescription();
		return returnString; 
	}

	public  String getName() {
		return name;
	}
}
