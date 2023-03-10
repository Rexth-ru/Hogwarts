package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;


@RestController
@RequestMapping("faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id){
        FacultyDTO facultyDTO = houseService.getFacultyById(id);
        if (facultyDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }
    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyDTO facultyDTO) {
        return houseService.createFaculty(facultyDTO);
    }
    @PutMapping
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO){
        FacultyDTO updateFacultyDTO = houseService.updateFaculty(facultyDTO);
        if (updateFacultyDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateFacultyDTO);
    }
    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id){
         houseService.deleteFaculty(id);
    }
    @GetMapping()
    public ResponseEntity getFaculties(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long studentId){
        if (color!=null && !color.isEmpty()) {
           return ResponseEntity.ok(houseService.getAllFacultyByColor(color));
        }
        if (name!=null && !name.isEmpty()){
           return ResponseEntity.ok(houseService.getFacultyByName(name));
        }
        if (studentId!=null){
            return ResponseEntity.ok(houseService.getFacultyByStudentId(studentId));
        }
        return ResponseEntity.ok(houseService.getAllFaculty());
    }
}
