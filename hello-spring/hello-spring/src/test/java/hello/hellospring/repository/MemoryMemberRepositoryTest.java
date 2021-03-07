package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test는 서로 의존관계가 없이 설계가 되어야 함.
    //test가 끝날때마다 이미 특정 객체가 들어와있어 테스트 시 발생하는 오류를 없애기 위해, 매번 test가 끝나면 메모리를 clear해줌
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //optional에서 값을 꺼낼때는 get으로 꺼낼 수 있다.
        Member result = repository.findById(member.getId()).get();
        Optional<Member> byId = repository.findById(member.getId());
        //비교로 같은지(console에는 출력되지 않지만(녹색불), null같이 다른 값을 입력할 경우 오류가 뜸)
        Assertions.assertEquals(member, result);
        //member가 result와 똑같은 경우(alt+enter로 앞의 Assertions를 static import하여 생략)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+f6키를 통해 ctrl+c/v를 한 변수값을 한번에 바꿀 수 있음
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //뒤에 .get()메소드를 사용해, Optional에 들어있는 객체를 꺼내옴(바로 Member result로 표기 가능)
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}