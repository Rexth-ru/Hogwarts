package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.function.Predicate;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> getAllFacultyByColor(String color);
    Faculty findFacultyByNameIgnoreCase(String name);
 }
