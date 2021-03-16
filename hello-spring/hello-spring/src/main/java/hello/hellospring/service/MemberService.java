package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//springcontainer에 memberservice를 등록
@Service
//@Repository //@Service는 오류를 잡기 위해 우선 추가
//jpa를 사용 시, transactional(데이터 저장, 변경 시) 꼭 표기
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //generate-constructor로 외부에서 넣어주도록 지정(alt+insert)
    //memberService의 입장에서 볼때, 직접 넣는것이 아니라 외부에서 넣어줌
    //DI(의존성 주입)
    //@Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
    * */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        //null이 아니라 이미 값이 있으면(null의 존재가능성이 있으면 Optional로 감싸고, 확인은 ifPresent로 함)
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
