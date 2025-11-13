package com.pluralsight;
import com.pluralsight.Userinterface.UserInterface;

//  Entry point of the program where execution begins
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();   // Create an instance of the UserInterface class
        userInterface.display();

    }
}