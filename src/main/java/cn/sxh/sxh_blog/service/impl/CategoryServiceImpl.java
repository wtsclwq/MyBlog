package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.ArticleCategoryDAO;
import cn.sxh.sxh_blog.dao.CategoryInfoDAO;
import cn.sxh.sxh_blog.entity.*;
import cn.sxh.sxh_blog.service.CategoryService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sxh
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Resource
    private CategoryInfoDAO categoryInfoDAO;

    @Resource
    private ArticleCategoryDAO articleCategoryDAO;


    /**
     * @Description:先进行按主键更新操作，如果没有更新语句没有返回数值，则表中没有该分类，进行插入操作
     * @Param: [categoryInfo]
     * @return: cn.sxh.sxh_blog.util.PublicresultJson
     */
    @Override
    public PublicResultJson addOrUpdateCategory(CategoryInfo categoryInfo) {
        int flag = categoryInfoDAO.updateByPrimaryKeySelective(categoryInfo);
        if (flag == 0){
            CategoryInfo check = checkCategoryName(categoryInfo.getName());
            if (null != check){
                throw new IllegalArgumentException("分类已存在");
            }
            categoryInfoDAO.insertSelective(categoryInfo);
        }
        return new PublicResultJson(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),null);
    }

    @Override
    public PublicResultJson deleteCategoryById(Long id) {

        //先删除文章分类表中的纪录
        ArticleCategoryCriteria example = new ArticleCategoryCriteria();
        ArticleCategoryCriteria.Criteria criteria = example.or();
        criteria.andCategoryIdEqualTo(id);
        List<ArticleCategory> articleCategoryList = articleCategoryDAO.selectByExample(example);
        //利用Stream提取出原List中的Category属性List
        List<Long> categoryIdList = articleCategoryList.stream().map(ArticleCategory::getCategoryId).collect(Collectors.toList());
        criteria = example.createCriteria();
        criteria.andCategoryIdIn(categoryIdList);
        articleCategoryDAO.deleteByExample(example);

        //再删除分类信息表中的内容
        categoryInfoDAO.deleteByPrimaryKey(id);
        return new PublicResultJson(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),null);
    }

    @Override
    public LayuiTableResult listAllCategory(Integer page, Integer pageSize) {
        LayuiTableResult tableResult = new LayuiTableResult();
        CategoryInfoCriteria example = new CategoryInfoCriteria();
        List<CategoryInfo> list = categoryInfoDAO.selectByExample(example);

        PageHelper.startPage(page,pageSize);
        PageInfo<CategoryInfo> pageInfo = new PageInfo<>(list, pageSize);

        tableResult.setCode(HttpStatus.OK.value());
        tableResult.setMessage(HttpStatus.OK.getReasonPhrase());
        tableResult.setData(pageInfo.getList());
        tableResult.setCount(pageInfo.getTotal());
        return tableResult;
    }

    @Override
    public PublicResultJson getCategoryByArticleId(Long id) {
        return null;
    }

    @Override
    public List<CategoryInfo> allCategory() {
        CategoryInfoCriteria example = new CategoryInfoCriteria();
        return categoryInfoDAO.selectByExample(example);
    }

    /**
     * @Description: 检查该用户是否已存在
     * @Param: [username]
     * @return: cn.sxh.sxh_blog.entity.SysUser
     */
    private CategoryInfo checkCategoryName(String categoryName) {
        CategoryInfoCriteria example = new CategoryInfoCriteria();
        CategoryInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(categoryName);
        List<CategoryInfo> list = categoryInfoDAO.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


}
