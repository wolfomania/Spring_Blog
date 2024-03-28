package pl.edu.pja.tpo04_blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class BlogController {

    private final RepositoryService repositoryService;

    @Autowired
    public BlogController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public boolean startMenu(Scanner scanner) {
        System.out.println("Choose one of the following level of operation:");
        System.out.println("1. Article");
        System.out.println("2. Blog");
        System.out.println("3. Role");
        System.out.println("4. User");
        System.out.println("Any other number for exit.");
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
            System.out.println("Exiting");
            return false;
        }
        System.out.println();
        switch (choice) {
            case 1 -> repositoryService.articleMenu(scanner);
            case 2 -> repositoryService.blogMenu(scanner);
            case 3 -> repositoryService.roleMenu(scanner);
            case 4 -> repositoryService.userMenu(scanner);
            default -> {
                System.out.println("Exiting");
                return false;
            }
        }
        return true;
    }
}
