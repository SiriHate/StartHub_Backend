package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entity for ConfirmationToken.
 * This class is used to map the confirmation_tokens table in the database.
 * It provides fields and methods specific to a ConfirmationToken.
 */
@Entity
public class ConfirmationToken {

    /**
     * The ID of the confirmation token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    /**
     * The type of the confirmation token.
     */
    @Column(name = "token_type")
    private String tokenType;

    /**
     * The value of the confirmation token.
     */
    @Column(name="token_value")
    private String tokenValue;

    /**
     * The member associated with the confirmation token.
     */
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * Default constructor.
     */
    public ConfirmationToken() {}

    /**
     * Constructor with parameters.
     *
     * @param tokenType The type of the confirmation token.
     * @param tokenValue The value of the confirmation token.
     * @param member The member associated with the confirmation token.
     */
    public ConfirmationToken(String tokenType, String tokenValue, Member member) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
        this.member = member;
    }

    /**
     * Gets the ID of the confirmation token.
     *
     * @return The ID of the confirmation token.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the confirmation token.
     *
     * @param id The ID of the confirmation token.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the type of the confirmation token.
     *
     * @return The type of the confirmation token.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Sets the type of the confirmation token.
     *
     * @param tokenType The type of the confirmation token.
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Gets the value of the confirmation token.
     *
     * @return The value of the confirmation token.
     */
    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * Sets the value of the confirmation token.
     *
     * @param tokenValue The value of the confirmation token.
     */
    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * Gets the member associated with the confirmation token.
     *
     * @return The member associated with the confirmation token.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member associated with the confirmation token.
     *
     * @param member The member associated with the confirmation token.
     */
    public void setMember(Member member) {
        this.member = member;
    }

}