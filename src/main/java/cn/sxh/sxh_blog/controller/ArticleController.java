package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.dto.ArticleAddDto;
import cn.sxh.sxh_blog.dto.ArticleSelectDto;
import cn.sxh.sxh_blog.service.ArticleService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sxh
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    @ApiOperation("添加文章")
    @PreAuthorize("hasAuthority('sys:article:add')")
    public PublicResultJson addArticle(@RequestBody ArticleAddDto articleAddDto) {
        PublicResultJson resultJson = articleService.add(articleAddDto);
        return resultJson;
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据ID批量或单个删除文章")
    @PreAuthorize("hasAuthority('sys:article:del')")
    public PublicResultJson deleteArticle(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = articleService.delete(id);
        return resultJson;
    }

    @PutMapping
    @ApiOperation("更新文章")
    @PreAuthorize("hasAuthority('sys:article:update')")
    public PublicResultJson updateArticle(@RequestBody ArticleAddDto articleAddDto) {
        PublicResultJson resultJson = articleService.updateByExample(articleAddDto,true);
        return resultJson;
    }

    @PutMapping("/switch")
    @ApiOperation("switch开关更新文章")
    @PreAuthorize("hasAuthority('sys:article:update')")
    public PublicResultJson updateArticleBySwitch(@RequestBody ArticleAddDto articleAddDto) {
        PublicResultJson resultJson = articleService.updateByExample(articleAddDto,false);
        return resultJson;
    }


    @GetMapping
    @ApiOperation("文章列表条件模糊查询")
    @PreAuthorize("hasAnyAuthority('sys:article:update','sys:article:query')")
    public LayuiTableResult selectByExample(ArticleSelectDto article, @RequestParam(defaultValue = "1")
            Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiTableResult resultJson = articleService.selectByExample(article,page,pageSize);
   //     System.out.println(resultJson);
        return resultJson;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id查找文章信息")
    @PreAuthorize("hasAnyAuthority('sys:article:update','sys:article:query')")
    public PublicResultJson selectByUid(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = articleService.selectByBid(id);
        System.out.println(resultJson);
        return resultJson;
    }


}
