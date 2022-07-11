package com.vikasietum.client;

import com.vikasietum.model.Category;
import com.vikasietum.model.Contact;
import com.vikasietum.service.ContactService;
import com.vikasietum.utility.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AddressBookApp {
    public static void main(String[] args) {
        ContactService contactService = new ContactService();
        Scanner sc = new Scanner(System.in);
        String option;

        while (true) {
            System.out.println("press 1 to add new Contact ");
            System.out.println("press 2 to display Contact ");
            System.out.println("press 3 to edit Contact ");
            option = sc.nextLine();
            if (option.equals("1")) {
                addNewContact(contactService,sc);
                continue;
            }
            if (option.equals("2")) {
                contactService.displayAll();;
                continue;
            }
            if (option.equals("3")) {
                contactService.edit();
                continue;
            }
        }
    }

    public static void addNewContact(ContactService contactService, Scanner sc) {
        String input="";
        Contact contact = new Contact();
        System.out.println("please enter First Name");
        input = sc.nextLine();
        input = validateInput("First Name", input, sc);
        contact.setName(input);
        System.out.println("please enter SurName");
        input = sc.nextLine();
        input = validateInput("SurName", input, sc);
        contact.setSurname(input);
        System.out.println("please enter email");
        input = sc.nextLine();
        input = validateInput("email", input, sc);
        contact.setEmail(input);
        System.out.println("please enter telephone Number");
        input = sc.nextLine();
        input = validateInput("telephone Number", input, sc);
        contact.setTelephoneNumber(input);
        System.out.println("please enter Age (optional # press enter to continue)");
        input = sc.nextLine();
        if (!input.isEmpty())
            contact.setAge(Integer.parseInt(input));
        System.out.println("please enter Hair Color(optional # press enter to continue)");
        input = sc.nextLine();
        contact.setHairColor(input);
        System.out.println("Choose category");
        System.out.println("press 1 for Family");
        System.out.println("press 2 for Friend");
        System.out.println("press 3 for Acquaintances");
        String option;
        option = sc.nextLine();
        if (option.equals("1")) {
            Category category = new Category();
            category.setName("Family");
            //
            category.setFriendshipYears(0);
            System.out.println("choose description");
            System.out.println("press 1 for parent");
            System.out.println("press 2 for grandparent");
            System.out.println("press 3 for son/daughter");
            System.out.println("press 4 for aunt/uncle");
            String descriptionOption = sc.nextLine();
            if (descriptionOption.equals("1"))
                category.setDescription("parent");
            if (descriptionOption.equals("2"))
                category.setDescription("grandparent");
            if (descriptionOption.equals("3"))
                category.setDescription("son/daughter");
            if (descriptionOption.equals("4"))
                category.setDescription("aunt/uncle");
            contact.setCategory(category);
        }
        if (option.equals("2")) {
            Category category = new Category();
            category.setName("Friend");
            category.setDescription("Friend");
            System.out.println("please enter Number of friendship years");
            String years = sc.nextLine();
            category.setFriendshipYears(Integer.parseInt(years));
            contact.setCategory(category);
        }
        if (option.equals("3")) {// to do for acquaintances
        }
        contactService.addContact(contact);
        System.out.println("contact has been saved");
    }

    public static String validateInput(String param, String input, Scanner sc) {
        while (input.isEmpty()) {
            System.out.println("please enter " + param + " it is mandotary");
            input = sc.nextLine();
        }
        return input;
    }
}
