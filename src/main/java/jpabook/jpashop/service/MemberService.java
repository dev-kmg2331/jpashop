package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long register(Member member) {
        validateDuplicateMember(member.getName());

        return memberRepository.save(member);
    }

    private void validateDuplicateMember(String name) {
        List<Member> findMembers = memberRepository.findBy(name, null);
        if (findMembers.size() > 0) {
            // EXCEPTION
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * */
    @Transactional(readOnly = true)
    public List<Member> getMemberList(){
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * */
    @Transactional(readOnly = true)
    public Member getMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
