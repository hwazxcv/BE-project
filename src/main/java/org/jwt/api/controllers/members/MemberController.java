package org.jwt.api.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jwt.commons.Utils;
import org.jwt.commons.exceptions.BadRequestException;
import org.jwt.commons.rests.JSONData;
import org.jwt.entities.Member;
import org.jwt.models.member.MemberInfo;
import org.jwt.models.member.MemberLoginService;
import org.jwt.models.member.MemberSaveService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService saveService;
    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<JSONData> join(@RequestBody @Valid RequestJoin form, Errors errors) {
        saveService.save(form, errors);

     errorProcess(errors);

        JSONData data = new JSONData();
        data.setStatus(HttpStatus.CREATED);

        return ResponseEntity.status(data.getStatus()).body(data);
    }

    @PostMapping("/token")
    public ResponseEntity<JSONData> token(@RequestBody @Valid RequestLogin form , Errors errors){
        errorProcess(errors);

        String accessToken = memberLoginService.login(form);

        /*
        * 1.응답 body - JSONData형식으로
        * 2.응갑 해더 - Authorization: bearer 토큰
        * */

        JSONData data = new JSONData(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization" ,"Bearer"+accessToken);

        return ResponseEntity.status(data.getStatus()).headers(headers).body(data);
    }


    @GetMapping("/info")
    public JSONData info(@AuthenticationPrincipal MemberInfo memberInfo){
        Member member = memberInfo.getMember();
        return new JSONData(member);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(){
        return "관리자 페이지 접속" ;
    }


    private void errorProcess(Errors errors){
        if(errors.hasErrors()){
            throw  new BadRequestException(Utils.getMessages(errors));
        }
    }


}
