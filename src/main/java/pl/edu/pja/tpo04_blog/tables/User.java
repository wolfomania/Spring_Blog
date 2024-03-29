package pl.edu.pja.tpo04_blog.tables;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @OneToMany(
            mappedBy = "author",
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
//                    CascadeType.PERSIST, //When turned on, during user deletion articles that connected to blog of this user are not deleted
                    CascadeType.DETACH
            },
            orphanRemoval = true
    )
    private Set<Article> articles;

    @OneToOne(
            mappedBy = "manager",
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
//                    CascadeType.PERSIST, //When turned on blog can't be deleted (No exception, just don't work)
                    CascadeType.DETACH
            }
    )
    private Blog managedBlog;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String blogName) {
        this.email = email;
        managedBlog = new Blog(blogName, this);
    }

    public User(String email, Blog managedBlog) {
        this.email = email;
        this.managedBlog = managedBlog;
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

    public void addNewArticle(Article article) {
        articles.add(article);
    }

    @Override
    public String toString() {
        String init = id + "\t|\t" + email + "\t" + managedBlog.getId() + "\t Articles: ";
        for (Article article : articles)
            init += article.getId() + "\t";
        return init;
    }
}
