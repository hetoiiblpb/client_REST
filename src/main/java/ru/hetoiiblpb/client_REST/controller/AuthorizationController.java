package ru.hetoiiblpb.client_REST.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.hetoiiblpb.client_REST.dto.AuthorizationRequestDto;
import ru.hetoiiblpb.client_REST.dto.AuthorizationResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AuthorizationController {
    private final String serverUrlLogin;
    private final String startWord;

    public AuthorizationController(@Value("${server.url.login}") String serverUrlLogin,
                                   @Value("${jwt.token.start.word}") String startWord) {
        this.serverUrlLogin = serverUrlLogin;
        this.startWord = startWord;
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
}

@PostMapping("login")
    public String login(AuthorizationRequestDto authorizationRequestDto, HttpServletRequest request) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<AuthorizationRequestDto> entity = new HttpEntity<>(authorizationRequestDto,httpHeaders);
    HttpSession session = request.getSession();
    try {
        ResponseEntity<AuthorizationResponseDto> result = restTemplate.exchange(serverUrlLogin, HttpMethod.POST, entity, AuthorizationResponseDto.class);
        AuthorizationResponseDto responseDto = result.getBody();
        String tokenCode = responseDto.getToken();
        responseDto.setToken(startWord + tokenCode);
        log.info("IN Authorizationcontroller : Sign in user : {}", responseDto.getUsername());
        log.info("IN Authorizationcontroller : Sign in with token {}", responseDto.getToken());
        log.info("IN Authorizationcontroller : Sign in with roles {}", responseDto.getRoles());
        session.setAttribute("username", responseDto.getUsername());
        session.setAttribute("token",responseDto.getToken());
        session.setAttribute("roles",responseDto.getRoles());

        if (session.getAttribute("roles").toString().contains("ROLE_ADMIN")) {
            return "redirect:/allUsers";
        } else {
            return "redirect:/greeting";
        }

    } catch (HttpClientErrorException e) {
        System.out.println("Вернулся статус ошибки");
        session.setAttribute("error", "error403");
        return "redirect:login?error";
    }

}

    @PostMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("username");
        session.removeAttribute("token");
        session.removeAttribute("roles");
        return "redirect:login?logout";

    }

}
