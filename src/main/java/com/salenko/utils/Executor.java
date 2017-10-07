package com.salenko.utils;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.command.*;
import com.salenko.model.Book;

public class Executor {

	public void execute(AnnotationConfigApplicationContext context, boolean[] doWork, Scanner in) {
		
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
            if (bookRow.length() == 0 || bookRow.charAt(bookRow.length() - 1) != '\"' || rowFields.length != 2) {
                System.out.println("Invalid row, input as example: add author_name\"book_name\"");break;}
            if (rowFields[0].length() > 50) {System.out.println("Author's name is to long.");break;}
            if (rowFields[1].length() > 50) {System.out.println("Book's name is to long.");break;}

            com = new AddCommand();
            com.doWork(rowFields, book, context, in);
            break;
        }
        case ("edit"): { 
            if (bookRow.length() == 0 || rowFields.length != 1) {System.out.println("Invalid row, input as example: edit book_name");break;}
            if (rowFields[0].length() > 50) {
                System.out.println("Book's name is to long.");
                break;
            }
            
            com = new EditCommand();
            com.doWork(rowFields, book, context, in);
            break;
        }
        case ("remove"): { 
            if (bookRow.length() == 0 || rowFields.length != 1) {System.out.println("Invalid row, input as example: remove book_name");break;}
            if (rowFields[0].length() > 50) {System.out.println("Book's name is to long.");break;}

            com = new RemoveCommand();
            com.doWork(rowFields, book, context, in);
            break;
        }
        case ("all"): { 
            if (bookRow.length() != 0) {System.out.println("Invalid row, input as example: all");break;}

            Command.readAll(context);
            break;
        }
        case ("quit"): { 
            if (bookRow.length() != 0) {System.out.println("Invalid row, input as example: quit");break;}

            Command.doQuit(doWork);
            break;
        }
        case ("help"): { 
            if (bookRow.length() != 0) {System.out.println("Invalid row, input as example: help");break;}

            Command.callHelp();
            break;
        }
        default: {
            System.out.println("Unknown command. For help type help.");
        }

        }
	}
}
