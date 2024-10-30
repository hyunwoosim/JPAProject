package jpaproject.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;


@Entity
@Getter
public class Member {

    @OneToMany(mappedBy = "member")
    private final List<Order> orders = new ArrayList<>();
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    @Embedded
    private Address address;

    // == 비즈니스 로직 ==//

    public void createInfo(String name, String email, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.commonAddress(address);
    }

    public void commonAddress(Address address) {
        this.address = address;
    }

    public void createPasswordInfo(String password) {
        this.password = password;
    }

    public void updateMember(String name, String email, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.commonAddress(address);

    }
}
