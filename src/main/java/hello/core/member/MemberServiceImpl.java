package hello.core.member;

import hello.core.repository.MemberMemoryRepository;
import hello.core.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemberMemoryRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
