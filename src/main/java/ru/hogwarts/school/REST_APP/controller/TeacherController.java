package ru.hogwarts.school.REST_APP.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequestMapping("/port")
@Profile( "teacher" )
public class TeacherController {
    @Value("${server.port}")
    private String port;

}
