package ru.hogwarts.school.HW4_course4_logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.HW4_course4_logging.model.Avatar;
import ru.hogwarts.school.HW4_course4_logging.model.Student;
import ru.hogwarts.school.HW4_course4_logging.repository.AvatarRepository;
import ru.hogwarts.school.HW4_course4_logging.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
            logger.warn("Method - uploadAvatar was invoked");
            Student student = studentRepository.findById(studentId).orElse(null);
            Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            try (
                    InputStream is = avatarFile.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }
            Avatar avatar = findAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(avatarFile.getSize());
            avatar.setMediaType(avatarFile.getContentType());
            avatar.setData(avatarFile.getBytes());
            avatarRepository.save(avatar);
        }

    public Avatar findAvatar(long studentId) {
        logger.info("Method - findAvatar was invoked");
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        logger.info("Method - getExtensions was invoked");
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
    public List<Avatar> findByPagination (int page, int size){
        logger.info("Method - findByPagination was invoked");
        return avatarRepository.findAll(PageRequest.of(page, size)).get()
                  .collect(Collectors.toList());
    }
}
