package project.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.project.Entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    /*Security*/
    Optional<MemberEntity> findByUsername(String username);

    /*OAuth*/
    Optional<MemberEntity> findByEmail(String email);

    /*user get*/
    MemberEntity findByNickname(String nickname);

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);



}
