/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysLog implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -8945894180920899681L;

    /** 
     * 主键
     */ 
    private Long id;

    /** 
     * 操作地址的IP
     */ 
    private String ip;

    /** 
     * 操作时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 操作内容
     */ 
    private String remark;

    /** 
     * 操作的访问地址
     */ 
    private String operateUrl;

    /** 
     * 操作的浏览器
     */ 
    private String operateBy;

    /** 
     * 获取 主键 sys_log.id
     * @return 主键
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 主键 sys_log.id
     * @param id 主键
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 操作地址的IP sys_log.ip
     * @return 操作地址的IP
     */
    public final String getIp() {
        return ip;
    }

    /** 
     * 设置 操作地址的IP sys_log.ip
     * @param ip 操作地址的IP
     */
    public final void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /** 
     * 获取 操作时间 sys_log.create_by
     * @return 操作时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 操作时间 sys_log.create_by
     * @param createBy 操作时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 操作内容 sys_log.remark
     * @return 操作内容
     */
    public final String getRemark() {
        return remark;
    }

    /** 
     * 设置 操作内容 sys_log.remark
     * @param remark 操作内容
     */
    public final void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /** 
     * 获取 操作的访问地址 sys_log.operate_url
     * @return 操作的访问地址
     */
    public final String getOperateUrl() {
        return operateUrl;
    }

    /** 
     * 设置 操作的访问地址 sys_log.operate_url
     * @param operateUrl 操作的访问地址
     */
    public final void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
    }

    /** 
     * 获取 操作的浏览器 sys_log.operate_by
     * @return 操作的浏览器
     */
    public final String getOperateBy() {
        return operateBy;
    }

    /** 
     * 设置 操作的浏览器 sys_log.operate_by
     * @param operateBy 操作的浏览器
     */
    public final void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
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
        sb.append(", remark=").append(remark);
        sb.append(", operateUrl=").append(operateUrl);
        sb.append(", operateBy=").append(operateBy);
        sb.append("]");
        return sb.toString();
    }
}