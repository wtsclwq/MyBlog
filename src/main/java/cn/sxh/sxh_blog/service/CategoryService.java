package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.entity.CategoryInfo;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;

import java.util.List;

/**
 * @author sxh
 */
public interface CategoryService {


    PublicResultJson addOrUpdateCategory(CategoryInfo categoryInfo);

    PublicResultJson deleteCategoryById(Long id);

    LayuiTableResult listAllCategory(Integer page, Integer pageSize);

    PublicResultJson getCategoryByArticleId(Long id);

    List<CategoryInfo> allCategory();
}
