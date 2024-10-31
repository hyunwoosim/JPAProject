package jpaproject.jpa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpaproject.jpa.domain.Address;
import jpaproject.jpa.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    @Valid
    private PasswordDTO passwordDTO;

    @NotEmpty(message = "회원 이메일 필수입니다.")
//    @Email(message = "유효한 이메일 주소여야합니다.")
    private String email;

    @NotEmpty(message = "회원 전화번호는 필수입니다.")
    private String phone;

    private String city;
    private String street;
    private String zipcode;


    public MemberDto() {
    }

    public Address toAddress() {
        return new Address(city, street, zipcode);
    }

    public void updateMember(Member member) {
        member.updateMember(this.name, this.email, this.phone, toAddress());
    }
}
