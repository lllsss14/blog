package com.jhm.service.Impl;

import com.jhm.NotFindException;
import com.jhm.dao.BlogDao;
import com.jhm.pojo.BlogInfo;
import com.jhm.service.BlogService;
import com.jhm.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Override
    public BlogInfo getBlog(Long id) {
       return blogDao.findById(id);

    }
    /*无条件查所有博客*/
    @Override
    public List<BlogInfo> getAllBlog() {
        return blogDao.getAll();
    }

    @Override
    public List<BlogInfo> findAllBlog(BlogInfo blogInfo) {

        return blogDao.findAll(blogInfo);
    }
    /*新增博客*/
    @Transactional
    @Override
    public Integer saveBlog(BlogInfo blogInfo) {
        /*新增博客的时候id是没有设置的，数据库id会自增，加个if可以判断是不是新增一个博客*/
            blogInfo.setCreate_time((new Timestamp(new Date().getTime())));
            blogInfo.setUpdate_time(new Timestamp(new Date().getTime()));
            blogInfo.setViews(0);
        return blogDao.saveBlog(blogInfo);

    }
    /*前台获得博客详情*/
    @Override
    public BlogInfo getAndConvert(Long id) {
        BlogInfo blogInfo=blogDao.getDetailedBlog(id);
        String content=blogInfo.getContent();
        /*编辑插件做一次转换，将Markdown格式转换成html*/
        blogInfo.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blogInfo;
    }

    @Override
    public List<BlogInfo> getAllRecommendBlog() {
        return blogDao.findAllRecommendBlog();
    }

    /*更新博客*/
    @Transactional
    @Override
    public Integer updateBlog(BlogInfo blogInfo) {
        blogInfo.setUpdate_time(new Timestamp(new Date().getTime()));
        BlogInfo b=blogDao.findById(blogInfo.getId());
        if(b==null) {
            throw new NotFindException("你想更新不存在的博客？");
        }
        /*把blogInfo赋值给b*/
        BeanUtils.copyProperties(blogInfo,b);
        return blogDao.updateBlog(b);
    }

    @Transactional
    /*删除博客*/
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogInfo> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public BlogInfo findBlogWithTag(Long id) {
        System.out.println(blogDao.findBlogWithTag(id).getTags());
        return blogDao.findBlogWithTag(id);
    }

    @Override
    public List<BlogInfo> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public Map<String, List<BlogInfo>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);  /*用set去掉重复的年份*/
        Map<String, List<BlogInfo>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getAll().size();
    }


}
