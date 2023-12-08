package me.winiarski.mydojo_pl.registration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

   public String register(@RequestBody RegistrationRequest request){
       return registrationService.register(request);
   }
}
