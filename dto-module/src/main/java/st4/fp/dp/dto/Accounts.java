package st4.fp.dp.dto;

import st4.fp.dp.annotation.Column;
import st4.fp.dp.annotation.Id;
import st4.fp.dp.annotation.Table;

import java.util.Date;

/**
 * Created by denis on 11.09.17.
 */
@Table("accounts")
public class Accounts implements Dto {
    @Id
    @Column("id")
    private Long id;
    @Column("userid")
    private Long userId;
    @Column("login")
    private String login;
    @Column("password")
    private String password;
    @Column("manager")
    private Integer manager;
    @Column("create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public enum AccountType {
        CLIENT,
        MANAGER
    }
}
