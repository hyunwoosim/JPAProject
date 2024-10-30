package jpaproject.jpa.Controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jpaproject.jpa.domain.Address;
import jpaproject.jpa.domain.Member;
import jpaproject.jpa.dto.MemberCreateDto;
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
    public String create(@ModelAttribute @Valid MemberCreateDto form, BindingResult result,
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

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

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
        MemberViewDto memberViewDto) {
        Member findMember = memberService.findOne(memberId);

        memberViewDto.setName(findMember.getName());
        memberViewDto.setEmail(findMember.getEmail());
        memberViewDto.setPhone(findMember.getPhone());
        memberViewDto.setAddress(findMember.getAddress());
        model.addAttribute("memberView", memberViewDto);
//        System.out.println("===============================");
//        System.out.println("form." + findMember.getAddress().getCity());
//        System.out.println("form." + findMember.getAddress().getStreet());
//        System.out.println("form." + findMember.getAddress().getZipcode());
//        System.out.println("===============================");
//        System.out.println("===============================");
//        System.out.println("form." + memberViewDto.getAddress().getCity());
//        System.out.println("form." + memberViewDto.getAddress().getStreet());
//        System.out.println("form." + memberViewDto.getAddress().getZipcode());
//        System.out.println("===============================");

        System.out.println("===============================");
        System.out.println();
        System.out.println(memberId);
        System.out.println("===============================");

        return "/members/updateMemberForm";
    }

    @PostMapping("/members/{memberId}/edit")
    public String UpdateMember(@PathVariable Long memberId,
        @ModelAttribute("memberView") MemberForm form) {

        System.out.println("===============================");
        System.out.println(memberId);
        System.out.println("===============================");

        MemberViewDto dto = new MemberViewDto();
        dto.setName(form.getName());
        dto.setEmail(form.getEmail());
        dto.setPhone(form.getPhone());

        Address address = new Address(form.getAddress().getCity(), form.getAddress().getStreet(),
            form.getAddress().getZipcode());
        dto.setAddress(address);

        System.out.println("===============================");
        System.out.println("@@@Controller@@@@");
        System.out.println("===============================");
        System.out.println("name" + form.getName());
        System.out.println("email" + form.getEmail());
        System.out.println("form." + form.getAddress().getCity());
        System.out.println("form." + form.getAddress().getZipcode());
        System.out.println("form." + form.getAddress().getStreet());
        System.out.println("===============================");
        System.out.println("dto.getName() = " + dto.getName());
        System.out.println("dto.getAddress() = " + dto.getAddress().getCity());
        System.out.println("dto.getAddress() = " + dto.getAddress().getStreet());
        System.out.println("dto.getAddress() = " + dto.getAddress().getZipcode());
        System.out.println("===============================");

        memberService.update(memberId, dto);

        return "redirect:/members";
    }
}
