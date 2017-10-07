package com.salenko.Application;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.salenko.command.Command;
import com.salenko.configuration.HibernateConfiguration;
import com.salenko.utils.CommandFactory;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        Scanner in = new Scanner(System.in);
        boolean[] workOn = new boolean[1];
        workOn[0] = true;

        try {
            while (workOn[0]) {
                System.out.println("Please, enter the command:");
                
                CommandFactory executor = new CommandFactory();
                Command com = executor.getCommand(context, workOn, in);
                com.execute();
                
                System.out.println();
            }
        } finally {
            in.close();
            context.close();
        }
    }

}
