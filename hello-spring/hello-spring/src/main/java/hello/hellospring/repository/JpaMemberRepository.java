package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //jpa는 모든 것이 EntityManager를 통해 동작
    private final EntityManager em;

    //만들어진 것을 injection(Entity Manager를 주입받음)
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist를 통해 영속(영구 저장)
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //jpa를 통해 자동 select 쿼리로 실행된 값을 member형태로 저장 후,
        Member member = em.find(Member.class, id);
        //값이 없을 수도 있으므로 Optional.ofNullable 형태로 반환
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //name같은 경우에는 jpqa라는 객체지향 query를 사용.
        //테이블 대상이 아닌 객체(entity)를 대상으로 쿼리를 날림
        //select m from Member as m, 멤버 entity 자체를 select해서 조회 후 끝냄
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //(단축키!)ctrl+alt+n을 통해 저장값과 return값을 한 라인으로 합쳐주며 반환
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
