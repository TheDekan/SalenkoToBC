package com.salenko.command;

public class UnknownCommand implements Command {

	@Override
	public void execute() {
		System.out.println("Unknown command. For help type help.");
	}

	@Override
	public boolean validate() {
		return false;
	}

}
