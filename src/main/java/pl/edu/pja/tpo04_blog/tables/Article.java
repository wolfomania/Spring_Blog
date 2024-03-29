package pl.edu.pja.tpo04_blog.tables;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public Article() {
    }

    public Article(String title, User author, Blog blog) {
        this.title = title;
        this.author = author;
        this.blog = blog;
    }

    public Article(String title, User author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return id + "\t|\t" + title + "\t" + author.getId() + "\t" + (blog == null ? "" : blog.getId());
    }
}
