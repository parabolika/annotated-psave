package com.parabolika.psave;

public class PlayerExample {
	@Saveable(true) private String username, password;
	@Saveable private int x, y;
	private int unsabeable = 12334;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getUnsabeable() {
		return unsabeable;
	}

	public void setUnsabeable(int unsabeable) {
		this.unsabeable = unsabeable;
	}
}
