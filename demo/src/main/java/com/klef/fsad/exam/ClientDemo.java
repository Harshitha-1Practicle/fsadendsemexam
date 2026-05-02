package com.klef.fsad.exam;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientDemo implements CommandLineRunner {

    private final LibraryRepository libraryRepository;

    public ClientDemo(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Management =====");
            System.out.println("1. Insert Library Record");
            System.out.println("2. Delete Library Record by ID");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Library Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Description: ");
                String description = sc.nextLine();

                System.out.print("Enter Path: ");
                String path = sc.nextLine();

                System.out.print("Enter Status: ");
                String status = sc.nextLine();

                Library library = new Library(name, description, path, status);

                libraryRepository.save(library);

                System.out.println("Library record inserted successfully.");
                System.out.println("Generated ID: " + library.getId());
            }

            else if (choice == 2) {
                System.out.print("Enter Library ID to delete: ");
                int id = sc.nextInt();

                if (libraryRepository.existsById(id)) {
                    libraryRepository.deleteById(id);
                    System.out.println("Library record deleted successfully.");
                } else {
                    System.out.println("Record not found with ID: " + id);
                }
            }

            else if (choice == 3) {
                System.out.println("Program ended.");
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
