package com.parabolika.psave;

import com.parabolika.psave.backing.TextBacking;

public class SaveLoadExample {
	public static void main(String[] args) {
		// Initialize an object for the values to be stored in, and set
		// some default values to be saved.
		PlayerExample player = new PlayerExample();
		player.setUsername("someusername");
		player.setPassword("123456789");
		player.setX(45);
		player.setY(54);
		
		// First, initialize a new backing, of whichever type you want.  In
		// the future, hopefully there will be more backings available (SQL
		// or protobufs, anyone?).  Currently, TextBacking is the only type.
		// TextBacking, specifically, takes one argument: the base path of
		// where the saved objects are stored.
		TextBacking backing = new TextBacking("saves");
		
		// Next, we initialize a new 'Saver'.  This manages the low-level
		// saving mechanisms, so you don't have to.  It takes one argument:
		// the backing mechanism to be used.
		Saver<PlayerExample> saver = new Saver<PlayerExample>(backing);
		
		// We then call Saver#save, which also takes one argument: the
		// object to write the values from.  In this case, TextBacking
		// creates, or overwrites, a text file, with the values marked
		// by the @Saveable annotation.
		saver.save(player);
		
		// For loading, first initialize the object to load values into.
		player = new PlayerExample();
		
		// Then construct a Loader, again, passing the backing store to it.
		Loader<PlayerExample> loader = new Loader<PlayerExample>(backing);
		
		// Now call Loader#load, which takes an instance to load the values into
		// and a key, and all your values are automatically loaded into this new
		// instance.  As previously mentioned, TextBacking takes the base directory.
		loader.load(player, "someusername");
		
		// And, finally, ensure that the values loaded are correct.
		assert player.getUsername().equals("someusername");
		assert player.getPassword().equals("123456789");
		assert player.getX() == 45;
		assert player.getY() == 54;
	}
}
