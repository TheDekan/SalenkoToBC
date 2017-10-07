package com.salenko.command;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public class AddCommand extends Command{

	@Override
	public void doWork(String[] rowFields, Book book, AnnotationConfigApplicationContext context, Scanner scanner) {
		book.setAuthor(rowFields[0]);
        book.setName(rowFields[1]);
        ((BookService) context.getBean("service")).insert(book);
	}

}
