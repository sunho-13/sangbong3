package com.softagape.myjpa.phonebook;

import com.softagape.myjpa.category.CategoryEntity;
import com.softagape.myjpa.category.ICategory;
import com.softagape.myjpa.category.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pb")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private IPhoneBookService<IPhoneBook> phoneBookService;

    @Autowired
    private ICategoryService<ICategory> categoryService;

    @PostMapping
    public ResponseEntity<IPhoneBook> insert(@RequestBody PhoneBookRequest dto) {
        try {
            if ( dto == null ) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.insert(dto);
            if ( result == null ) {
                return ResponseEntity.badRequest().build();
            }
            if ( result.getCategory() != null ) {
                ICategory find = this.categoryService.findById(result.getCategory().getId());
                result.setCategory(find);
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<IPhoneBook>> getAll() {
        try {
            List<IPhoneBook> result = this.phoneBookService.getAllList();
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            if ( id == null ) {
                return ResponseEntity.badRequest().build();
            }
            Boolean result = this.phoneBookService.remove(id);
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IPhoneBook> update(@PathVariable Long id, @RequestBody PhoneBookRequest dto) {
        try {
            if ( id == null || dto == null ) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.update(id, dto);
            if ( result == null ) {
                return ResponseEntity.notFound().build();
            }
            if ( result.getCategory() != null ) {
                ICategory find = this.categoryService.findById(result.getCategory().getId());
                result.setCategory(find);
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IPhoneBook> findById(@PathVariable Long id) {
        try {
            if ( id == null || id <= 0 ) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.findById(id);
            if ( result == null ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/nm/{name}")
    public ResponseEntity<List<IPhoneBook>> findAllByNameContains(@PathVariable String name) {
        try {
            if ( name == null || name.isEmpty() ) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromName(name);
            if ( result == null || result.size() <= 0 ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ct/{category}")
    public ResponseEntity<List<IPhoneBook>> findAllByCategory(@PathVariable Integer category) {
        try {
            if ( category == null ) {
                return ResponseEntity.badRequest().build();
            }
            CategoryEntity categoryDto = CategoryEntity.builder()
                    .id(Long.parseLong(category.toString()))
                    .build();
            List<IPhoneBook> result = this.phoneBookService.getListFromCategory(categoryDto);
            if ( result == null || result.size() <= 0 ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pn/{phoneNumber}")
    public ResponseEntity<List<IPhoneBook>> findAllByPhoneNumberContains(@PathVariable String phoneNumber) {
        try {
            if ( phoneNumber == null || phoneNumber.isEmpty() ) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromPhoneNumber(phoneNumber);
            if ( result == null || result.size() <= 0 ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/em/{email}")
    public ResponseEntity<List<IPhoneBook>> findAllByEmailContains(@PathVariable String email) {
        try {
            if ( email == null || email.isEmpty() ) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromEmail(email);
            if ( result == null || result.size() <= 0 ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            logger.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }
}
