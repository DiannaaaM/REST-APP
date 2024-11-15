package ru.hogwarts.school.REST_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.REST_APP.model.Avatar;

import java.util.List;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Avatar save(Avatar avatar);
    Avatar findById(long id);

    @Query(value = "SELECT * FROM Student expenses OFFSET 4", nativeQuery = true)
    List<Avatar> findAll(Integer offset, Integer limit);
}
