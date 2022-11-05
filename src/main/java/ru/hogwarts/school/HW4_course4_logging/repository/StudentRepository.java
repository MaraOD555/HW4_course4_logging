package ru.hogwarts.school.HW4_course4_logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.HW4_course4_logging.model.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { //Spring ищет сущности по определенным полям
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int from, int to);

    @Query(value = "SELECT count(id) FROM student", nativeQuery = true)
    int totalCountOfStudents();
    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    double averageAgeOfStudents();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true) // последние студенты (5) по id, ранжирование от самого большого
    List<Student> lastStudents();
}
