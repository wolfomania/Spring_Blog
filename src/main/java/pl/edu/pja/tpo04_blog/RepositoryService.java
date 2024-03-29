package pl.edu.pja.tpo04_blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

//        Assume the input is always correct
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

    @Transactional
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
                    case 1 -> {
                        String name = scanner.next();
                        Long authorId = scanner.nextLong();
                        User user = userRepository.findById(authorId).get();

                        Article tmp = new Article(name, user);
                        articleRepository.save(tmp);
                    }
                    case 2 -> {
                        String name = scanner.next();
                        Long authorId = scanner.nextLong();
                        Long blogId = scanner.nextLong();
                        User user = userRepository.findById(authorId).get();
                        Blog blog = blogRepository.findById(blogId).get();

                        Article tmp = new Article(name, user, blog);
                        articleRepository.save(tmp);
                    }
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

    @Transactional
    public void blogMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> blogRepository.findAll().forEach(System.out::println);
            case 2 -> {
                System.out.println("Choose one of the following constructor options:");
                System.out.println("1. BlogName");
                System.out.println("2. Manager_Id, BlogName");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        String blogName = scanner.next();
                        Blog blog = new Blog(blogName);
                        blogRepository.save(blog);
                    }
                    case 2 -> {
                        String blogName = scanner.next();
                        long managedId = scanner.nextLong();
                        Blog blog = new Blog(blogName, userRepository.findById(managedId).get());
                        blogRepository.save(blog);
                    }
                    default -> System.out.println("Returning to main menu");
                }
            }
            case 3 -> {
                blogRepository.findAll().forEach(System.out::println);
                System.out.print("Choose id of blog to delete: ");
                long id = scanner.nextLong();
                System.out.println();
                blogRepository.findById(id).ifPresent(blogRepository::delete);
            }
            default -> System.out.println("Returning to the main menu");
        }
    }

    @Transactional
    public void roleMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> roleRepository.findAll().forEach(System.out::println);
            case 2 -> {
                System.out.println("Provide name for new role:");
                String roleName = scanner.next();
                Role role = new Role(roleName);
                roleRepository.save(role);
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

    @Transactional
    public void userMenu(Scanner scanner) {
        int choice = getChoice(scanner);
        switch (choice) {
            case 1 -> userRepository.findAll().forEach(System.out::println);
            case 2 -> {
                System.out.println("Choose one of the following constructor options:");
                System.out.println("1. Email");
                System.out.println("2. Email, NewBlogName");
                System.out.println("3. Email, Existing BlogID");

                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        String email = scanner.next();
                        User user = new User(email);
                        userRepository.save(user);
                    }
                    case 2 -> {
                        String email = scanner.next();
                        String blogName = scanner.next();
                        User user = new User(email, blogName);
                        userRepository.save(user);
                    }
                    case 3 -> {
                        String email = scanner.next();
                        long blogId = scanner.nextLong();
                        User user = new User(email, blogRepository.findById(blogId).get());
                        userRepository.save(user);
                    }
                    default -> System.out.println("Returning to main menu");

                }
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
