package com.salenko.command;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public class RemoveCommand implements Command {

	String[] rowFields;
	Book book;
	AnnotationConfigApplicationContext context;
	Scanner scanner;
	String row;

	public RemoveCommand(String[] rowFields, Book book, AnnotationConfigApplicationContext context, Scanner scanner, String row) {
		this.rowFields = rowFields;
		this.book = book;
		this.context = context;
		this.scanner = scanner;
		this.row = row;
	}

	@Override
	public void execute() {
		if (validate()) {
			book.setName(rowFields[0]);
			List<Book> books = ((BookService) context.getBean("service")).findAllByName(book.getName());
			for (int i = 0; i < books.size(); i++) {
				System.out.println(i + ". " + books.get(i).getAuthor() + " \"" + books.get(i).getName() + "\"");
			}
			if (books.size() == 0) {
				System.out.println("There is no books with name " + book.getName());
			} else {
				int number = 0;
				if (books.size() > 1) {
					boolean invalidNumber = true;
					while (invalidNumber) {
						invalidNumber = false;
						System.out.println("enter book number");
						String readNumber = scanner.nextLine();
						try {
							number = Integer.parseInt(readNumber);
						} catch (NumberFormatException e) {
							invalidNumber = true;
							System.out.println("Invalid row, input number.");
							continue;
						}
						if (number > books.size() - 1) {
							invalidNumber = true;
							System.out.println("Enter number of list item.");
						}
					}
				}
				((BookService) context.getBean("service")).delete(books.get(number).getId());
			}
		}
	}

	@Override
	public boolean validate() {
		boolean valid = true;
		if (this.row.length() == 0 || rowFields.length != 1) {
			System.out.println("Invalid row, input as example: remove book_name");
			valid = false;
		}
		if (rowFields[0].length() > 50) {
			System.out.println("Book's name is to long.");
			valid = false;
		}
		return valid;
	}

}
