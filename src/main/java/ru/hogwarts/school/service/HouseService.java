package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public HouseService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.facultyToDTO(facultyRepository.save(faculty));
    }

    public FacultyDTO getFacultyById(Long id) {
        return FacultyDTO.facultyToDTO(facultyRepository.findById(id).orElse(null));
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.facultyToDTO(facultyRepository.save(faculty));
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<FacultyDTO> getAllFacultyByColor(String color){
        Collection<Faculty> faculties = facultyRepository.getAllFacultyByColor(color);
        return faculties.stream().map(FacultyDTO::facultyToDTO).collect(Collectors.toList());
    }
    public Collection<FacultyDTO> getAllFaculty(){
        Collection<Faculty> faculties = facultyRepository.findAll();
        return faculties.stream().map(FacultyDTO::facultyToDTO).collect(Collectors.toList());
    }
    public FacultyDTO getFacultyByName(String name){

        return FacultyDTO.facultyToDTO(facultyRepository.findFacultyByNameIgnoreCase(name));
    }
    public FacultyDTO getFacultyByStudentId(Long studentId){
        Student student = studentRepository.getReferenceById(studentId);
        Faculty faculty = student.getFaculty();
        return FacultyDTO.facultyToDTO(faculty);
    }
}
