package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.dto.ArticleCommentDto;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;

/**
 * @author sxh
 */
public interface CommentService {

    PublicResultJson add(ArticleCommentDto comment);

    PublicResultJson deleteCommentById(Long id);

    PublicResultJson deleteArticleCommentById(Long id);

    LayuiTableResult selectByArticleId(Long articleId);

    LayuiTableResult selectAll();

}
