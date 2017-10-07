package com.salenko.command;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public abstract class Command {

	public abstract void doWork(String[] rowFields, Book book, AnnotationConfigApplicationContext context, Scanner scanner);

	public static void callHelp() {
		System.out.println("Available commands: add, edit, remove, all, quit.");
		System.out.println("To see commands examples - enter them incorrect.");
	}

	public static void doQuit(boolean[] workOn) {
		workOn[0] = false;
		System.out.println("Bye!");
	}

	public static void readAll(AnnotationConfigApplicationContext context) {
		List<Book> books = ((BookService) context.getBean("service")).findAll();
		for (int i = 0; i < books.size(); i++) {
			System.out.println(i + ". " + books.get(i).getAuthor() + " \"" + books.get(i).getName() + "\"");
		}
	}

}
