import static org.junit.Assert.*;

import org.junit.Test;

public class BackTest
{
    public BackTest()
    {
    }

	@Test
	public void testBack()
	{
		Game game = new Game();
		Room start = Player.getCurrentRoom();
		
		Command command = new Command("go", "east");
		game.goRoom( command );
		
		assertTrue(!start.equals( Player.getCurrentRoom()));
		
		Command back = new Command("back", null);
		game.goBack(back);

		assertTrue(start.equals( Player.getCurrentRoom()));
		
	}
}