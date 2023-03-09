package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
@Service
public class HouseService {
    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFacultyByColor(String color){
        return facultyRepository.getAllFacultyByColor(color);
    }
    public Collection<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }
}
