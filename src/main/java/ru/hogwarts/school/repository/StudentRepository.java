package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> getAllStudentByAge(Integer age);

    Collection<Student> findStudentByAgeBetween(Integer min, Integer max);

    Collection<Student> findStudentByFacultyId(Long facultyId);

    @Query(value = "select count (s) from student s", nativeQuery = true)
    Long getCountOfStudent();

    @Query(value = "select avg (s.age) from student s", nativeQuery = true)
    Integer avgAgeStudent();

    @Query(value = "select * from student order by student.age limit 5", nativeQuery = true)
    Collection<Student> findFirstStudentByAge();
}
