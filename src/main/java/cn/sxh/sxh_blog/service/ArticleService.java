package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.dto.ArticleAddDto;
import cn.sxh.sxh_blog.dto.ArticleSelectDto;
import cn.sxh.sxh_blog.entity.ArticleInfo;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;

import java.util.List;

/**
 * @author sxh
 */
public interface ArticleService {

    PublicResultJson add(ArticleAddDto article);

    PublicResultJson delete(Long id);

    PublicResultJson updateByExample(ArticleAddDto article, Boolean flag);

    LayuiTableResult selectByExample(ArticleSelectDto article, Integer page, Integer pageSize);

    PublicResultJson selectByBid(Long id);

    List<ArticleInfo> listAll();

    List<ArticleInfo> listByCategoryId(Long id);

    List<ArticleInfo> listLastest();
}
