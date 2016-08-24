/*
 * This program was written to help me alphabetize my library. It takes user 
 * input and adds it to a list which is then sorted and saved to a text file.
 */
package alphabetizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import javax.swing.*;

/**
 * @author Kevin
 */
public class Alphabetizer {

    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<String>();
        File bookList = new File("books.txt");

        JFrame frame = new AlfUI();
        frame.setTitle("Alphabetizer by Kevin Lai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JTextArea label = AlfUI.jTextArea1;
        JTextField input = AlfUI.jTextField2;
        JButton button = AlfUI.jButton1;

        // Prints initial list
        try {
            Scanner scanner = new Scanner(bookList);
            while (scanner.hasNextLine()) {
                books.add(scanner.nextLine());
            }
        } catch (Exception exception) {
            label.append(exception.toString() + '\n');
        }
            label.append("Current list:\n--------------------\n");
        for (int i = 0; i < books.size(); ++i) {
            label.append(books.get(i) + '\n');
        }

        // Handles input from user
        label.append("\nEnter the name of a book:\n");
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String response = input.getText();
                input.setText("");
                if (response.startsWith("-")){
                    books.remove(response.substring(1));
                } else {
                    books.add(response);
                }
                label.append(response + '\n');
            }
        });

        // Alphabetizes list and saves it to text file
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Collections.sort(books, String.CASE_INSENSITIVE_ORDER);
                    PrintWriter printWriter = new PrintWriter(bookList);
                    for (int i = 0; i < books.size(); ++i) {
                        printWriter.println(books.get(i));
                    }
                    printWriter.close();
                } catch (Exception exception) {
                    label.append(exception.toString() + '\n');
                }
                    label.append("\nSorted list:\n--------------------\n");
                for (int i = 0; i < books.size(); ++i) {
                    label.append(books.get(i) + '\n');
                 }       
                label.append("\nEnter the name of a book:\n");
            }
        });
    }
}