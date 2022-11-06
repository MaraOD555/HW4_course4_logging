package ru.hogwarts.school.HW4_course4_logging.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    //Создать контроллер InfoController. Добавить в него один эндпоинт /getPort.
   //Этот эндпоинт будет возвращать одно значение - порт,
    // на котором запущено приложение.
    // В application.properties создать свойство server.port = 8080.
    // Это свойство указывает на порт, который будет использовать приложение.
    // Именно это значение необходимо возвращать в эндпоинте /getPort
    @Value("${server.port}")
    private int port;

    @Value("port")
    public int getPort(){
        return port;
    }
}
