package summerain.privacy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:17 下午
 */
@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String account;
    private String pwd;
    private String name;
    private Boolean category;
    private String email;
}
