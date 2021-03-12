package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//spring container에서의 spring bean 관리
//spring container라는 통이 생기는데, Membercontroller라는 객체를 생성해 스프링에 넣어두고, 스프링이 관리함
//@Component 안에 @Controller 어노테이션이 있어, 스프링 빈으로 자동 등록(컴포넌트 스캔)
@Controller
public class MemberController {

    private MemberService memberService;

    //생성자로 연결(generate -> constructor) - 권장
    //memberservice를 spring이 가져와 연결시켜줌
    //DI(dependency injection, 의존성 주입)
    // - @Autowired 객체는 스프링이 관리하는 객체에서만 동작함
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 정형화된 코드 관리에는 컴포넌트 스캔을 사용
    // @Autowired private MemberService memberService(필드 주입), 추천 x
    // setter + @autowired 주입(생성 + 호출), setter injection - public으로 선언되어 있어야 함

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //데이터를 form에 넣어서 전달할 때 주로 사용, getMapping은 주로 조회 시
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        //MemberForm의 name에 받아온 name값을 세팅해줌
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/"; //홈 화면으로 돌려줌
    }

    @GetMapping("/members")
    public String list(Model model) {
        // ctrl+alt+v로 리스트 형태 변수 자동완성, 모든 회원 정보를 가져옴
        // list 형태 자체로 담아 view(화면)에 넘김
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
