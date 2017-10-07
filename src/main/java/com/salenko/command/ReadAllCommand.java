package com.salenko.command;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public class ReadAllCommand implements Command {

	AnnotationConfigApplicationContext context;
	String row;

	/*
	ReadAllCommand(AnnotationConfigApplicationContext context) {
		this.context = context;
	}
	*/
	
	public ReadAllCommand(AnnotationConfigApplicationContext context, String input) {
		this.context = context;
		this.row = input;
	}

	@Override
	public void execute() {
		if (validate()) {
			List<Book> books = ((BookService) context.getBean("service")).findAll();
			for (int i = 0; i < books.size(); i++) {
				System.out.println(i + ". " + books.get(i).getAuthor() + " \"" + books.get(i).getName() + "\"");
			}
		}

	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (this.row.length() != 0) {
			System.out.println("Invalid row, input as example: all");
			valid = false;
		}
		return valid;
	}

}
