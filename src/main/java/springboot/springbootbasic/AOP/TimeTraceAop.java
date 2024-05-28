package springboot.springbootbasic.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // @Aspect를 해줘야 AOP를 쓸 수 있다.
@Component
public class TimeTraceAop {

    @Around("execution(* springboot.springbootbasic..*(..))") // 공통사를 연결시켜주는 것
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START:" + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
           long finish = System.currentTimeMillis();
           long timeMs = finish - start;
           System.out.println("END:" + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
