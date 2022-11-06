package ru.hogwarts.school.HW4_course4_logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.HW4_course4_logging.model.Student;
import ru.hogwarts.school.HW4_course4_logging.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class); // конструкция добавлена, чтобы добавить логгер в класс
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }
    public Student findStudent(long id) {
        logger.debug("Was invoked method for find student");
        return studentRepository.findById(id).orElse(null);
    }
    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }
    public void deleteStudents(long id){
        logger.debug("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int from, int to) {
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetween(from, to);
    }
   public Collection<Student> getAllStudents(){
       logger.info("Was invoked method for findAll");
        return studentRepository.findAll();
  }

    public int totalCountOfStudents() {
        logger.info("Was invoked method for totalCountOfStudents");
        return studentRepository.totalCountOfStudents();
    }
    public double averageAgeOfStudents(){
        logger.info("Was invoked method for averageAgeOfStudents");
        return studentRepository.averageAgeOfStudents();
    }
    public List<Student> lastStudents (){
        logger.info("Was invoked method for lastStudents");
        return new ArrayList<>(studentRepository.lastStudents());

    }
}

