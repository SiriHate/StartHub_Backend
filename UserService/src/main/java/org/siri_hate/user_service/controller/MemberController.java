package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.dto.request.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.RecoveryPasswordRequest;
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


@RestController
@Validated
@RequestMapping("/api/v1/user_service/members")
public class MemberController {

    final private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/check_token")
    public ResponseEntity<?> checkToken() {
        return new ResponseEntity<>("Token is valid", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> memberRegistration(@RequestBody @Valid MemberRegistrationRequest member) {
        memberService.memberRegistration(member);
        return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
    }

    @PostMapping("/password_recovery/request")
    public ResponseEntity<String> memberPasswordRecoveryRequest(
            @RequestBody RecoveryPasswordRequest recoveryPasswordRequest
                                                               ) {
        memberService.memberPasswordRecoveryRequest(recoveryPasswordRequest);
        return new ResponseEntity<>("Request to change the password has been successfully sent", HttpStatus.OK);
    }

    @PostMapping("/password_recovery/confirm")
    public ResponseEntity<String> memberPasswordRecoveryConfirm(
            @RequestBody ChangePasswordTokenRequest changePasswordTokenRequest
                                                               ) {
        memberService.memberPasswordRecoveryConfirmation(changePasswordTokenRequest);
        return new ResponseEntity<>("Password has been successfully changed", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<MemberSummaryResponse>> getAllMembers(
            @PageableDefault(size = 1) Pageable pageable
                                                                    ) {
        Page<MemberSummaryResponse> members = memberService.getAllMembers(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/visible")
    public ResponseEntity<Page<MemberSummaryResponse>> getAllVisibleMembers(
            @PageableDefault(size = 1) Pageable pageable
                                                                           ) {
        Page<MemberSummaryResponse> members = memberService.getAllVisibleMembers(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberFullResponse> getMemberById(
            @Positive(message = "ID should be greater than zero") @PathVariable("id") Long id
                                                           ) {
        MemberFullResponse member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<MemberFullResponse> getMemberByUsername(@PathVariable String username) {
        MemberFullResponse member = memberService.getMemberByUsername(username);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/search-by-username/{username}")
    public ResponseEntity<Page<MemberSummaryResponse>> searchMemberByUsername(
            @PathVariable String username,
            @PageableDefault(size = 1) Pageable pageable
                                                                             ) {
        Page<MemberSummaryResponse> members = memberService.searchMemberByUsername(username, pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<MemberFullResponse> memberUpdate(
            @Positive(message = "ID should be greater than zero") @RequestParam("id") Long id,
            @Valid MemberFullRequest memberFullRequest
                                                          ) {
        MemberFullResponse updatedMember = memberService.memberUpdate(id, memberFullRequest);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteMemberById(
            @Positive(message = "ID should be greater than zero") @RequestParam("id") Long id
                                                  ) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteByUsername")
    public ResponseEntity<String> deleteMemberById(@RequestParam("username") String username) {
        memberService.deleteMemberByUserName(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/auth/personInfo")
    public ResponseEntity<MemberFullResponse> getMyPersonInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFullResponse member = memberService.getMemberByUsername(username);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/auth/delete_my_account")
    public ResponseEntity<String> deleteMemberByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.deleteMemberByUserName(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/auth/change_password")
    public ResponseEntity<String> changeMemberPasswordByAuth(
            @RequestBody @Valid ChangePasswordForm changePasswordForm
                                                            ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberPasswordChange(username, changePasswordForm);
        return new ResponseEntity<>("Password has been successfully changed", HttpStatus.OK);
    }

    @PatchMapping("/auth/avatar")
    public ResponseEntity<String> changeMemberAvatar(@RequestBody @Valid MemberChangeAvatarRequest avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberChangeAvatar(username, avatar);
        return new ResponseEntity<>("Avatar has been successfully changed", HttpStatus.OK);
    }

    @PatchMapping("/auth/personal_info")
    public ResponseEntity<MemberFullResponse> changePersonalInfoByAuth(
            @RequestBody @Valid MemberProfileDataRequest profileData
                                                                      ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFullResponse updatedMember = memberService.memberChangePersonalInfo(username, profileData);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @PatchMapping("/auth/profile_visibility")
    public void changeMemberProfileVisibility(
            @Valid @RequestBody MemberChangeProfileVisibilityRequest request
                                             ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFullResponse updatedMember = memberService.changeMemberProfileVisibility(request, username);
        //return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

}
