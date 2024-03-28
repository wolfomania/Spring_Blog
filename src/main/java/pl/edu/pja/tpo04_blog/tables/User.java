package pl.edu.pja.tpo04_blog.tables;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Article> articles = new HashSet<>();

    @OneToOne(mappedBy = "manager", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Blog managedBlog;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String blogName) {
        this.email = email;
        managedBlog = new Blog(blogName, this);
    }

    public Long getId() {
        return id;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blog getManagedBlog() {
        return managedBlog;
    }

    public void setManagedBlog(Blog managedBlog) {
        this.managedBlog = managedBlog;
    }

    @Override
    public String toString() {
        String init = id + "\t|\t" + email + "\t" + managedBlog.getId() + ".\t Articles: ";
        for (Article article : articles)
            init += article.getId() + "\t";
        return init;
    }
}
