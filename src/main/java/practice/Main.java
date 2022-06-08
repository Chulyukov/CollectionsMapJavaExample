package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.terminalWork();

//        phoneBook.addContact("79001234567", "Маша");
//        phoneBook.addContact("79001234567", "Миша");
        System.out.println(phoneBook.getAllContacts());
    }
}
