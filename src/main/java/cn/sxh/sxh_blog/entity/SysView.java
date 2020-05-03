/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysView implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -1760216985078844194L;

    /** 
     */ 
    private Long id;

    /** 
     * 访问IP
     */ 
    private String ip;

    /** 
     * 访问时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 获取 sys_view.id
     * @return sys_view.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 sys_view.id
     * @param id sys_view.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 访问IP sys_view.ip
     * @return 访问IP
     */
    public final String getIp() {
        return ip;
    }

    /** 
     * 设置 访问IP sys_view.ip
     * @param ip 访问IP
     */
    public final void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /** 
     * 获取 访问时间 sys_view.create_by
     * @return 访问时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 访问时间 sys_view.create_by
     * @param createBy 访问时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }
}