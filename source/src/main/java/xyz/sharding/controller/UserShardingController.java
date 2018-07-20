package xyz.sharding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.sharding.common.GeneralResponse;
import xyz.sharding.mapper.UserShardingMapper;
import xyz.sharding.pojo.po.UserSharding;

@RestController
@RequestMapping("/userSharding")
public class UserShardingController {

    @Autowired
    private UserShardingMapper userShardingMapper;

    @GetMapping("/findAll")
    public GeneralResponse<List<UserSharding>> findAll() {
        return GeneralResponse.success(userShardingMapper.findAllUser());
    }

    @GetMapping("/findById")
    public GeneralResponse<UserSharding> findById(Long id) {
        return GeneralResponse.success(userShardingMapper.selectByPrimaryKey(id));
    }

    @GetMapping("/findByUserId")
    public GeneralResponse<UserSharding> findByUserId(Long userId) {
        return GeneralResponse.success(userShardingMapper.findByUserId(userId));
    }

    @PostMapping("/save")
    public GeneralResponse<UserSharding> save(UserSharding userSharding) {
        userShardingMapper.insert(userSharding);
        return GeneralResponse.success(userSharding);
    }

    @PostMapping("/updateById")
    public GeneralResponse<UserSharding> updateById(UserSharding userSharding) {
        userShardingMapper.updateByPrimaryKey(userSharding);
        return GeneralResponse.success(userSharding);
    }

}
