package xyz.sharding.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.BaseMapper;
import xyz.sharding.annotations.GeneralMapper;
import xyz.sharding.pojo.po.UserSharding;

@GeneralMapper
public interface UserShardingMapper extends BaseMapper<UserSharding>{
    
    @Select(value = { "select * from ",UserSharding.TABLE_NAME})
    List<UserSharding> findAllUser();

    /**
     * @param userId
     * @return
     */
    @Select(value = { "select * from ",UserSharding.TABLE_NAME ," where user_id = #{userId}"})
    UserSharding findByUserId(Long userId);

}