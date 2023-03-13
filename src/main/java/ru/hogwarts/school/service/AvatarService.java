package ru.hogwarts.school.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
@Data
public class AvatarService {
    @Value("${students.avatar.dir.path}")
    private String avatarDir;
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;
    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException{
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        Path filePath = Path.of(avatarDir, studentId+"."+
                getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = Files.newOutputStream(filePath,CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024))
             {
                 bufferedInputStream.transferTo(bufferedOutputStream);
             }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(studentDTO.toStudent());
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImageData(filePath));
        avatarRepository.save(avatar);
    }
    public Avatar findAvatar(Long studentId){
       return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }
    private String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
    private byte[] generateImageData(Path filePath) throws IOException{
        try(InputStream inputStream = Files.newInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream,1024);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            BufferedImage image = ImageIO.read(bufferedInputStream);
            int height = image.getHeight()/(image.getWidth()/100);
            BufferedImage preview = new BufferedImage(100, height,image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image,0,0,100,height,null);
            graphics.dispose();
            ImageIO.write(preview,getExtension(filePath.getFileName().toString()),byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
