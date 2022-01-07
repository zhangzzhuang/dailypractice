package com.example.diymysqlms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "camp_new_member")
public class CampNewMember {
    @Id
    private String id;

    /**
     * 营地id
     */
    @Column(name = "camp_id")
    private String campId;

    /**
     * 用户 id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 权限 1：营长 2：助教  3：嘉宾  4：学员
     */
    private Boolean permission;

    /**
     * 是否在营  0：移除 1：在营  2 主动退出
     */
    private Boolean status;

    /**
     * 创建时间（入营时间）
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取营地id
     *
     * @return camp_id - 营地id
     */
    public String getCampId() {
        return campId;
    }

    /**
     * 设置营地id
     *
     * @param campId 营地id
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }

    /**
     * 获取用户 id
     *
     * @return user_id - 用户 id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户 id
     *
     * @param userId 用户 id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户手机号
     *
     * @return phone - 用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户手机号
     *
     * @param phone 用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取权限 1：营长 2：助教  3：嘉宾  4：学员
     *
     * @return permission - 权限 1：营长 2：助教  3：嘉宾  4：学员
     */
    public Boolean getPermission() {
        return permission;
    }

    /**
     * 设置权限 1：营长 2：助教  3：嘉宾  4：学员
     *
     * @param permission 权限 1：营长 2：助教  3：嘉宾  4：学员
     */
    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    /**
     * 获取是否在营  0：移除 1：在营  2 主动退出
     *
     * @return status - 是否在营  0：移除 1：在营  2 主动退出
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置是否在营  0：移除 1：在营  2 主动退出
     *
     * @param status 是否在营  0：移除 1：在营  2 主动退出
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取创建时间（入营时间）
     *
     * @return create_time - 创建时间（入营时间）
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间（入营时间）
     *
     * @param createTime 创建时间（入营时间）
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}