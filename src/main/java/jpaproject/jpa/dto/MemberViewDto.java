package jpaproject.jpa.dto;

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
    private Address address;

    public void updateMember(Member member) {
       mem

    }
}
