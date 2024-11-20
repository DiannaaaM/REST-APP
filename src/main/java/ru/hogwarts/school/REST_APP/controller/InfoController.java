package ru.hogwarts.school.REST_APP.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequestMapping(" /port")
public class InfoController {
    @Value( "${server.port}" )
    private String port;

}
