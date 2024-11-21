package ru.hogwarts.school.REST_APP.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.REST_APP.service.InfoService;

import java.util.List;

@Getter
@RestController
@RequestMapping(" /port")
public class InfoController {

    private final InfoService infoService;
    @Value( "${server.port}" )
    private String port;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/testTask")
    public long testTask(@RequestBody List<Integer> nums) {
        return infoService.calculateSum( nums );
    }

}
