/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iotesting;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Lenovo-BM
 */
public class Iotesting {
      public static void main(String[] args) {
        String directoryName; // Directory name entered by the user.
        File directory; // File object referring to the directory.
        String[] files; // Array of file names in the directory.
        Scanner scanner; // For reading a line of input from the user.
        scanner = new Scanner(System.in); // scanner reads from standard input.
        //System.out.print("Enter a directory name: ");
        //directoryName = scanner.nextLine().trim();
        directoryName="c:\\";
        directory = new File(directoryName);
        if (directory.isDirectory() == false) {
                if (directory.exists() == false)
                    System.out.println("There is no such directory!");
                else
                    System.out.println("That file is not a directory.");
        }
        else {
            files = directory.list();
            System.out.println("Files in directory \"" + directory + "\":");
            for (int i = 0; i < files.length; i++)
            System.out.println(" " + files[i]);
}
    } // end main()
}