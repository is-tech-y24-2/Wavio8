package restApiKotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;
import restApiKotiki.service.CatsService;

import java.util.List;

@RestController
@RequestMapping("/kotiki")
public class CatsController {
    private final CatsService catsService;

    public CatsController(CatsService catsService) {
        this.catsService = catsService;
    }

    @PostMapping("/owner/create")
    public ResponseEntity<Integer> createOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(catsService.createOwner(owner));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable int id) {
        return ResponseEntity.ok(catsService.getOwnerByID(id));
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(catsService.getAllOwners());
    }


    @DeleteMapping("/owner/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOwnerByID(@PathVariable int id) {
        catsService.deleteOwnerByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/owner/change-date/{id}")
    public ResponseEntity<HttpStatus> changeBirthDateOwner(@PathVariable int id,
                                                           @RequestBody java.sql.Timestamp birthDateOwner) {
        catsService.changeBirthDateOwner(id, birthDateOwner);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cat/create/{id}/owner")
    public ResponseEntity<Integer> createCatWithOwner(@RequestBody Cat cat, @PathVariable int id) {
        return ResponseEntity.ok(catsService.createCatWithOwner(cat, id));
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable int id) {
        return ResponseEntity.ok(catsService.getCatByID(id));
    }

    @GetMapping("/cats")
    public ResponseEntity<List<Cat>> getAllCats() {
        return ResponseEntity.ok(catsService.getAllCats());
    }

    @DeleteMapping("/cat/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCatByID(@PathVariable int id) {
        catsService.deleteCatByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cat/find-cats/{id}/owner")
    public ResponseEntity<List<Cat>> getAllCatsByOwner(@PathVariable int id) {
        return ResponseEntity.ok(catsService.getAllCatsByOwner(id));
    }

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

