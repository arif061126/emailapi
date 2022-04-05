package com.emailApi.controller;

import com.emailApi.model.EmailRequest;
import com.emailApi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){

        return "this is email api";
    }

    //api to send email
    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){

        System.out.println(emailRequest);

        boolean b= emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
        //return ResponseEntity.ok("Email sent successfully....");
        if(b){
            return ResponseEntity.ok("Email sent successfully....");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent.....");
        }
    }

}
