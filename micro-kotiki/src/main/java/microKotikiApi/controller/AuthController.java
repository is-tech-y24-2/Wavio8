package microKotikiApi.controller;

import microKotikiApi.security.dto.RegistrationRequest;
import microKotikiApi.security.dto.RegistrationUserRequest;
import microKotikiApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AuthController {
    @Autowired
    private AccountService accountService;
//
//    @Autowired
//    private JwtProvider jwtProvider;

//    @GetMapping("/register")
//    public String registerUser(){
//        return "OK";
//    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerAccount(@RequestBody @Valid RegistrationRequest registrationRequest) {
        accountService.saveAccount(registrationRequest.toAccount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/user")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody @Valid RegistrationUserRequest registrationUserRequest) {
        if (registrationUserRequest.getOwner() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        accountService.createUser(registrationUserRequest.toAccount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/owner/delete")
    public ResponseEntity<HttpStatus> deleteUserByLogin(@RequestBody @Valid RegistrationUserRequest registrationUserRequest) {
        if (registrationUserRequest.getLogin() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!accountService.checkUserRole(accountService.findByLogin(registrationUserRequest.getLogin()).getRole().toString())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.deleteUserByLogin(registrationUserRequest.getLogin());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/adm")
//    public String registerAccount() {
//        return "ADMIN";
//    }

//    @PostMapping("/auth")
//    public AuthResponse auth(@RequestBody AuthRequest request){
//        Account account=accountService.findByLoginAndPassword(request.getLogin(),request.getPassword());
//        String token=jwtProvider.generateToken(account.getLogin());
//        return new AuthResponse(token);
//    }
}

