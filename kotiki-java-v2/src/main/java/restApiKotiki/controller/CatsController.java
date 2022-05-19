package restApiKotiki.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restApiKotiki.dto.CatRequest;
import restApiKotiki.dto.CatResponse;
import restApiKotiki.dto.OwnerRequest;
import restApiKotiki.dto.OwnerResponse;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;
import restApiKotiki.service.CatsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kotiki")
public class CatsController {
    private static final int FORBIDDEN = -1;
    private final CatsService catsService;

    public CatsController(CatsService catsService) {
        this.catsService = catsService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/owner/create")
    public ResponseEntity<Integer> createOwner(@RequestBody OwnerRequest owner) {
        return ResponseEntity.ok(catsService.createOwner(owner.toOwner()));
    }

    //    @PreAuthorize("#id == authentication.principal.owner.getOwnerId")
    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerResponse> getOwner(@PathVariable int id) {
        OwnerResponse ownerResponse=new OwnerResponse();
        return ResponseEntity.ok(ownerResponse.toOwnerResponse(catsService.getOwnerByID(id)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        List<OwnerResponse> ownerResponses=new ArrayList<>();
        OwnerResponse ownerResponse=new OwnerResponse();
        for (Owner owner : catsService.getAllOwners()) {
            ownerResponses.add(ownerResponse.toOwnerResponse(owner));
        }
        return ResponseEntity.ok(ownerResponses);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/owner/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOwnerByID(@PathVariable int id) {
        catsService.deleteOwnerByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/owner/change-date/{id}")
    public ResponseEntity<HttpStatus> changeBirthDateOwner(@PathVariable int id,
                                                           @RequestBody java.sql.Timestamp birthDateOwner) {
        catsService.changeBirthDateOwner(id, birthDateOwner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cat/create/{id}/owner")
    public ResponseEntity<HttpStatus> createCatWithOwner(@RequestBody CatRequest catRequest, @PathVariable int id) {
        if (catsService.createCatWithOwner(catRequest.toCat(), id) == FORBIDDEN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catsService.createCatWithOwner(catRequest.toCat(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<CatResponse> getCat(@PathVariable int id) {
        CatResponse catResponse=new CatResponse();
        Cat cat = catsService.getCatById(id);

        return ResponseEntity.ok(catResponse.toCatResponse(cat));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cats")
    public ResponseEntity<List<CatResponse>> getAllCats() {
        CatResponse catResponse=new CatResponse();
        List<CatResponse> catResponses=new ArrayList<>();
        for (Cat cat : catsService.getAllCats()) {
            catResponses.add(catResponse.toCatResponse(cat));
        }

        return ResponseEntity.ok(catResponses);
    }

    @DeleteMapping("/cat/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCatByID(@PathVariable int id) {
        catsService.deleteCatByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cat/find-cats/{id}/owner")
    public ResponseEntity<List<CatResponse>> getAllCatsByOwner(@PathVariable int id) {
        CatResponse catResponse=new CatResponse();
        List<CatResponse> catResponses=new ArrayList<>();
        for (Cat cat : catsService.getAllCats()) {
            catResponses.add(catResponse.toCatResponse(cat));
        }
        return ResponseEntity.ok(catResponses);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cat/{id}/friend/{ids}")
    public ResponseEntity<HttpStatus> makeFriendsCat(@PathVariable int id, @PathVariable int ids) {
        catsService.makeFriendsCat(id, ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cat/find-cats/{colour}")
    public ResponseEntity<List<Cat>> getCatByColour(@PathVariable String colour) {
        return ResponseEntity.ok(catsService.getCatByColour(colour));
    }

}

