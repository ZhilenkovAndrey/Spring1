package main.java.com.zhilencov.persist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<main.java.com.zhilencov.persist.User, Long> {

    List<main.java.com.zhilencov.persist.User> findAllByUsernameLike(String usernameFilter);

    @Query(value = """
            select * from users u
            where (:usernameFilter is null or u.username like :usernameFilter)
            """, nativeQuery = true)
    List<main.java.com.zhilencov.persist.User> usersByUsername(String usernameFilter);
}