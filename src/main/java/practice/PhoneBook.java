package practice;

import java.util.*;

public class PhoneBook {
    HashMap<String, String> book = new HashMap<>();

    public void terminalWork() {
        String error = "Неверный формат ввода.";
        while (true) {

            System.out.println("Введите номер, имя или команду:");
            String input = new Scanner(System.in).next();

            if (input.equals("LIST")) {
                getAllContacts();
                break;
            }

            if (isValidName(input)) {
                if (hasName(input)) {
                    System.out.println("Такой контакт уже есть в телефонной книжке:");
                    System.out.println(getContactByName(input));
                    System.out.println();
                } else {
                    System.out.println("Такого имени в телефонной книге нет.\n" +
                            "Введите номер телефона для абонента \"" + input + "\":");
                    String input2 = new Scanner(System.in).next();
                    if (isValidPhone(input2)) {
                        addContact(input2, input);
                        System.out.println("Контакт сохранен!\n");
                    } else {
                        System.out.println(error + "\n");
                    }
                }
            } else if (isValidPhone(input)) {
                if (hasPhone(input)) {
                    System.out.println("Такой контакт уже есть в телефонной книжке:");
                    System.out.println(getContactByPhone(input));
                    System.out.println();
                } else {
                    System.out.println("Такого номера нет в телефонной книге.\n" +
                            "Введите имя абонента для номера \"" + input + "\":");
                    String input2 = new Scanner(System.in).next();
                    if (isValidName(input2)) {
                        addContact(input, input2);
                        System.out.println("Контакт сохранен!\n");
                    } else {
                        System.out.println(error + "\n");
                    }
                }
            } else {
                System.out.println(error + "\n");
            }
        }
    }

    public boolean hasName(String name) {
        return book.containsKey(name);
    }

    public boolean hasPhone(String phone) {
        return book.containsValue(phone);
    }

    public void addContact(String phone, String name) {
        if (book.containsValue(phone)) {
            for (Map.Entry<String, String> entry : book.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value.equals(phone)) {
                    book.remove(key);
                }
            }
            book.put(name, phone);
        } else if (book.containsKey(name)) {
            book.put(name, book.get(name) + ", " + phone);
        } else if (isValidPhone(phone) && isValidName(name) && !(book.containsKey(name))) {
            book.put(name, phone);
        }
    }

    public boolean isValidPhone(String phone) {
        String regex = "[+]?[7-8]?[-\\s(]{0,2}[\\d]{3}[-\\s)]{0,2}[\\d]{3}[-]?[\\d]{2}[-]?[\\d]{2}";
        return phone.matches(regex);
    }

    public boolean isValidName(String name) {
        String regex = "[а-яА-Я]+";
        return name.matches(regex);
    }


    public String getContactByPhone(String phone) {
        for (Map.Entry<String, String> entry : book.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals(phone)) {
                return key + " - " + phone;
            }
        }
        return null;
    }

    public Set<String> getContactByName(String name) {
        if (book.containsKey(name)) {
            String[] contact = (name + " - " + book.get(name)).split("\n");
            return new HashSet<>(List.of(contact));
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        if (book.size() > 0) {
            StringBuilder keyBuilder = new StringBuilder();
            for (String key : book.keySet()) {
                keyBuilder.append(key).append(" - ").append(book.get(key)).append("\n");
            }
            String[] contacts = keyBuilder.toString().split("\n");
            return new HashSet<>(List.of(contacts));
        }
        return new TreeSet<>();
    }
}