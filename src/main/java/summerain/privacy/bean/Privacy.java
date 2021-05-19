package summerain.privacy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 8:16 下午
 */
@Data
public class Privacy {
    @TableId(type = IdType.AUTO)
    private int id;
    private String content;
}
