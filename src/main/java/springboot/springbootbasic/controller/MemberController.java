package springboot.springbootbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import springboot.springbootbasic.service.MemberService;

// spring이 뜰 때 Controller가 객체를 생성해서 스프링이 가지고 있음
// 스프링 컨트롤러에서 스프링 빈이 생성됨

@Controller
public class MemberController {

    // 스프링 컨테이너에 등록을 하고 쓰면 되는데 이러면 딱 하나만 등록이 됨
    // 그 외에 굉장히 부가적인 여러가지 효과룰 많이 볼 수 있음
    private final MemberService memberService;

    // 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    // 객체 의존관계를 외부에서 넣어주는 것 -> 의존성 주입
    @Autowired // 컨트롤러가 연결 시켜줄 때 Autowired 쓰는데 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체로 가져다 넣어줌(의존성 주입)
    public MemberController(MemberService memberService) { // MemberController 에 memberService를 연결시켜줌
        this.memberService = memberService;
    }

}

// 스프링이 뜰 때 패턴
// 컨트롤러를 통해서 외부 요청을 받고 (연결 시켜 줄 때 Autowired 씀)
// 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체를 가져다가 넣어줌 -> 의존성 주입
// 서비스에서 비즈니스 로직을 만들고
// 리포지토리에서 데이터를 저장함