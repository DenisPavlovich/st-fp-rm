package st4.fp.dp.dto;

import st4.fp.dp.annotation.Column;
import st4.fp.dp.annotation.Id;
import st4.fp.dp.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by denis on 11.09.17.
 */
@Table("users")
public class Users implements Dto {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("age")
    private Integer age;
    @Column("phone_number")
    private String phoneNumber;
    @Column("email")
    private String email;
    @Column("priceb")
    private BigDecimal priceb;
    @Column("priced")
    private Double priced;

    public void setPriceb(BigDecimal priceb) {
        this.priceb = priceb;
    }

    public void setPriced(Double priced) {
        this.priced = priced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
