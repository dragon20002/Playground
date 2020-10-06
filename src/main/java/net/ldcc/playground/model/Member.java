package net.ldcc.playground.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Member {
    private static final Logger logger = LoggerFactory.getLogger(Member.class);

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ID")
    @Id
    private Long id;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String telNo;

    @Column
    private String address;

    @Column
    private String exprDate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private List<BaseGrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExprDate() {
        return exprDate;
    }

    public void setExprDate(String exprDate) {
        this.exprDate = exprDate;
    }

    public Collection<? extends BaseGrantedAuthority> getAuthorities() {
        return null;
    }

    public void setAuthorities(List<BaseGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAccountNonExpired() {
        // 1. 만료일자가 빈 경우, 유효
        if (exprDate == null || exprDate.length() == 0)
            return true;

        // 2. 만료일자 이전인 경우, 유효
        try {
            return DateFormat.getDateInstance().parse(exprDate).before(new Date());
        } catch (ParseException e) {
            logger.debug(e.getMessage());
        }

        return false;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telNo='" + telNo + '\'' +
                ", address='" + address + '\'' +
                ", exprDate='" + exprDate + '\'' +
                '}';
    }
}