package ru.hogwarts.school.HW4_course4_logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.HW4_course4_logging.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> { //расширяет PagingAndSortingRepository
     Optional<Avatar> findAvatarByStudentId (long studentId);
}
