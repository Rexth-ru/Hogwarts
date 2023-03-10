package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> getAllStudentByAge(Integer age);
    Collection<Student> findStudentByAgeBetween(Integer min, Integer max);
    Collection<Student> findStudentByFacultyId(Long facultyId);
}
