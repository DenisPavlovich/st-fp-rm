package st4.fp.dp.dto;

import st4.fp.dp.annotation.Column;
import st4.fp.dp.annotation.Id;
import st4.fp.dp.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by denis on 11.09.17.
 */
@Table("apartments")
public class Apartments implements Dto {
    @Id
    @Column("id")
    private Long id;
    @Column("number")
    private Integer number;
    @Column("rooms")
    private Integer rooms;
    @Column("person_count")
    private Integer personCount;
    @Column("price")
    private Double price;
    @Column("status")
    private ApartmentStatus status;
    @Column("rank")
    private ApartmentType rank;
    @Column("photo_path")
    private String photoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ApartmentStatus getStatus() {
        return status;
    }

    public void setStatus(ApartmentStatus status) {
        this.status = status;
    }

    public ApartmentType getRank() {
        return rank;
    }

    public void setRank(ApartmentType rank) {
        this.rank = rank;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public enum ApartmentStatus {
        FREE,
        BOOKED,
        BUSY,
        NOT_AVAILABLE
    }
}
