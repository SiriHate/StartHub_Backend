package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.response.SuccessfulAuthorizationResponse;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/member")
@Validated
public class MemberController {

    final private MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> memberRegistration(@RequestBody @Valid Member member) {
        memberService.memberRegistration(member);
        return new ResponseEntity<>("Successful registration", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulAuthorizationResponse> memberLogin(@RequestBody @Valid LoginForm loginForm) {
        String token = memberService.memberLogin(loginForm);
        SuccessfulAuthorizationResponse response = new SuccessfulAuthorizationResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/password_recovery/request")
    public String memberPasswordRecoveryRequest(@RequestBody String email) {
        return "redirect:/login";
    }

    @PostMapping("/password_recovery/confirm")
    public String memberPasswordRecoveryConfirm(@RequestBody String newPassword) {
        return "redirect:/login";
    }

    @PostMapping("/get")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> memberList = memberService.getAllMembers();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Member> getMemberById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id
    ) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Member> memberUpdate(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id,
            @Valid Member member
    ) {
        Member updatedMember = memberService.memberUpdate(id, member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>("Successful delete", HttpStatus.NO_CONTENT);
    }

}
