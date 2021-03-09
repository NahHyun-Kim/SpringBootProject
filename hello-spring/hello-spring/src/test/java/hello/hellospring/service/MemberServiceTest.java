package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //clear를 위한 객체 생성
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    //같은 memory repository를 사용하도록 설정
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //한 개의 테스트가 끝나면, db의 값을 모두 날려줌
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given(주어짐)
        Member member = new Member();
        member.setName("hello");

        //when(실행했을 때)
        Long saveId = memberService.join(member);

        //then(이런 결과로 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //같은 이름을 두번 넣어서, validate 함수에서 예외가 터짐
        memberService.join(member1);
        //member2를 넣었을때 오류가 터지길 기대
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            //예외가 터져서 정상적으로 성공함
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        } */


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}