/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统（后台）用户表 sys_user
 *
 * @author orange1438 code generator
 * date:2020/04/24 10:53
 */
public class SysUser implements Serializable {
    /**
     * 串行版本ID
     */
    private static final long serialVersionUID = 8390705267735021677L;

    /**
     *
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像图片地址
     */
    private String headimgurl;

    /**
     * 状态：1可用 ，0不可用  默认：1
     */
    private Integer status;

    /**
     * 创建时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /**
     * 更新时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedBy;

    /**
     * 获取 sys_user.id
     *
     * @return sys_user.id
     */
    public final Long getId() {
        return id;
    }

    /**
     * 设置 sys_user.id
     *
     * @param id sys_user.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户名 sys_user.username
     *
     * @return 用户名
     */
    public final String getUsername() {
        return username;
    }

    /**
     * 设置 用户名 sys_user.username
     *
     * @param username 用户名
     */
    public final void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取 加密密码 sys_user.password
     *
     * @return 加密密码
     */
    public final String getPassword() {
        return password;
    }

    /**
     * 设置 加密密码 sys_user.password
     *
     * @param password 加密密码
     */
    public final void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取 用户昵称 sys_user.nickname
     *
     * @return 用户昵称
     */
    public final String getNickname() {
        return nickname;
    }

    /**
     * 设置 用户昵称 sys_user.nickname
     *
     * @param nickname 用户昵称
     */
    public final void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取 电话号码 sys_user.phone
     *
     * @return 电话号码
     */
    public final String getPhone() {
        return phone;
    }

    /**
     * 设置 电话号码 sys_user.phone
     *
     * @param phone 电话号码
     */
    public final void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取 邮箱地址 sys_user.email
     *
     * @return 邮箱地址
     */
    public final String getEmail() {
        return email;
    }

    /**
     * 设置 邮箱地址 sys_user.email
     *
     * @param email 邮箱地址
     */
    public final void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 生日 sys_user.birthday
     *
     * @return 生日
     */
    public final Date getBirthday() {
        return birthday;
    }

    /**
     * 设置 生日 sys_user.birthday
     *
     * @param birthday 生日
     */
    public final void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取 性别 sys_user.sex
     *
     * @return 性别
     */
    public final Integer getSex() {
        return sex;
    }

    /**
     * 设置 性别 sys_user.sex
     *
     * @param sex 性别
     */
    public final void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取 头像图片地址 sys_user.headimgurl
     *
     * @return 头像图片地址
     */
    public final String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置 头像图片地址 sys_user.headimgurl
     *
     * @param headimgurl 头像图片地址
     */
    public final void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    /**
     * 获取 状态：1可用 ，0不可用 sys_user.status
     *
     * @return 状态：1可用 ，0不可用
     */
    public final Integer getStatus() {
        return status;
    }

    /**
     * 设置 状态：1可用 ，0不可用 sys_user.status
     *
     * @param status 状态：1可用 ，0不可用
     */
    public final void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 创建时间 sys_user.create_by
     *
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /**
     * 设置 创建时间 sys_user.create_by
     *
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取 更新时间 sys_user.modified_by
     *
     * @return 更新时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置 更新时间 sys_user.modified_by
     *
     * @param modifiedBy 更新时间
     */
    public final void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", headimgurl=").append(headimgurl);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}