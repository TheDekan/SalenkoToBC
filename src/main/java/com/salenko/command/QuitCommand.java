package com.salenko.command;

public class QuitCommand implements Command {

	String row;
	boolean[] doWork;

	// public QuitCommand() {}

	public QuitCommand(String input, boolean[] doWork) {
		this.row = input;
		this.doWork = doWork;
	}

	@Override
	public void execute() {
		if (validate()) {
			this.doWork[0] = false;
			System.out.println("Bye!");
		}

	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (this.row.length() != 0) {
			System.out.println("Invalid row, input as example: quit");
			valid = false;
		}
		return valid;
	}

}
