package com.vikasietum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikasietum.model.Contact;
import com.vikasietum.utility.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ContactService {

    public void addContact(Contact contact) {
        String fileName = contact.getName() + "_" + contact.getSurname();
        ObjectMapper obj = new ObjectMapper();
        try {
            String jsonStr = obj.writeValueAsString(contact);
            String contact_file = Constants.filePath + fileName;
            FileWriter file = new FileWriter(contact_file);
            file.write(jsonStr);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAll() {
        List<Contact> contacts = new ArrayList<>();

        ObjectMapper obj = new ObjectMapper();
        try {
            List<File> filesInFolder = Files.walk(Paths.get(Constants.filePath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            Iterator<File> it = filesInFolder.iterator();
            while (it.hasNext()) {
                    File contactFile = it.next();
                    String contactJsonString = new String(Files.readAllBytes(contactFile.toPath()));
                    Contact contact = obj.readValue(contactJsonString, Contact.class);
                    contacts.add(contact);
            }
            if (contacts.isEmpty()) {
                System.out.println("No Contacts Found!");
                return;
            }
            Collections.sort(contacts);
            for (Contact e : contacts
            ) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println("could not locate contact_files");
            throw new RuntimeException(e);
        }
    }

    public void edit() {

        String firstName;
        String lastName;
        String option;
        Scanner sc = new Scanner(System.in);
        System.out.println("EDIT SECTION");
        System.out.println("enter first name of contact ");
        firstName = sc.nextLine();
        System.out.println("enter last name of contact");
        lastName = sc.nextLine();
        String filePath;
        filePath = Constants.filePath + firstName + "_" + lastName;
        String contactJsonString = "";
        int read = 0;
        int i = 0;

        try {

            FileInputStream contactFileStream = new FileInputStream(filePath);
            ObjectMapper obj = new ObjectMapper();
            Contact contact = obj.readValue(contactFileStream, Contact.class);
            System.out.println("press 1 to edit phone number");
            System.out.println("press 2 to edit emailid");
            option = sc.nextLine();


            if (option.equals("1")) {
                System.out.println("Please enter new phone mumber");
                contact.setTelephoneNumber(sc.nextLine());

            }
            if (option.equals("2")) {
                System.out.println("Please enter new email ");
                contact.setEmail(sc.nextLine());
            }


            FileWriter file = new FileWriter(filePath);
            file.write(obj.writeValueAsString(contact));
            file.flush();
            file.close();
            System.out.println("your contact has been updated");

        } catch (FileNotFoundException e) {
            System.out.println("No contact found with this name\n");
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void remove() {

    }


}
