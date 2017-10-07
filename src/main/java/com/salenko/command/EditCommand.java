package com.salenko.command;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public class EditCommand extends Command {

	@Override
	public void doWork(String[] rowFields, Book book, AnnotationConfigApplicationContext context, Scanner in) {
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
					String readNumber = in.nextLine();
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
			book.setId(books.get(number).getId());
			System.out.println("enter author");
			book.setAuthor(in.nextLine());
			System.out.println("enter book name");
			book.setName(in.nextLine());
			((BookService) context.getBean("service")).update(book);
		}
	}

}
