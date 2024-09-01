package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI Container")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberedService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberedService2 = appConfig.memberService();

        // 참조 값이 다른 것 확인
        System.out.println("memberedService1 = " + memberedService1);
        System.out.println("memberedService2 = " + memberedService2);

        // memberService1 != memberService2
        assertThat(memberedService1).isNotSameAs(memberedService2);
    }

    @Test
    @DisplayName("Singleton 객체로 테스트")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isEqualTo(singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("Spring Container와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberedService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberedService2 = ac.getBean("memberService", MemberService.class);

        // 참조 값이 다른 것 확인
        System.out.println("memberedService1 = " + memberedService1);
        System.out.println("memberedService2 = " + memberedService2);

        // memberService1 != memberService2
        assertThat(memberedService1).isSameAs(memberedService2);
    }
}
