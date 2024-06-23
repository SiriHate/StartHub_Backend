package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.auth.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.tokens.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.auth.RecoveryPasswordRequest;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Member service interface.
 * This interface defines the contract for member operations.
 * It provides methods for member registration, account activation, password recovery, password change, member retrieval, member update, member deletion, avatar change, personal info change, and profile visibility change.
 */
public interface MemberService {

    /**
     * Registers a member.
     * This method takes a MemberRegistrationRequest DTO and registers a member.
     *
     * @param member the member registration request DTO
     */
    void memberRegistration(MemberRegistrationRequest member);

    /**
     * Activates a member account.
     * This method activates a member account by ID.
     *
     * @param id the ID of the member
     */
    void activateMemberAccount(Long id);

    /**
     * Requests member password recovery.
     * This method takes a RecoveryPasswordRequest DTO and requests member password recovery.
     *
     * @param recoveryPasswordRequest the recovery password request DTO
     */
    void memberPasswordRecoveryRequest(RecoveryPasswordRequest recoveryPasswordRequest);

    /**
     * Confirms member password recovery.
     * This method takes a ChangePasswordTokenRequest DTO and confirms member password recovery.
     *
     * @param changePasswordTokenRequest the change password token request DTO
     */
    void memberPasswordRecoveryConfirmation(ChangePasswordTokenRequest changePasswordTokenRequest);

    /**
     * Changes a member password.
     * This method takes a username and a ChangePasswordForm DTO and changes the member password.
     *
     * @param username the username of the member
     * @param changePasswordForm the change password form DTO
     */
    void memberPasswordChange(String username, ChangePasswordForm changePasswordForm);

    /**
     * Retrieves all members.
     * This method retrieves all members and maps them to MemberSummaryResponse DTOs.
     *
     * @param pageable the pagination information
     * @return a page of member summary response DTOs
     */
    Page<MemberSummaryResponse> getAllMembers(Pageable pageable);

    /**
     * Retrieves all visible members.
     * This method retrieves all visible members and maps them to MemberSummaryResponse DTOs.
     *
     * @param pageable the pagination information
     * @return a page of member summary response DTOs
     */
    Page<MemberSummaryResponse> getAllVisibleMembers(Pageable pageable);

    /**
     * Retrieves members by username and specialization.
     * This method retrieves members by username and specialization and maps them to MemberSummaryResponse DTOs.
     *
     * @param username the username of the member
     * @param specialization the specialization of the member
     * @param pageable the pagination information
     * @return a page of member summary response DTOs
     */
    Page<MemberSummaryResponse> getMembersByUsernameAndSpecialization(
            String username,
            String specialization,
            Pageable pageable
                                                                     );

    /**
     * Retrieves a member by ID.
     * This method retrieves a member by ID and maps it to a MemberFullResponse DTO.
     *
     * @param id the ID of the member
     * @return the member full response DTO
     */
    MemberFullResponse getMemberById(Long id);

    /**
     * Retrieves a member by username.
     * This method retrieves a member by username and maps it to a MemberFullResponse DTO.
     *
     * @param username the username of the member
     * @return the member full response DTO
     */
    MemberFullResponse getMemberByUsername(String username);

    /**
     * Updates a member by ID.
     * This method retrieves a member by ID, updates the member with the information from the MemberFullRequest DTO, and saves the updated member.
     *
     * @param id the ID of the member
     * @param member the member full request DTO
     * @return the member full response DTO
     */
    MemberFullResponse memberUpdate(Long id, MemberFullRequest member);

    /**
     * Deletes a member by ID.
     * This method retrieves a member by ID and deletes it.
     *
     * @param id the ID of the member
     */
    void deleteMemberById(Long id);

    /**
     * Deletes a member by username.
     * This method retrieves a member by username and deletes it.
     *
     * @param username the username of the member
     */
    void deleteMemberByUsername(String username);

    /**
     * Changes a member avatar.
     * This method takes a username and a MemberChangeAvatarRequest DTO and changes the member avatar.
     *
     * @param username the username of the member
     * @param avatar the member change avatar request DTO
     */
    void memberChangeAvatar(String username, MemberChangeAvatarRequest avatar);

    /**
     * Changes a member personal info.
     * This method takes a username and a MemberProfileDataRequest DTO and changes the member personal info.
     *
     * @param username the username of the member
     * @param profileDataRequest the member profile data request DTO
     * @return the member full response DTO
     */
    MemberFullResponse memberChangePersonalInfo(String username, MemberProfileDataRequest profileDataRequest);

    /**
     * Changes a member profile visibility.
     * This method takes a MemberChangeProfileVisibilityRequest DTO and a username and changes the member profile visibility.
     *
     * @param request the member change profile visibility request DTO
     * @param username the username of the member
     * @return the member full response DTO
     */
    MemberFullResponse changeMemberProfileVisibility(MemberChangeProfileVisibilityRequest request, String username);

}