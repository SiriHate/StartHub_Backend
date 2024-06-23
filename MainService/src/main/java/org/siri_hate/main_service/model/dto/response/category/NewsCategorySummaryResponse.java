package org.siri_hate.main_service.model.dto.response.category;

/**
 * This class represents a summary response for a news category.
 * It includes the essential details of a news category that can be sent as a response.
 */
public class NewsCategorySummaryResponse {

    private Long id;
    private String name;

    /**
     * Default constructor.
     */
    public NewsCategorySummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id   the id of the news category
     * @param name the name of the news category
     */
    public NewsCategorySummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id of the news category
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
     * @return the name of the news category
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}