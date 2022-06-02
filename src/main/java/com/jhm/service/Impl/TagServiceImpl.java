package com.jhm.service.Impl;

import com.jhm.dao.TagDao;
import com.jhm.pojo.Tag;
import com.jhm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.findOne(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }
    @Override
    public Tag getTagByName(String name) {
        return tagDao.findTagByName(name);
    }
    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }
@Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.delete(id);
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long id1 : longs) {/*遍历list集合里面的id，然后赋值给List<Tag>*/
            tags.add(tagDao.findOne(id1));
        }
        return tags;
    }
    private List<Long> convertToList(String ids) {  /*把前端的tagIds字符串转换为list集合*/
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
