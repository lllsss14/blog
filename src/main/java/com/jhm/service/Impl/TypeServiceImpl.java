package com.jhm.service.Impl;

import com.jhm.NotFindException;
import com.jhm.dao.TypeDao;
import com.jhm.pojo.Type;
import com.jhm.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    /*新增类型*/
    @Override
    @Transactional
    public int saveType(Type type) {
        return typeDao.save(type);
    }

    /*获得一条Type数据*/
    @Override
    public Type getType(Long id) {
        return typeDao.findOne(id);
    }

    /*获得type列表*/
    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> getBlogType() {

        return typeDao.getBlogType();
    }


    /*通过名字获得type*/
    @Override
    public Type getTypeByName(String name) {
        return typeDao.findTypeByName(name);
    }

    @Override
    @Transactional
    public int updateType(Type type) {

        Type type1=typeDao.findOne(type.getId());
        if(type1==null){
            /*如果没这个id那说明数据库没数据，就不存在这个类别*/
            throw new NotFindException("不存在该类型");
        }
        /*把type赋值到type1*/
        BeanUtils.copyProperties(type,type1);
        return typeDao.updateType(type1);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeDao.delete(id);
    }
}
