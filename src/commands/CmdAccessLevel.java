package commands;

public enum CmdAccessLevel {
	
	PLAYER_ONLY(0),
	PLAYER_AND_CONSOLE(1),
	CONSOLE_ONLY(2);
	
	int value;
	
	CmdAccessLevel(int val){
		value = val;
	}
	
	
}
