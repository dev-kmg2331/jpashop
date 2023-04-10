package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Rollback(false)
    void 회원_가입_테스트(){
        //given
        Member member = new Member();
        member.setName("강민구");
        member.setAddress(new Address("서울특별시", "테헤란로", "00001"));

        //when
        Long registerMemberId = memberService.register(member);

        //then
        assertEquals(registerMemberId, memberService.getMember(registerMemberId).getId());
    }

    @Test
    void 중복_검사_테스트() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("강민구");
        member1.setAddress(new Address("서울특별시", "테헤란로", "00001"));

        Member member2 = new Member();
        member2.setName("강민구");
        member2.setAddress(new Address("서울특별시", "테헤란로", "00001"));

        //when
        memberService.register(member1);

        try {
            memberService.register(member2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생해야 한다.");
    }
}