package com.salenko.command;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public class AddCommand implements Command {

	String[] rowFields;
	Book book;
	AnnotationConfigApplicationContext context;
	String row;

	public AddCommand(String[] rowFields, Book book, AnnotationConfigApplicationContext context, String input) {
		this.rowFields = rowFields;
		this.book = book;
		this.context = context;
		this.row = input;
	}

	@Override
	public void execute() {
		if (validate()) {
			book.setAuthor(rowFields[0]);
			book.setName(rowFields[1]);
			((BookService) context.getBean("service")).insert(book);
		}
	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (this.row.length() == 0 || this.row.charAt(this.row.length() - 1) != '\"' || rowFields.length != 2) {
			System.out.println("Invalid row, input as example: add author_name\"book_name\"");
			valid = false;
		}
		if (rowFields[0].length() > 50) {
			System.out.println("Author's name is to long.");
			valid = false;
		}
		if (rowFields[1].length() > 50) {
			System.out.println("Book's name is to long.");
			valid = false;
		}
		return valid;
	}

}
