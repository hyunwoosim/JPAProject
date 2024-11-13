package jpaproject.jpa.repository;

import java.util.List;
import jpaproject.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // select m from Member m where m.name = ?
    // 자동으로 jpql을 만들어 준다.
    List<Member> findByName(String name);
}