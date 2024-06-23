package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.dto.request.auth.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.tokens.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.auth.RecoveryPasswordRequest;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing member-related operations in the user service API.
 * Provides endpoints for member registration, password recovery, profile management,
 * and retrieving member information.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * Constructs a MemberController with the provided MemberService.
     *
     * @param memberService The service responsible for member operations.
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Endpoint for registering a new member.
     *
     * @param member The member registration request body.
     * @return ResponseEntity with a success message and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<String> memberRegistration(@RequestBody @Valid MemberRegistrationRequest member) {
        memberService.memberRegistration(member);
        return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
    }

    /**
     * Endpoint for sending a request to recover member's password.
     *
     * @param recoveryPasswordRequest The request body containing recovery information.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PostMapping("/password_recovery_requests")
    public ResponseEntity<String> memberPasswordRecoveryRequest(
            @RequestBody RecoveryPasswordRequest recoveryPasswordRequest) {
        memberService.memberPasswordRecoveryRequest(recoveryPasswordRequest);
        return new ResponseEntity<>("Request to change the password has been successfully sent", HttpStatus.OK);
    }

    /**
     * Endpoint for confirming member's password recovery using a token.
     *
     * @param changePasswordTokenRequest The request body containing the change password token.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PatchMapping("/password_recovery_confirmations")
    public ResponseEntity<String> memberPasswordRecoveryConfirm(
            @RequestBody ChangePasswordTokenRequest changePasswordTokenRequest) {
        memberService.memberPasswordRecoveryConfirmation(changePasswordTokenRequest);
        return new ResponseEntity<>("Password has been successfully changed", HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all members with pagination support.
     *
     * @param pageable Pagination information to specify page size and number.
     * @return ResponseEntity with a page of MemberSummaryResponse and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<Page<MemberSummaryResponse>> getAllMembers(
            @PageableDefault(size = 1) Pageable pageable) {
        Page<MemberSummaryResponse> members = memberService.getAllMembers(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all visible members with pagination support.
     *
     * @param pageable Pagination information to specify page size and number.
     * @return ResponseEntity with a page of visible MemberSummaryResponse and HTTP status OK.
     */
    @GetMapping("/visible")
    public ResponseEntity<Page<MemberSummaryResponse>> getAllVisibleMembers(
            @PageableDefault(size = 1) Pageable pageable) {
        Page<MemberSummaryResponse> members = memberService.getAllVisibleMembers(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Endpoint to search members by username and specialization with pagination support.
     *
     * @param username      Username to search for (optional).
     * @param specialization Specialization to search for (optional).
     * @param pageable      Pagination information to specify page size and number.
     * @return ResponseEntity with a page of matching MemberSummaryResponse and HTTP status OK.
     */
    @GetMapping("/visible/search")
    public ResponseEntity<Page<MemberSummaryResponse>> searchMembers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String specialization,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<MemberSummaryResponse> membersPage = memberService.getMembersByUsernameAndSpecialization(
                username,
                specialization,
                pageable);
        return new ResponseEntity<>(membersPage, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a member by ID.
     *
     * @param id The ID of the member to retrieve.
     * @return ResponseEntity with the MemberFullResponse and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberFullResponse> getMemberById(
            @Positive(message = "ID should be greater than zero") @PathVariable("id") Long id) {
        MemberFullResponse member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Endpoint to update a member's details by ID.
     *
     * @param id                The ID of the member to update.
     * @param memberFullRequest The updated member details.
     * @return ResponseEntity with the updated MemberFullResponse and HTTP status OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<MemberFullResponse> memberUpdate(
            @Positive(message = "ID should be greater than zero") @PathVariable("id") Long id,
            @Valid MemberFullRequest memberFullRequest) {
        MemberFullResponse updatedMember = memberService.memberUpdate(id, memberFullRequest);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a member by ID.
     *
     * @param id The ID of the member to delete.
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(
            @Positive(message = "ID should be greater than zero") @PathVariable("id") Long id) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to delete a member by username.
     *
     * @param username The username of the member to delete.
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteMemberByUsername(@PathVariable("username") String username) {
        memberService.deleteMemberByUsername(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to retrieve the authenticated member's personal information.
     *
     * @return ResponseEntity with the authenticated MemberFullResponse and HTTP status OK.
     */
    @GetMapping("/me/personal_info")
    public ResponseEntity<MemberFullResponse> getMyPersonInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFullResponse member = memberService.getMemberByUsername(username);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Endpoint to delete the authenticated member.
     *
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMemberByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.deleteMemberByUsername(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to change the authenticated member's password.
     *
     * @param changePasswordForm The form containing the new password.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PatchMapping("/me/password")
    public ResponseEntity<String> changeMemberPasswordByAuth(
            @RequestBody @Valid ChangePasswordForm changePasswordForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberPasswordChange(username, changePasswordForm);
        return new ResponseEntity<>("Password has been successfully changed", HttpStatus.OK);
    }

    /**
     * Endpoint to change the authenticated member's avatar.
     *
     * @param avatar The request containing the new avatar details.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PatchMapping("/me/avatar")
    public ResponseEntity<String> changeMemberAvatar(@RequestBody @Valid MemberChangeAvatarRequest avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberChangeAvatar(username, avatar);
        return new ResponseEntity<>("Avatar has been successfully changed", HttpStatus.OK);
    }

    /**
     * Endpoint to change the authenticated member's personal information.
     *
     * @param profileData The request containing the updated profile information.
     * @return ResponseEntity with the updated MemberFullResponse and HTTP status OK.
     */
    @PatchMapping("/me/personal_info")
    public ResponseEntity<MemberFullResponse> changePersonalInfoByAuth(
            @RequestBody @Valid MemberProfileDataRequest profileData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFullResponse updatedMember = memberService.memberChangePersonalInfo(username, profileData);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    /**
     * Endpoint to change the authenticated member's profile visibility.
     *
     * @param request The request containing the new profile visibility flag.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PatchMapping("/me/profile_hidden_flag")
    public ResponseEntity<String> changeMemberProfileVisibility(
            @Valid @RequestBody MemberChangeProfileVisibilityRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.changeMemberProfileVisibility(request, username);
        return new ResponseEntity<>("Profile visibility has been successfully changed", HttpStatus.OK);
    }

}
