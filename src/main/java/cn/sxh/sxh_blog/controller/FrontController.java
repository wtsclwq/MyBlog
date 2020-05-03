package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.dto.ArticleSelectDto;
import cn.sxh.sxh_blog.entity.ArticleInfo;
import cn.sxh.sxh_blog.entity.CategoryInfo;
import cn.sxh.sxh_blog.service.ArticleService;
import cn.sxh.sxh_blog.service.CategoryService;
import cn.sxh.sxh_blog.util.Markdown2HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sxh
 */
@RestController
@RequestMapping("/api")
public class FrontController {
    @Resource
    ArticleService articleService;
    @Resource
    CategoryService categoryService;


    @GetMapping("/article/list")
    @ApiOperation("获取所有文章")
    public List<ArticleInfo> listAllArticleInfo() {
        return articleService.listAll();
    }

    @GetMapping("/article/list/sort/{id}")
    @ApiOperation("获取某一分类下的所有文章")
    public List<ArticleInfo> listArticleInfo(@PathVariable Long id) {
        return articleService.listByCategoryId(id);
    }

    @ApiOperation("获取最新的几篇文章")
    @GetMapping("article/list/lastest")
    public List<ArticleInfo> listLastestArticle() {
        return articleService.listLastest();
    }
    @ApiOperation("通过文章ID获取文章信息")
    @GetMapping("article/{id}")
    public ArticleSelectDto getArticleById(@PathVariable Long id) {
        ArticleSelectDto articleDto = (ArticleSelectDto) articleService.selectByBid(id).getData();
        articleDto.setContent(Markdown2HtmlUtil.markdown2html(articleDto.getContent()));
        return articleDto;
    }
    @ApiOperation("获取所有分类信息")
    @GetMapping("category/list")
    public List<CategoryInfo> listAllCategoryInfo() {
        return categoryService.allCategory();
    }


}
