package ru.hogwarts.school.HW4_course4_logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.HW4_course4_logging.model.Faculty;
import ru.hogwarts.school.HW4_course4_logging.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("CreateFaculty method was invoked");
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id) {
        logger.info("FindFaculty method was invoked");
        return facultyRepository.findById(id).orElse(null);
    }
    public Faculty editFaculty(Faculty faculty) {
        logger.info("EditFaculty method was invoked");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id){
        logger.info("DeleteFaculty method was invoked");
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findFacultyByColor(String color) {
        logger.info("FindFacultyByColor method was invoked");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("FindByNameIgnoreCase method was invoked");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Faculty> findByColorIgnoreCase(String color) {
        logger.info("FindByColorIgnoreCase method was invoked");
        return facultyRepository.findByColorIgnoreCase(color);
    }
    public Collection<Faculty> getAllFaculty() {
        logger.info("GetAllFaculty method was invoked");
        return facultyRepository.findAll();
    }

}
