/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.dao;

import cn.sxh.sxh_blog.entity.ArticleInfo;
import cn.sxh.sxh_blog.entity.ArticleInfoCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 本文件由 https://github.com/orange1438/mybatis-generator-core-chinese-annotation1.3.5-chinese-annotation 自动生成
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
@Repository
@Mapper
public interface ArticleInfoDAO extends IMapper<ArticleInfo, ArticleInfoCriteria, Long> {
    List<Long> selectId();
}