package summerain.privacy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summerain.privacy.bean.User;
import summerain.privacy.mapper.UserMapper;

import java.util.List;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:58 下午
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserByAccount(String account){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        User user = userMapper.selectOne(wrapper);
        if(user == null){
            return null;
        }else {
            return user;
        }
    }

    public User getByUserId(Integer userId) {
        return userMapper.selectById(userId);
    }

    public User getAccountByAccount(String account){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        User account1 = userMapper.selectOne(wrapper);
        if(account1 == null){
            return null;
        }else {
            return account1;
        }
    }

    public Boolean accountIsDifferent(String accountAccount){
        if(getAccountByAccount(accountAccount)==null){
            return true;
        }else{
            return false;
        }
    }


    public void insert(String account, String password1, String username, String email) {
        User user = new User();
        user.setAccount(account);
        user.setEmail(email);
        user.setName(username);
        user.setPwd(password1);
        userMapper.insert(user);
    }

    public List<User> getAllUser() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("category",1);
        return userMapper.selectList(wrapper);
    }

    public void delete(Integer userId) {
        userMapper.deleteById(userId);
    }
}
