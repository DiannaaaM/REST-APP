package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.REST_APP.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Avatar save(Avatar avatar);
    Avatar findById(long id);
}
