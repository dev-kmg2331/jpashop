package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional @Rollback(false)
    void memberSaveTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("MemberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember).isEqualTo(member);
        System.out.println(findMember.getClass());
    }
}