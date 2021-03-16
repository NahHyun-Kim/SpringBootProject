package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface가 interface를 받는 경우(extends 사용)
//spring data jpa가 구현체를 자동으로 만들어 등록
public interface SpringDataJpaMemberRepository<findByName> extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);


}
