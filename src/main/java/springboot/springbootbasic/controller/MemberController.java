package springboot.springbootbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.springbootbasic.service.MemberService;

// spring이 뜰 때 Controller가 객체를 생성해서 스프링이 가지고 있음
// 스프링 컨트롤러에서 스프링 빈이 생성됨

// 스프링이 뜰 때 정형화된 패턴
// 컨트롤러를 통해서 외부 요청을 받고 (연결 시켜 줄 때 Autowired 씀)
// 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체를 가져다가 넣어줌 -> 의존성 주입
// 서비스에서 비즈니스 로직을 만들고
// 리포지토리에서 데이터를 저장함

// 스프링 빈을 등록하는 2가지 방법
// 1. 컴포넌트 스캔(@Controller,@Service,@Repository->원래는 @Component 왜 되냐면 안에 포함되어있기 때문에 실행됨)과 자동 의존관계 설정(@Autowired로 컨트롤러 서비스 리포지토리 연결됨)
// 2. 자바 코드로 직접 스프링 빈 등록
// - @Component 어노테이션이 있으면 스프링 빈으로 자동 등록됨
// - @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문
// - @Component를 포함하는 다음 어노테이션도 스프링 빈으로 자동 등록됨
// => @Controller,@Service,@Repository

//스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.(유일하게 하나만 등록해서 공유한다(서비스,리포지토리,컨트롤러 1개씩만 등록))
// 따라서 같은 스프링 빈이면 모두 같은 인스턴스다. -> 메모리 절약되고 좋음
// 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.

@Controller
// 수동으로 @bean을 등록하려면 컨트롤러는 스프링이 관리하기 때문에 컨트롤러는 컴포넌트 스캔으로 올라가고 이는 컴포넌트 스캔이기 때문에 @Autowired로 자동이라 따로 설정 할 수 없다.
// 그래서 @Autowired로 해주면 멤버 서비스를 SpringConfig에 쓴 memberService에 넣어준다.
public class MemberController {

    // 스프링 컨테이너에 등록을 하고 쓰면 되는데 이러면 딱 하나만 등록이 됨
    // 그 외에 굉장히 부가적이고 여러가지 효과를 많이 볼 수 있음
    private final MemberService memberService;

    // 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    @Autowired // 컨트롤러가 연결 시켜줄 때 Autowired 쓰는데 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버 서비스(외부)를 객체(의존관계)로 가져다 넣어줌(의존성 주입)
    public MemberController(MemberService memberService) { // MemberController 에 memberService를 연결시켜줌
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


}