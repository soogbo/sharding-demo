package xyz.sharding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.sharding.common.GeneralResponse;
import xyz.sharding.mapper.UserShardingMapper;
import xyz.sharding.pojo.po.UserSharding;

@RequestMapping("/userSharding")
@RestController
public class UserShardingController {

    @Autowired
    private UserShardingMapper userShardingMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public GeneralResponse<List<UserSharding>> findAll() {
        return GeneralResponse.success(userShardingMapper.findAllUser());
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public GeneralResponse<UserSharding> findById(Long id) {
        return GeneralResponse.success(userShardingMapper.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public GeneralResponse<UserSharding> findByUserId(Long userId) {
        return GeneralResponse.success(userShardingMapper.findByUserId(userId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public GeneralResponse<UserSharding> save(UserSharding userSharding) {
        userShardingMapper.insert(userSharding);
        return GeneralResponse.success(userSharding);
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public GeneralResponse<UserSharding> updateById(UserSharding userSharding) {
        userShardingMapper.updateByPrimaryKey(userSharding);
        return GeneralResponse.success(userSharding);
    }

}
