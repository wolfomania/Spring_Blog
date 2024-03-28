package pl.edu.pja.tpo04_blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo04_blog.tables.*;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class RepositoryService {

    ArticleRepository articleRepository;

    BlogRepository blogRepository;

    RoleRepository roleRepository;

    UserRepository userRepository;

    @Autowired
    public RepositoryService(ArticleRepository articleRepository, BlogRepository blogRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.blogRepository = blogRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public int getChoice(Scanner scanner) {
        System.out.println("Choose one of the following:");
        System.out.println("1. View all records");
        System.out.println("2. Add new record");
        System.out.println("3. Delete a record");
        System.out.println("Any other number to return to main menu.");
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
            System.out.println("Returning to the main menu");
            return 0;
        }
        System.out.println();
        return choice;
    }
    public void articleMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> articleRepository.findAll().forEach(System.out::println);
            case 2 -> {
                System.out.println("Choose constructor:");
                System.out.println("1. Title, AuthorId");
                System.out.println("2. Title, AuthorId, BlogId");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> articleRepository.save(
                            new Article(
                                    scanner.next(),
                                    userRepository.findById(scanner.nextLong()).get()
                            )
                    );
                    case 2 -> articleRepository.save(
                            new Article(
                                    scanner.next(),
                                    userRepository.findById(scanner.nextLong()).get(),
                                    blogRepository.findById(scanner.nextLong()).get()
                            )
                    );
                    default -> System.out.println("exit");
                }
            }
            case 3 -> {
                articleRepository.findAll().forEach(System.out::println);
                System.out.print("Choose id of article to delete: ");
                long id = scanner.nextLong();
                System.out.println();
                articleRepository.deleteById(id);
            }
            default -> System.out.println("Returning to the main menu");
        }
    }

    public void blogMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> blogRepository.findAll().forEach(System.out::println);
            case 2 -> {

            }
            case 3 -> {
                blogRepository.findAll().forEach(System.out::println);
                System.out.print("Choose id of blog to delete: ");
                long id = scanner.nextLong();
                System.out.println();
                blogRepository.deleteById(id);
            }
            default -> System.out.println("Returning to the main menu");
        }
    }

    public void roleMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> roleRepository.findAll().forEach(System.out::println);
            case 2 -> {

            }
            case 3 -> {
                roleRepository.findAll().forEach(System.out::println);
                System.out.print("Choose id of role to delete: ");
                long id = scanner.nextLong();
                System.out.println();
                roleRepository.deleteById(id);
            }
            default -> System.out.println("Returning to the main menu");
        }
    }

    public void userMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> userRepository.findAll().forEach(System.out::println);
            case 2 -> {

            }
            case 3 -> {
                userRepository.findAll().forEach(System.out::println);
                System.out.print("Choose id of user to delete: ");
                long id = scanner.nextLong();
                System.out.println();
                userRepository.deleteById(id);
            }
            default -> System.out.println("Returning to the main menu");
        }
    }
}
