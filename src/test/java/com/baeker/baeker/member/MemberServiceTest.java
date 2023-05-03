package com.baeker.baeker.member;

import com.baeker.baeker.base.request.RsData;
import com.baeker.baeker.member.form.MemberJoinForm;
import com.baeker.baeker.myStudy.MyStudyService;
import com.baeker.baeker.study.Study;
import com.baeker.baeker.study.StudyService;
import com.baeker.baeker.study.form.StudyCreateForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private StudyService studyService;
    @Autowired private MyStudyService myStudyService;
    @Autowired private MemberRepository memberRepository;

    private Member create(String username, String name) {
        MemberJoinForm form = new MemberJoinForm(username, name, "", "1234", "1234", null);
        return memberService.join(form).getData();
    }

    private Study createStudy(String name, Member member) {
        StudyCreateForm form = new StudyCreateForm(name, "about", 10);
        Study study = studyService.create(form, member).getData();
        myStudyService.create(member, study);
        return study;
    }

    @Test
    void 회원가입() {
        MemberJoinForm form = new MemberJoinForm("username", "name",  "","1234", "1234" ,null);
        RsData<Member> memberRs = memberService.join(form);
        Member member = memberRs.getData();

        Member findMember = memberService.getMember("username").get();

        assertThat(memberRs.getResultCode()).isEqualTo("S-1");
        assertThat(member).isSameAs(findMember);
        assertThat(member.getNickName()).isEqualTo(findMember.getNickName());
    }

    @Test
    void 프로필_변경() {
        Member member = create("user1", "member1");

        assertThat(member.getNickName()).isEqualTo("member1");

        Optional<Member> findMember = memberRepository.findByNickName("member1");

        assertThat(findMember.isPresent()).isTrue();

        String nickName = findMember.get().getNickName();
        System.out.println(nickName);
    }

    @Test
    void name() {
        System.out.println(memberService.verifyCode());
    }
}