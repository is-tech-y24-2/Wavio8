package microKotikiApi.controller;

import dto.*;
import microKotikiApi.rabbitMq.RabbitMqSender;
import microKotikiApi.service.AccountService;
import models.Account;
import models.Cat;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//import restApiKotiki.dto.CatRequest;
//import restApiKotiki.dto.CatResponse;
//import restApiKotiki.dto.OwnerRequest;
//import restApiKotiki.dto.OwnerResponse;
//import restApiKotiki.models.Cat;
//import restApiKotiki.models.Owner;
//import restApiKotiki.service.CatsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/kotiki")
public class CatsController {
    private static final int FORBIDDEN = -1;
//    private final CatsService catsService;

    private final RabbitMqSender rabbitMqSender;
    private final AccountService accountService;

    @Autowired
    public CatsController(RabbitMqSender rabbitMqSender, AccountService accountService) {
        this.rabbitMqSender = rabbitMqSender;
        this.accountService = accountService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/owner/create")
    public ResponseEntity<Integer> createOwner(@RequestBody OwnerRequest owner) {
        return ResponseEntity.ok(rabbitMqSender.createOwner(owner));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerResponse> getOwner(@PathVariable int id) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
            return null;
        }

        return ResponseEntity.ok(rabbitMqSender.getOwnerByID(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {

        return ResponseEntity.ok(rabbitMqSender.getAllOwners());

    }
//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/owner/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOwnerByID(@PathVariable int id) {
        rabbitMqSender.deleteOwnerByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/owner/change-date/{id}")
    public ResponseEntity<HttpStatus> changeBirthDateOwner(@PathVariable int id,
                                                           @RequestBody java.sql.Timestamp birthDateOwner) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rabbitMqSender.changeBirthDateOwner(id,birthDateOwner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cat/create/owner")
    public ResponseEntity<HttpStatus> createCatWithOwner(@RequestBody CatRequest catRequest) {
//        if (catsService.createCatWithOwner(catRequest.toCat(), id) == FORBIDDEN) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        catsService.createCatWithOwner(catRequest.toCat(), id);
        rabbitMqSender.createCatWithOwner(catRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<CatResponse> getCat(@PathVariable int id) {
        return ResponseEntity.ok(rabbitMqSender.getCat(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cats")
    public ResponseEntity<List<CatResponse>> getAllCats() {
        return ResponseEntity.ok(rabbitMqSender.getAllCats());

    }

    @DeleteMapping("/cat/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCatByID(@PathVariable int id) {
        rabbitMqSender.deleteCatByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cat/find-cats/{id}/owner")
    public ResponseEntity<List<CatResponse>> getAllCatsByOwner(@PathVariable int id) {

        List<CatResponse> catResponses=new ArrayList<>();
        for (CatResponse cat : rabbitMqSender.getAllCats()) {
            if(id==cat.getOwner().getId()){
                catResponses.add(cat);
            }
        }
        return ResponseEntity.ok(catResponses);
    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/cat/{id}/friend/{ids}")
//    public ResponseEntity<HttpStatus> makeFriendsCat(@PathVariable int id, @PathVariable int ids) {
//        catsService.makeFriendsCat(id, ids);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
    @GetMapping("/cat/find-cats/{colour}")
    public ResponseEntity<List<Cat>> getCatByColour(@PathVariable String colour) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        AccountResponse accountResponse=new AccountResponse();
        accountResponse.setRole(account.getRole());
        OwnerResponse ownerResponse=new OwnerResponse();
        accountResponse.setColour(colour);
        accountResponse.setOwnerResponse(ownerResponse.toOwnerResponse(account.getOwner()));

        return ResponseEntity.ok(catsService.getCatByColour(colour));
    }

}
