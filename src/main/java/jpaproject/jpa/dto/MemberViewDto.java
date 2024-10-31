package jpaproject.jpa.dto;

import jakarta.validation.Valid;
import jpaproject.jpa.domain.Address;
import jpaproject.jpa.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberViewDto {

    private Long id;
    private String name;
    private String email;
    private String phone;

    @Valid
    private Address address;

    public MemberViewDto() {
    }


    public void updateMember(Member member) {
        member.updateMember(this.name, this.email, this.phone, this.address);
    }
}
