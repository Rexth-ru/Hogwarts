package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("id")
    public ResponseEntity<Faculty> getFacultyById(@RequestParam("id") Long id){
        Faculty faculty = houseService.getFacultyById(id);
        if (faculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return houseService.createFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty updateFaculty = houseService.updateFaculty(faculty);
        if (updateFaculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateFaculty);
    }
    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id){
         houseService.deleteFaculty(id);
    }
    @GetMapping("color")
    public ResponseEntity<Collection<Faculty>> getAllFacultyByColor(@RequestParam("color") String color){
        Collection<Faculty> faculties = houseService.getAllFacultyByColor(color);
        if (faculties==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty(){
        Collection<Faculty> allFaculties = houseService.getAllFaculty();
        if (allFaculties==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allFaculties);
    }
}
