package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the Project entity.
 * It includes the id, user, members, projectName, projectDescription, projectLogoUrl, category, stage, likes, event, and moderationPassed of the project.
 * It is mapped to the "projects" table in the database.
 */
@Entity
@Table(name = "projects")
public class Project {

    /**
     * The id of the project.
     * It is the primary key in the "projects" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The user who created the project.
     * It is a foreign key referencing the "users" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The members of the project.
     * It is a many-to-many relationship with the "project_members" table.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "project_membership", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<ProjectMember> members = new HashSet<>();

    /**
     * The name of the project.
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * The description of the project.
     */
    @Column(name = "project_description")
    private String projectDescription;

    /**
     * The URL of the project's logo.
     */
    @Column(name = "project_logo_url")
    private String projectLogoUrl;

    /**
     * The category of the project.
     * It is a foreign key referencing the "project_categories" table.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProjectCategory category;

    /**
     * The stage of the project.
     */
    @Column(name = "stage")
    private String stage;

    /**
     * The number of likes the project has received.
     */
    @Column(name = "likes")
    private Long likes;

    /**
     * The event associated with the project.
     * It is a one-to-one relationship with the "events" table.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    /**
     * Whether the project has passed moderation.
     */
    @Column(name = "moderation_passed")
    private Boolean moderationPassed;

    /**
     * Default constructor.
     */
    public Project() { }

    /**
     * Constructor with all fields.
     *
     * @param user               the user who created the project
     * @param members            the members of the project
     * @param projectName        the name of the project
     * @param projectDescription the description of the project
     * @param projectLogoUrl     the URL of the project's logo
     * @param category           the category of the project
     * @param stage              the stage of the project
     * @param likes              the number of likes the project has received
     * @param event              the event associated with the project
     * @param moderationPassed   whether the project has passed moderation
     */
    public Project(
            User user,
            Set<ProjectMember> members,
            String projectName,
            String projectDescription,
            String projectLogoUrl,
            ProjectCategory category,
            String stage,
            Long likes,
            Event event,
            Boolean moderationPassed
                  ) {
        this.user = user;
        this.members = members;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLogoUrl = projectLogoUrl;
        this.category = category;
        this.stage = stage;
        this.likes = likes;
        this.event = event;
        this.moderationPassed = moderationPassed;
    }

    /**
     * @return the id of the project
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
     * @return the user who created the project
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the members of the project
     */
    public Set<ProjectMember> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Set<ProjectMember> members) {
        this.members = members;
    }

    /**
     * @return the name of the project
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the name to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the description of the project
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param projectDescription the description to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * @return the URL of the project's logo
     */
    public String getProjectLogoUrl() {
        return projectLogoUrl;
    }

    /**
     * @param projectLogoUrl the logo URL to set
     */
    public void setProjectLogoUrl(String projectLogoUrl) {
        this.projectLogoUrl = projectLogoUrl;
    }

    /**
     * @return the category of the project
     */
    public ProjectCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    /**
     * @return the stage of the project
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * @return the number of likes the project has received
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * @param likes the number of likes to set
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * @return the event associated with the project
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * @return whether the project has passed moderation
     */
    public Boolean getModerationPassed() {
        return moderationPassed;
    }

    /**
     * @param moderationPassed the moderation status to set
     */
    public void setModerationPassed(Boolean moderationPassed) {
        this.moderationPassed = moderationPassed;
    }

}