/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * 因为message分为两种，一种是留言，一种是评论，这里搞成一张表是因为它们几乎是拥有相同的字段，我觉得没必要分成两张表来进行维护 tbl_comment
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class Comment implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -1483479426620546931L;

    /** 
     * 主键
     */ 
    private Long id;

    /** 
     * 留言/评论内容
     */ 
    private String content;

    /** 
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 邮箱，用于回复消息
     */ 
    private String email;

    /** 
     * 用户自己定义的名称
     */ 
    private String name;

    /** 
     * 留言/评论IP
     */ 
    private String ip;

    /** 
     * 是否有效，默认为1为有效，0为无效  默认：1
     */ 
    private Boolean isEffective;

    /** 
     * 获取 主键 tbl_comment.id
     * @return 主键
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 主键 tbl_comment.id
     * @param id 主键
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 留言/评论内容 tbl_comment.content
     * @return 留言/评论内容
     */
    public final String getContent() {
        return content;
    }

    /** 
     * 设置 留言/评论内容 tbl_comment.content
     * @param content 留言/评论内容
     */
    public final void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /** 
     * 获取 创建日期 tbl_comment.create_by
     * @return 创建日期
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建日期 tbl_comment.create_by
     * @param createBy 创建日期
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 邮箱，用于回复消息 tbl_comment.email
     * @return 邮箱，用于回复消息
     */
    public final String getEmail() {
        return email;
    }

    /** 
     * 设置 邮箱，用于回复消息 tbl_comment.email
     * @param email 邮箱，用于回复消息
     */
    public final void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /** 
     * 获取 用户自己定义的名称 tbl_comment.name
     * @return 用户自己定义的名称
     */
    public final String getName() {
        return name;
    }

    /** 
     * 设置 用户自己定义的名称 tbl_comment.name
     * @param name 用户自己定义的名称
     */
    public final void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 留言/评论IP tbl_comment.ip
     * @return 留言/评论IP
     */
    public final String getIp() {
        return ip;
    }

    /** 
     * 设置 留言/评论IP tbl_comment.ip
     * @param ip 留言/评论IP
     */
    public final void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /** 
     * 获取 是否有效，默认为1为有效，0为无效 tbl_comment.is_effective
     * @return 是否有效，默认为1为有效，0为无效
     */
    public final Boolean getIsEffective() {
        return isEffective;
    }

    /** 
     * 设置 是否有效，默认为1为有效，0为无效 tbl_comment.is_effective
     * @param isEffective 是否有效，默认为1为有效，0为无效
     */
    public final void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", createBy=").append(createBy);
        sb.append(", email=").append(email);
        sb.append(", name=").append(name);
        sb.append(", ip=").append(ip);
        sb.append(", isEffective=").append(isEffective);
        sb.append("]");
        return sb.toString();
    }
}