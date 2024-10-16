package jpaproject.jpa.Controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import jpaproject.jpa.domain.Address;
import jpaproject.jpa.domain.Member;
import jpaproject.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, Model model) {

        System.out.println(
            "form.getPasswordDTO().getPassword() = " + form.getPasswordDTO().getPassword());
        System.out.println(
            "form.getPasswordDTO().getPassword() = " + form.getPasswordDTO().getConfirmPassword());
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("result = " + result.hasErrors());
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("result = " + result);
        System.out.println("++++++++++++++++++++++++++++++");

        //에러에게 맞게 문구 나가기
        if (result.hasErrors()) {

            Map<String, String> validatorResult = memberService.validateHandling(result);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                System.out.println("=====================");
                System.out.println("key = " + key);
                System.out.println("validatorResult = " + validatorResult.get(key));
                System.out.println("=====================");
            }

            return "members/createMemberForm";
        }
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("result 에러 없음");
        System.out.println("++++++++++++++++++++++++++++++");

        // 비밀번호 일치 확인
        if (!form.getPasswordDTO().getPassword()
            .equals(form.getPasswordDTO().getConfirmPassword())) {
            result.rejectValue("passwordDTO.confirmPassword", "error.passwordDTO",
                "비밀 번호가 일치하지 않습니다.");
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setEmail(form.getEmail());
        member.setPhone(form.getPhone());

        // 비밀 번호 암호화
        String encode = passwordEncoder.encode(form.getPasswordDTO().getPassword());
        member.setPassword(encode);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
