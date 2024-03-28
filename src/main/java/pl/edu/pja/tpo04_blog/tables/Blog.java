package pl.edu.pja.tpo04_blog.tables;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Article> articles = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    private User manager;

    public Blog() {
    }

    public Blog(String name, User manager) {
        this.name = name;
        this.manager = manager;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        String init = id + "\t|\t" + name + "\t" + manager.getId() + "\t";
        for (Article article : articles)
            init += article.getId() + "\t";
        return init;
    }
}
