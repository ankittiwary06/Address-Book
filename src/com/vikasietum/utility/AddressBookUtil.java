package com.vikasietum.utility;

import java.util.Scanner;

public class AddressBookUtil {

    /* for mandatory inputs in contact */
    public static String validateInput(String param, String input, Scanner sc) {
        while (input.isEmpty()) {
            System.out.println("please enter " + param + " it is mandotary");
            input = sc.nextLine();
        }
        return input;
    }
}
