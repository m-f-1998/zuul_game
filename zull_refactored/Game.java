public class Game 
{
    private Parser parser;
    private Player player;
    
    public Game() 
    {        
    	Room startRoom = createRooms();     
    	player = new Player("Matthew", startRoom);
        Player.setPreviousRoom(null);
        parser = new Parser();
    }
    
    private Room createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        pub.addItem(new Item("beer", 0.5));
        pub.addItem(new Item("wine", 0.75));
        lab.addItem(new Item("computer", 30));
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        return outside;
    }

    public void play() 
    {            
        printWelcome();
    
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getName() + ", you are in this location: ");
        System.out.println(Player.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }else if(commandWord.equals("back")){
        	goBack(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    protected void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {

            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = Player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
        	Player.setPreviousRoom(Player.getCurrentRoom());
            Player.enterRoom(nextRoom);
            System.out.println(Player.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;
    }
    
    public void goBack(Command command) {
		if(command.hasSecondWord()){
			System.out.println("Back where??");
			}else if(Player.getPreviousRoom()==null){
			System.out.println("This is the First Room");
				}else{
					Player.enterRoom(Player.getPreviousRoom());
					System.out.println(Player.getLongDescription());
				}	
	}
	
	 public static void main( String[] args )
	   	{
		 	Game game = new Game();
	   		game.play();
	   		
	   	}
}