package jpaproject.jpa.Controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jpaproject.jpa.domain.Address;
import jpaproject.jpa.domain.Member;
import jpaproject.jpa.dto.MemberDto;
import jpaproject.jpa.dto.MemberViewDto;
import jpaproject.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String create(@ModelAttribute @Valid MemberDto form, BindingResult result,
        Model model) {

        //에러에게 맞게 문구 나가기
        if (result.hasErrors()) {

            Map<String, String> validatorResult = memberService.validateHandling(result);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));

            }

            return "members/createMemberForm";
        }

        // 비밀번호 일치 확인
        if (!form.getPasswordDTO().getPassword()
            .equals(form.getPasswordDTO().getConfirmPassword())) {
            result.rejectValue("passwordDTO.confirmPassword", "error.passwordDTO",
                "비밀 번호가 일치하지 않습니다.");
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(),
            form.getZipcode());
        System.out.println("===========================================");
        System.out.println("address.getCity() = " + address.getCity());
        System.out.println("address.getCity() = " + address.getZipcode());
        System.out.println("address.getCity() = " + address.getStreet());
        System.out.println("===========================================");

        Member member = new Member();
        member.createInfo(form.getName(), form.getEmail(), form.getPhone(), address);

        // 비밀 번호 암호화
        String encode = passwordEncoder.encode(form.getPasswordDTO().getPassword());
        member.createPasswordInfo(encode);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> findMembers = memberService.findMembers();

        List<MemberViewDto> memberViewDto = findMembers.stream()
            .map(m -> new MemberViewDto(
                m.getId(),
                m.getName(),
                m.getEmail(),
                m.getPhone(),
                m.getAddress()))
            .collect(Collectors.toList());

        model.addAttribute("members", memberViewDto);
        return "members/memberList";
    }

    @GetMapping("/members/{memberId}/edit")
    public String viewMember(@PathVariable Long memberId, Model model,
        MemberDto dto) {
        Member findMember = memberService.findOne(memberId);

        dto.setName(findMember.getName());
        dto.setEmail(findMember.getEmail());
        dto.setPhone(findMember.getPhone());
        dto.setCity(findMember.getAddress().getCity());
        dto.setStreet(findMember.getAddress().getStreet());
        dto.setZipcode(findMember.getAddress().getZipcode());
        model.addAttribute("memberView", dto);

        System.out.println("===============================");
        System.out.println(memberId);
        System.out.println("===============================");

        return "/members/updateMemberForm";
    }

    @PostMapping("/members/{memberId}/edit")
    public String UpdateMember(@PathVariable Long memberId,
        @ModelAttribute("memberView") MemberDto form) {

        System.out.println("================================");
        System.out.println("dto.getZipcode() = " + form.getName());
        System.out.println("dto.getZipcode() = " + form.getEmail());
        System.out.println("dto.getZipcode() = " + form.getZipcode());
        System.out.println("dto.getZipcode() = " + form.getStreet());
        System.out.println("dto.getZipcode() = " + form.getCity());
        System.out.println("================================");

        memberService.update(memberId, form);

        return "redirect:/members";
    }
}
