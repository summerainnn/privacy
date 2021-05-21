package summerain.privacy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summerain.privacy.bean.Collection;
import summerain.privacy.mapper.CollectionMapper;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:57 下午
 */
@Service
public class CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    public void insert(Integer userId, String privacy, Integer resultId) {
        Collection collection = new Collection();
        if(privacy.length()>10){
            collection.setPrivacy(privacy.substring(0,10)+"......");
        }else {
            collection.setPrivacy(privacy);
        }
        collection.setResultId(resultId);
        collection.setUserId(userId);
        collectionMapper.insert(collection);
    }

    public boolean isNew(String privacy) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("privacy",privacy);
        if (collectionMapper.selectOne(wrapper) == null){
            return true;
        }
        return false;
    }

    public List<Collection> getAllByUserId(int userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        return collectionMapper.selectList(wrapper);
    }
}
