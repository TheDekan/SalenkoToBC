package com.salenko.utils;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.command.*;
import com.salenko.model.Book;

public class CommandFactory {

	public Command getCommand(AnnotationConfigApplicationContext context, boolean[] doWork, Scanner in) {
		
		//needs to add space to easily make bookRow and command fields
        String inputRow = in.nextLine() + " ";
		
		String bookRow = inputRow.substring(inputRow.indexOf(" "), inputRow.length() - 1);

        // removing space
        if (bookRow.length() > 1) {
            bookRow = bookRow.substring(1);
        }

        String[] rowFields = bookRow.split("\"");
        Book book = new Book();
        Command com;
        
        String command = inputRow.substring(0, inputRow.indexOf(" "));
        switch (command) {
        case ("add"): { 
            com = new AddCommand(rowFields, book, context, bookRow);
            break;
        }
        case ("edit"): {           
            com = new EditCommand(rowFields, book, context, in, bookRow);
            break;
        }
        case ("remove"): { 
            com = new RemoveCommand(rowFields, book, context, in, bookRow);
            break;
        }
        case ("all"): {
            com = new ReadAllCommand(context, bookRow);
            break;
        }
        case ("quit"): { 
            com = new QuitCommand(bookRow, doWork);
            break;
        }
        case ("help"): { 
            com = new HelpCommand(bookRow);
            break;
        }
        default: {            
            com = new UnknownCommand();
        }

        }
		return com;
	}
}
