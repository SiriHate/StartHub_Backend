package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.request.*;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/users/member")
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

    @PostMapping("/registration")
    public ResponseEntity<String> memberRegistration(@RequestBody @Valid Member member) {
        memberService.memberRegistration(member);
        return new ResponseEntity<>("Successful registration", HttpStatus.OK);
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> memberList = memberService.getAllMembers();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<Member> getMemberById(
            @Positive(message = "ID should be greater than zero")
            @RequestParam("id") Long id
    ) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Member> memberUpdate(
            @Positive(message = "ID should be greater than zero")
            @RequestParam("id") Long id,
            @Valid Member member
    ) {
        Member updatedMember = memberService.memberUpdate(id, member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteMemberById(
            @Positive(message = "ID should be greater than zero")
            @RequestParam("id") Long id
    ) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteByUsername")
    public ResponseEntity<String> deleteMemberById(@RequestParam("username") String username) {
        memberService.deleteMemberByUserName(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete_my_account")
    public ResponseEntity<String> deleteMemberByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.deleteMemberByUserName(username);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/change_password")
    public ResponseEntity<String> changeMemberPasswordByAuth(@RequestBody @Valid ChangePasswordForm changePasswordForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberPasswordChange(username, changePasswordForm);
        return new ResponseEntity<>("Password has been successfully changed", HttpStatus.OK);
    }

    @PostMapping("/change_avatar")
    public ResponseEntity<String> changeMemberAvatar(@RequestPart("avatar") MultipartFile avatarFile) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            byte[] avatarBytes = avatarFile.getBytes();
            memberService.memberChangeAvatar(username, avatarBytes);
            return new ResponseEntity<>("Avatar has been successfully changed", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to change avatar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change_personal_info")
    public ResponseEntity<String> changePersonalInfoByAuth(@RequestBody @Valid PersonalData personalData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberService.memberChangePersonalInfo(username, personalData);
        return new ResponseEntity<>("Personal data has been successfully changed", HttpStatus.OK);
    }

}
