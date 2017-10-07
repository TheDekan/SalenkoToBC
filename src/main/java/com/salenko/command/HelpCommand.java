package com.salenko.command;

public class HelpCommand implements Command {

	String row;

	public HelpCommand(String input) {
		this.row = input;
	}

	@Override
	public void execute() {
		if (validate()) {
			System.out.println("Available commands: add, edit, remove, all, quit.");
			System.out.println("To see commands examples - enter them incorrect.");
		}
	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (this.row.length() != 0) {
			System.out.println("Invalid row, input as example: help");
			valid = false;
		}
		return valid;
	}
}
