package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
// @Autowired, Component 스캔 방식 외에 직접 springconfig 파일을 통해 스프링 빈 등록이 가능하다.
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    //sequence : 0,1,2와 같이 key 값을 생성해줌
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //시스템이 정해주는 id값을 지정
        member.setId(++sequence);
        //map 형식의 store에 저장
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이여도 감싸서 반환할 수 있는 Optional.ofNullable();
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // map에서 돌리면서 하나라도 있으면 반환(findAny), 끝까지 없는 경우 Optional이 null로 감싸서 반환
        return store.values().stream()
                //loop로 돌림(member.getName이 파라미터로 넘어온 name과 같은지 확인)
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store에 있는 values(member)를 list형태로 반환하라.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        //작업 후 store를 모두 비우기 위해 .clear() 사용
        store.clear();
    }
}
