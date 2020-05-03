package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.*;
import cn.sxh.sxh_blog.dto.ArticleAddDto;
import cn.sxh.sxh_blog.dto.ArticleSelectDto;
import cn.sxh.sxh_blog.entity.*;
import cn.sxh.sxh_blog.service.ArticleService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sxh
 */

@Service
public class ArticleServiceImpl implements ArticleService {


    @Resource
    private ArticleInfoDAO articleInfoDAO;

    @Resource
    private ArticleCategoryDAO articleCategoryDAO;

    @Resource
    private ArticleContentDAO articleContentDAO;

    @Resource
    private CategoryInfoDAO categoryInfoDAO;

    @Resource
    private ArticleCommentDAO articleCommentDAO;

    private static byte MAX_LASTEST_ARTICLE_COUNT = 5;


    @Override
    public PublicResultJson add(ArticleAddDto article) {

        //文章基本信息插入
        ArticleInfo articleInfo = new ArticleInfo();
//        articleInfo.setTitle(article.getTitle());
//        articleInfo.setSummary(article.getSummary());
//        articleInfo.setIsTop(article.getIsTop());
//        articleInfo.setPictureUrl(article.getPictureUrl());

        //利用BeanUtils复制属性
        BeanUtils.copyProperties(article, articleInfo);

        articleInfoDAO.insertSelective(article);

        //获取最新插入的文章的id
        Long articleId = getArticleLastestId();

        //文章内容插入
        ArticleContent articleContent = new ArticleContent();
        articleContent.setContent(article.getContent());
        articleContent.setArticleId(articleId);
        articleContentDAO.insertSelective(articleContent);

        //文章分类信息插入
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleId);
        articleCategory.setCategoryId(article.getCategoryId());
        articleCategoryDAO.insertSelective(articleCategory);


        //分类下的文章数量+1
        CategoryInfo categoryInfo = categoryInfoDAO.selectByPrimaryKey(articleCategory.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
        categoryInfoDAO.updateByPrimaryKeySelective(categoryInfo);

        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public PublicResultJson delete(Long id) {

        // 获取对应的文章信息
        ArticleSelectDto articleDto = (ArticleSelectDto) selectByBid(id).getData();
        // 删除文章信息中的数据
        articleInfoDAO.deleteByPrimaryKey(articleDto.getId());
        // 删除文章内容信息表
        articleContentDAO.deleteByPrimaryKey(articleDto.getArticleContentId());
        // 删除文章分类信息表
        articleCategoryDAO.deleteByPrimaryKey(articleDto.getArticleCategoryId());
        //分类下的文章数量-1
        CategoryInfo categoryInfo = categoryInfoDAO.selectByPrimaryKey(articleDto.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() - 1));
        categoryInfoDAO.updateByPrimaryKeySelective(categoryInfo);

        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public PublicResultJson updateByExample(ArticleAddDto article, Boolean flag) {
        //根据文章主键更新信息
        articleInfoDAO.updateByPrimaryKeySelective(article);
        //是否更新文章全部信息
        if (flag) {
            //更新之前的分类下，文章数量-1
            ArticleCategoryCriteria example = new ArticleCategoryCriteria();
            ArticleCategoryCriteria.Criteria criteria = example.or();
            criteria.andArticleIdEqualTo(article.getId());
            ArticleCategory articleCategory = articleCategoryDAO.selectByExample(example).get(0);
            CategoryInfo categoryInfo = categoryInfoDAO.selectByPrimaryKey(articleCategory.getCategoryId());
            categoryInfo.setNumber((byte) (categoryInfo.getNumber() - 1));
            categoryInfoDAO.updateByPrimaryKeySelective(categoryInfo);
            // 添加分类
            this.saveCategory(article.getId(), article.getCategoryId());
            //添加内容
            this.saveContent(article.getId(), article.getContent());
            //更新之后的分类下，文章数量+1
            categoryInfo = categoryInfoDAO.selectByPrimaryKey(article.getCategoryId());
            categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
            categoryInfoDAO.updateByPrimaryKeySelective(categoryInfo);
        }
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);

    }

    @Override
    public LayuiTableResult selectByExample(ArticleSelectDto article, Integer page, Integer pageSize) {

        LayuiTableResult tableResult = new LayuiTableResult();
        List<Long> resultArticleIds = null;
        if (null != article.getCategoryId()) {
            ArticleCategoryCriteria example1 = new ArticleCategoryCriteria();
            ArticleCategoryCriteria.Criteria criteria1 = example1.createCriteria();
            System.out.println(article);
            criteria1.andCategoryIdEqualTo(article.getCategoryId());
            List<ArticleCategory> articleIds = articleCategoryDAO.selectByExample(example1);
            //利用Java8特性：流  取出文章分类对象中的文章id集合
            resultArticleIds = articleIds.stream().map(ArticleCategory::getArticleId).collect(Collectors.toList());
        } else {
            resultArticleIds = articleInfoDAO.selectId();
        }


        ArticleInfoCriteria example2 = new ArticleInfoCriteria();
        example2.setOrderByClause("create_by DESC");
        ArticleInfoCriteria.Criteria criteria = example2.createCriteria();
        criteria.andIdIn(resultArticleIds);

        if (StringUtils.isNotBlank(article.getTitle())) {
            criteria.andTitleLike("%" + article.getTitle() + "%");
        }

        PageHelper.startPage(page, pageSize);
        List<ArticleInfo> articleInfoList = articleInfoDAO.selectByExample(example2);
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleInfoList, pageSize);
        tableResult.setCode(HttpStatus.OK.value());
        tableResult.setMessage(HttpStatus.OK.getReasonPhrase());
        tableResult.setData(pageInfo.getList());
        tableResult.setCount(pageInfo.getTotal());
        return tableResult;
    }

    @Override
    public PublicResultJson selectByBid(Long id) {
        ArticleSelectDto articleDto = new ArticleSelectDto();
        // 填充文章基础信息
        ArticleInfo articleInfo = articleInfoDAO.selectByPrimaryKey(id);
//        articleDto.setId(articleInfo.getId());
//        articleDto.setTitle(articleInfo.getTitle());
//        articleDto.setSummary(articleInfo.getSummary());
//        articleDto.setPictureUrl(articleInfo.getPictureUrl());
//        articleDto.setIsTop(articleInfo.getIsTop());
//        articleDto.setCreateBy(articleInfo.getCreateBy());

        //利用BeanUtils复制属性
        BeanUtils.copyProperties(articleInfo, articleDto);

        // 文章访问量要加1
        articleInfo.setTraffic(articleInfo.getTraffic() + 1);
        articleDto.setTraffic(articleInfo.getTraffic() + 1);
        articleInfoDAO.updateByPrimaryKey(articleInfo);
        // 填充文章内容信息
        ArticleContentCriteria example = new ArticleContentCriteria();
        example.or().andArticleIdEqualTo(id);
        ArticleContent articleContent = articleContentDAO.selectByExample(example).get(0);
        articleDto.setContent(articleContent.getContent());
        articleDto.setArticleContentId(articleContent.getId());

        // 填充文章分类信息
        ArticleCategoryCriteria example2 = new ArticleCategoryCriteria();
        example2.or().andArticleIdEqualTo(id);
        ArticleCategory articleCategory = articleCategoryDAO.selectByExample(example2).get(0);
        articleDto.setArticleCategoryId(articleCategory.getId());
        // 填充文章分类基础信息
        CategoryInfoCriteria example3 = new CategoryInfoCriteria();
        example3.or().andIdEqualTo(articleCategory.getCategoryId());
        CategoryInfo categoryInfo = categoryInfoDAO.selectByExample(example3).get(0);
        articleDto.setCategoryId(categoryInfo.getId());
        articleDto.setCategoryName(categoryInfo.getName());
        articleDto.setCategoryNumber(categoryInfo.getNumber());
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), articleDto);
    }

    @Override
    public List<ArticleInfo> listAll() {
        ArticleInfoCriteria example = new ArticleInfoCriteria();
        List<ArticleInfo> articleInfoList = articleInfoDAO.selectByExample(example);
        // 2.然后再对集合进行重排，置顶的文章在前
        LinkedList<ArticleInfo> list = new LinkedList<>();
        for (ArticleInfo articleInfo : articleInfoList) {
            if (articleInfo.getIsTop()) {
                list.addFirst(articleInfo);
            } else {
                list.addLast(articleInfo);
            }
        }
        articleInfoList = new ArrayList<>(list);
        return articleInfoList;
    }

    @Override
    public List<ArticleInfo> listByCategoryId(Long id) {
        ArticleCategoryCriteria example1 = new ArticleCategoryCriteria();
        example1.or().andCategoryIdEqualTo(id);
        List<ArticleCategory> articleCategories = articleCategoryDAO.selectByExample(example1);

        //获取文章id集合
        List<Long> articleIds = articleCategories.stream().map(ArticleCategory::getArticleId).collect(Collectors.toList());

        ArticleInfoCriteria example2 = new ArticleInfoCriteria();
        example2.or().andIdIn(articleIds);
        List<ArticleInfo> articleInfos = articleInfoDAO.selectByExample(example2);

        // 对集合进行重排，置顶的文章在前
        LinkedList<ArticleInfo> list = new LinkedList<>();
        for (int i = 0; i < articleInfos.size(); i++) {
            if (articleInfos.get(i).getIsTop()) {
                list.add(articleInfos.get(i));
            } else {
                list.addLast(articleInfos.get(i));
            }
        }
        articleInfos = new ArrayList<>(list);
        return articleInfos;
    }

    @Override
    public List<ArticleInfo> listLastest() {
        // 1.先获取所有的数据
        List<ArticleInfo> articles = listAll();
        // 2.判断是否满足5个的条件
        if (articles.size() >= MAX_LASTEST_ARTICLE_COUNT) {
            // 3.大于5个则返回前五个数据
            List<ArticleInfo> tempArticles = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tempArticles.add(articles.get(i));
            }
            return tempArticles;
        }
        // 4.不大于五个则直接返回
        return articles;
    }

    /**
     * 返回最新插入一条数据的ID
     *
     * @return
     */
    private Long getArticleLastestId() {
        ArticleInfoCriteria example = new ArticleInfoCriteria();
        example.setOrderByClause("id desc");
        return articleInfoDAO.selectByExample(example).get(0).getId();
    }


    /**
     * @Description:删除文章分类纪录
     * @Param: [bids]
     * @return: void
     */
    private void deleteCategory(List<Long> bids) {
        ArticleCategoryCriteria example = new ArticleCategoryCriteria();
        ArticleCategoryCriteria.Criteria criteria = example.createCriteria();
        criteria.andArticleIdIn(bids);
        articleCategoryDAO.deleteByExample(example);
    }

    /**
     * @param: [bids]
     * @return: void
     * @description:删除文章评论纪录
     */
    private void deleteComments(List<Long> bids) {
        ArticleCommentCriteria example = new ArticleCommentCriteria();
        ArticleCommentCriteria.Criteria criteria = example.createCriteria();
        criteria.andArticleIdIn(bids);
        articleCommentDAO.deleteByExample(example);
    }

    /**
     * @param: [bids]
     * @return: void
     * @description:删除文章内容纪录
     */
    private void deleteContent(List<Long> bids) {
        ArticleContentCriteria example = new ArticleContentCriteria();
        ArticleContentCriteria.Criteria criteria = example.createCriteria();
        criteria.andArticleIdIn(bids);
        articleContentDAO.deleteByExample(example);
    }

    /**
     * @Description:更新文章分类纪录
     * @Param: [id, categoryId]
     * @return: void
     */
    private void saveCategory(Long id, Long categoryId) {
        ArticleCategoryCriteria example = new ArticleCategoryCriteria();
        ArticleCategoryCriteria.Criteria criteria = example.or();
        criteria.andArticleIdEqualTo(id);
        ArticleCategory articleCategory = articleCategoryDAO.selectByExample(example).get(0);
        articleCategory.setCategoryId(categoryId);
        articleCategoryDAO.updateByPrimaryKeySelective(articleCategory);
    }


    /**
     * @Description:更新文章内容纪录
     * @Param: [id, content]
     * @return: void
     */
    private void saveContent(Long id, String content) {
        ArticleContentCriteria example = new ArticleContentCriteria();
        ArticleContentCriteria.Criteria criteria = example.or();
        criteria.andArticleIdEqualTo(id);
        ArticleContent articleContent = articleContentDAO.selectByExample(example).get(0);
        articleContent.setContent(content);
        articleContentDAO.updateByPrimaryKeySelective(articleContent);
    }

}
