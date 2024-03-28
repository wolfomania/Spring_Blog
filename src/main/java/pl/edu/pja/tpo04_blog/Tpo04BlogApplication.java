package pl.edu.pja.tpo04_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.pja.tpo04_blog.tables.ArticleRepository;
import pl.edu.pja.tpo04_blog.tables.BlogRepository;
import pl.edu.pja.tpo04_blog.tables.RoleRepository;
import pl.edu.pja.tpo04_blog.tables.UserRepository;

import java.util.Scanner;

@SpringBootApplication
public class Tpo04BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo04BlogApplication.class, args);
        BlogController blogController = context.getBean(BlogController.class);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome");
        while (blogController.startMenu(scanner));

    }

}
