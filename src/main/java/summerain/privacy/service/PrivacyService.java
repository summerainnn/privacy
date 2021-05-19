package summerain.privacy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summerain.privacy.bean.Privacy;
import summerain.privacy.mapper.PrivacyMapper;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:57 下午
 */
@Service
public class PrivacyService {
    @Autowired
    PrivacyMapper privacyMapper;

    public Integer insert(String privacy){
        Privacy privacy1 = new Privacy();
        privacy1.setContent(privacy);
        privacyMapper.insert(privacy1);
        return privacy1.getId();
    }

    public Integer getIdByContent(String content) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("content",content);
        Privacy privacy =privacyMapper.selectOne(wrapper);
        if(privacy!=null){
            return privacy.getId();
        }else {
            return 0;
        }

    }
}
