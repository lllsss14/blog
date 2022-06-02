package com.jhm.service.Impl;

import com.jhm.NotFindException;
import com.jhm.dao.BlogDao;
import com.jhm.pojo.BlogInfo;
import com.jhm.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
}
