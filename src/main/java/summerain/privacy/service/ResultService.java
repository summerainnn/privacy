package summerain.privacy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summerain.privacy.bean.Result;
import summerain.privacy.mapper.ResultMapper;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:58 下午
 */
@Service
public class ResultService {
    @Autowired
    ResultMapper resultMapper;

    public Integer insert(String line,Integer privacyId) {
        Result result = new Result();
        result.setResult(line);
        result.setPrivacyId(privacyId);
        resultMapper.insert(result);
        return result.getId();
    }

    public String getById(Integer id) {
        return resultMapper.selectById(id).getResult();
    }

    public Integer getByPrivacyId(Integer privacyId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("privacy_id",privacyId);
        Result result = resultMapper.selectOne(wrapper);
        return result.getId();
    }
}
