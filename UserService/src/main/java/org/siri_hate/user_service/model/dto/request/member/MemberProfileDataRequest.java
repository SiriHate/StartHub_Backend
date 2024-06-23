package org.siri_hate.user_service.model.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO for member profile data request.
 * This class is used to transfer data related to member profile data requests.
 */
public class MemberProfileDataRequest {

    /**
     * The name of the member.
     */
    @NotBlank(message = "Name must not be null")
    private String name;

    /**
     * The phone number of the member.
     */
    @NotBlank(message = "Phone must not be null")
    private String phone;

    /**
     * The email of the member.
     */
    @NotBlank(message = "Email must not be null")
    private String email;

    /**
     * The birthday of the member.
     */
    @NotNull(message = "Birthday must not be null")
    private LocalDate birthday;

    /**
     * The about section of the member.
     */
    private String about;

    /**
     * The ID of the member's specialization.
     */
    private Long specializationId;

    public MemberProfileDataRequest() { }

    public MemberProfileDataRequest(
            String name,
            String phone,
            String email,
            LocalDate birthday,
            String about,
            Long specializationId
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.about = about;
        this.specializationId = specializationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

}
