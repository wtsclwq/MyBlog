package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.entity.CategoryInfo;
import cn.sxh.sxh_blog.service.CategoryService;
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

@Api(tags = "分类管理")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("添加、修改分类")
    @PreAuthorize("hasAuthority('sys:category:addUp')")
    public PublicResultJson addCategory(@RequestBody CategoryInfo categoryInfo) {
        PublicResultJson resultJson = categoryService.addOrUpdateCategory(categoryInfo);
        return resultJson;
    }

    @GetMapping
    @ApiOperation("查询所有分类")
    @PreAuthorize("hasAnyAuthority('sys:category:query','sys:category:addUp','sys:category:del')")
    public LayuiTableResult getAllCategory(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiTableResult resultJson = categoryService.listAllCategory(page, pageSize);
        return resultJson;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除某一分类")
    @PreAuthorize("hasAuthority('sys:category:del')")
    public PublicResultJson deleteCategory(@PathVariable Long id) {
        PublicResultJson resultJson = categoryService.deleteCategoryById(id);
        return resultJson;
    }

}
