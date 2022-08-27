package com.keduit.project02.controller.api;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.dto.ResponseDto;
import com.keduit.project02.domain.User;
import com.keduit.project02.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Json 데이터를 받으려면 @RequestBody로 받아야함
    // 회원가입
    @PostMapping("/join")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        userService.userCreate(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바 오브젝트를 JSON으로 변환하여 전송 (JACKSON)
    }

    @PutMapping("/userModify")
    public ResponseDto<String> update(@RequestBody User user,@AuthenticationPrincipal PrincipalDetails principalDetail) {

        User newUser = userService.userModify(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션 값을 변경해줘야함.
        // 한마디로 DB는 회원수정이 이뤄졌지만, 실제 웹에서는 세션정보는 DB수정 전 세션이라는 뜻
        // 해결하기 위해서 세션 정보를 직접 변경 해줘야함
        System.out.println("gd");
        principalDetail.setUser(newUser);
        
        return new ResponseDto<String>(HttpStatus.OK.value(), "정보수정이 완료되었습니다.");
    }
    
    
    
    @PostMapping("/userDelete")
    public boolean delete(@RequestBody User user,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	System.out.println(userService.deletUser(user.getPassword(), principalDetail.getUser()));
    	return userService.deletUser(user.getPassword(), principalDetail.getUser());
    }
    
    @PostMapping("/confirmId")
    public boolean confirmId(@RequestBody String username) {
    	
    	return userService.confirmUsername(username);
    }
    
    @PostMapping("/confirmPassword")
    public boolean confirmPassword(@RequestBody User user,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	
    	return userService.confirmPassword(user.getPassword(), principalDetail.getUser());
    }


}