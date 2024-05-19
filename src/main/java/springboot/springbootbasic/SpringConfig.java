package springboot.springbootbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.springbootbasic.repository.MemberRepository;
import springboot.springbootbasic.repository.MemoryMemberRepository;
import springboot.springbootbasic.service.MemberService;

// 스프링이 뜨면 @Configuration가 뜨면 스프링빈에 등록하라는 뜻이네 하고 스프링이 인식한다.
// 이 멤버 서비스를 이 로직을 호출을 해서 스프링빈에 등록 해준다.
@Configuration
public class SpringConfig {

    @Bean // 수동으로 스프링 빈에 등록하겠다는 의미
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 멤버 서비스가 스프링빈에 등록이 된다. -> 생성자에 멤버 리포지토리를 연결 시켜 줘야 헤서 memberRepository() 넣어줘야 한다.
    }

    // 멤버 서비스랑 멤버 리포지토리를 둘 다 스프링빈에 등록하고 등록되어 있는 멤버 리포지토리를 멤버 서비스에 넣어준다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 메모리멤버리포지토리 구현체(인터페이스는 안됨)
    }

}
