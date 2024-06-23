package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

/**
 * This class represents the User entity.
 * It includes the id, username, articles, news, ownedProjects, and memberProjects of the user.
 * It is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The id of the user.
     * It is the primary key in the "users" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * The username of the user.
     * It is a unique field in the "users" table.
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * The articles written by the user.
     * It is a one-to-many relationship with the "articles" table.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Article> articles;

    /**
     * The news written by the user.
     * It is a one-to-many relationship with the "news" table.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<News> news;

    /**
     * The projects owned by the user.
     * It is a one-to-many relationship with the "projects" table.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Project> ownedProjects;

    /**
     * The projects that the user is a member of.
     * It is a many-to-many relationship with the "projects" table.
     */
    @ManyToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Project> memberProjects;

    /**
     * Default constructor.
     */
    public User() { }

    /**
     * Constructor with username field.
     *
     * @param username the username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the articles written by the user
     */
    public Set<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return the news written by the user
     */
    public Set<News> getNews() {
        return news;
    }

    /**
     * @param news the news to set
     */
    public void setNews(Set<News> news) {
        this.news = news;
    }

    /**
     * @return the projects owned by the user
     */
    public Set<Project> getOwnedProjects() {
        return ownedProjects;
    }

    /**
     * @param ownedProjects the owned projects to set
     */
    public void setOwnedProjects(Set<Project> ownedProjects) {
        this.ownedProjects = ownedProjects;
    }

    /**
     * @return the projects that the user is a member of
     */
    public Set<Project> getMemberProjects() {
        return memberProjects;
    }

    /**
     * @param memberProjects the member projects to set
     */
    public void setMemberProjects(Set<Project> memberProjects) {
        this.memberProjects = memberProjects;
    }

}