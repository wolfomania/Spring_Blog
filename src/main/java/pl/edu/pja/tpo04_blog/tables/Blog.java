package pl.edu.pja.tpo04_blog.tables;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "blog",
            orphanRemoval = true,
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
//                    CascadeType.PERSIST,
                    CascadeType.DETACH
            }
    )
    private Set<Article> articles;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User manager;

    public Blog() {
    }

    public Blog(String name) {
        this.name = name;
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

    public void addNewArticle(Article article) {
        articles.add(article);
    }

    @Override
    public String toString() {
        String init = id + "\t|\t" + name + "\t" + manager.getId() + "\t|\t";
        for (Article article : articles)
            init += article.getId() + "\t";
        return init;
    }

}
