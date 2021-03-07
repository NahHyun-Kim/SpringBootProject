package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //저장된 회원이 반환
    Member save(Member member);

    //값이 없으면 null로 반환되는데, 그대로 반환 대신 Optional로 감싸 반환
    //저장된 회원을 찾을 수 있음, findAll시 저장된 모든 회원 리스트 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
