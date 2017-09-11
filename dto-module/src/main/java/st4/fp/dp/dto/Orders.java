package st4.fp.dp.dto;

import st4.fp.dp.annotation.Column;
import st4.fp.dp.annotation.Id;
import st4.fp.dp.annotation.Table;

import java.util.Date;

/**
 * Created by denis on 11.09.17.
 */
@Table("orders")
public class Orders implements Dto {
    @Id
    @Column("id")
    private Long id;
    @Column("accountid")
    private Long accountId;
    @Column("apartmentid")
    private Long apartmentId;
    @Column("status")
    private OrderStatus status;
    @Column("person_count")
    private Integer personCount;
    @Column("rank")
    private ApartmentType rank;
    @Column("from")
    private Date from;
    @Column("to")
    private Date to;
    @Column("create_date")
    private Date create_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public ApartmentType getRank() {
        return rank;
    }

    public void setRank(ApartmentType rank) {
        this.rank = rank;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public enum OrderStatus {
        UNCHECKED,
        WAITED,
        PAID,
        IN_PROCESS,
        CANCELED,
        DONE
    }
}
