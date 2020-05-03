/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.dao;

import cn.sxh.sxh_blog.entity.ArticleContent;
import cn.sxh.sxh_blog.entity.ArticleContentCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 本文件由 https://github.com/orange1438/mybatis-generator-core-chinese-annotation1.3.5-chinese-annotation 自动生成
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
@Repository
@Mapper
public interface ArticleContentDAO extends IMapper<ArticleContent, ArticleContentCriteria, Long> {
}