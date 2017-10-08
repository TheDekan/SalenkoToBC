package com.salenko.command;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.model.Book;
import com.salenko.service.BookService;

public interface Command {

	void execute();

	boolean validate();
	
	
}
