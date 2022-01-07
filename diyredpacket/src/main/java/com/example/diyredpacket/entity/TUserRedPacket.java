package com.example.diyredpacket.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_red_packet")
public class TUserRedPacket {
    @Id
    private Long id;

    @Column(name = "red_packet_id")
    private Long redPacketId;

    @Column(name = "user_id")
    private Long userId;

    private BigDecimal amount;

    @Column(name = "grab_time")
    private Date grabTime;

    private String note;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return red_packet_id
     */
    public Long getRedPacketId() {
        return redPacketId;
    }

    /**
     * @param redPacketId
     */
    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return grab_time
     */
    public Date getGrabTime() {
        return grabTime;
    }

    /**
     * @param grabTime
     */
    public void setGrabTime(Date grabTime) {
        this.grabTime = grabTime;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}