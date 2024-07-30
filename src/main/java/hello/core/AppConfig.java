package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.repository.MemberMemoryRepository;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemberMemoryRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemberMemoryRepository(), new RateDiscountPolicy());
    }
}
