package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Support;
import ru.nag.spring.dto.request.SupportRequest;
import ru.nag.spring.dto.request.SupportResponseRequest;
import ru.nag.spring.exception.SupportException.SupportNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.EmailService;
import ru.nag.spring.service.SupportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupportController {

    private final EmailService emailService;
    private final SupportService supportService;
    private final AuthService authService;

    @PostMapping("/support") // ALL
    public ResponseEntity<String> createMailToSupport(@RequestBody SupportRequest supportData) {

        Support support = new Support();

        support.setSenderName(supportData.getSenderName());
        support.setSenderSurname(supportData.getSenderSurname());
        support.setSenderEmail(supportData.getSenderEmail());
        support.setIssue(supportData.getIssue());

        supportService.saveSupport(support);

        return ResponseEntity.ok("Mail created");
    }

    //////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/support") // ROLE_SUPPORT
    public ResponseEntity<List<Support>> getMails() {
        JwtAuthentication authInfo = authService.getAuthInfo();

        List<Support> mailList = supportService.getAll();

        return ResponseEntity.ok(mailList);
    }

    @PostMapping("/support/{id}") // ROLE_SUPPORT
    public ResponseEntity<String> sendResponseToEmail(@PathVariable Integer id,
                                                      @RequestBody SupportResponseRequest responseData) throws SupportNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        Support support = supportService.getSupportById(id);

        emailService.sendSupportMessage(
                support.getSenderEmail(),
                "Ответ от поддержки PetFood",
                "Проблема: %s \nОтвет: %s".formatted(support.getIssue(), responseData.getResponse())
        );

        supportService.deleteSupport(support);

        return ResponseEntity.ok(id + responseData.getResponse());
    }

    @DeleteMapping("/support/{id}") // ROLE_SUPPORT
    public ResponseEntity<String> deleteSupport(@PathVariable Integer id) throws SupportNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        Support support = supportService.getSupportById(id);
        supportService.deleteSupport(support);

        return ResponseEntity.ok("Support deleted");
    }

}
