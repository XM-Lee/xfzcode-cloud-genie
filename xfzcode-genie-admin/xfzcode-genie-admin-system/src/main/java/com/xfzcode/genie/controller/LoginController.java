package com.xfzcode.genie.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultMessage;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.dto.LoginModel;
import com.xfzcode.genie.dto.RegisterUser;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.redis.RedisService;
import com.xfzcode.genie.service.UserService;
import com.xfzcode.genie.utils.RandImageUtil;
import com.xfzcode.genie.vo.LoginUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.xfzcode.genie.constant.CommonConstant.BASE_CHECK_CODES;

/**
 * @Author: XMLee
 * @Date: 2023/7/26 18:47
 * @Description: 登录相关API
 */
@RestController
@RequestMapping(ApiVersion.V1_LOGIN)
@Slf4j
@Api(value = "登录相关API", tags = " 2-01.登录相关API-LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/loginByImageCode")
    @ApiOperation("【根据验证码登录】")
    public HttpResult<?> loginByImageCode(@RequestBody LoginModel loginModel) {

        try {
            String captcha = loginModel.getCaptcha();
            if(null ==captcha){
                return HttpResult.failed(ResultMessage.CODE_ERROR);
            }
            String codeInDb = (String) redisService.get(loginModel.getCheckKey());
            if (null == codeInDb) {
                return HttpResult.failed(ResultMessage.CODE_ERROR);
            }
            redisService.del(loginModel.getCheckKey());

            if (!captcha.equalsIgnoreCase(codeInDb)) {
                return HttpResult.failed(ResultMessage.CODE_ERROR);
            }
            // 使用方法
            User account = userService.getOne(new QueryWrapper<User>().eq("user_name", loginModel.getUsername()));
            if (null == account) {
                return HttpResult.failed(ResultMessage.LOGIN_ERROR);
            }
            if (!BCrypt.checkpw(loginModel.getPassword(), account.getPassword())) {
                return HttpResult.failed(ResultMessage.LOGIN_ERROR);
            }
            StpUtil.login(account.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            LoginUserVo vo = new LoginUserVo();
            BeanUtils.copyProperties(account, vo);
            vo.setToken(tokenInfo.tokenValue);
            return HttpResult.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }


    @GetMapping("/getVerCode")
    @ApiOperation("【获取图形验证码】")
    public HttpResult<?> getVerCode() {
        try {
            //生成验证码
            String code = RandomUtil.randomString(BASE_CHECK_CODES,4);
            String realKey = RandomUtil.randomString(32);
            redisService.set(realKey, code, 3 * 60);

            log.info("获取验证码，Redis key = {}，checkCode = {}", realKey, code);
            String base64 = RandImageUtil.generate(code);
            HashMap<String, String> result = new HashMap<>();
            result.put("key", realKey);
            result.put("base64", base64);
            return HttpResult.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @GetMapping("/gePhoneCode")
    @ApiOperation("【获取手机验证码】")
    public HttpResult<?> getVerCode(@RequestParam String phone) {
        try {
            String codeInCache = (String) redisService.get(phone);
            if (null != codeInCache) {
                return HttpResult.failed(ResultMessage.CODE_NOT_EXPIRE);
            }
            redisService.set(phone, "123456", 5 * 60);
            return HttpResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @PostMapping("/register")
    @ApiOperation("【注册用户】")
    public HttpResult<?> register(@RequestBody RegisterUser registerUser) {
        try {
            if (null == registerUser.getPhone()) {
                return HttpResult.failed(ResultMessage.PARAM_MISS);
            }
            if (!registerUser.getPassword().equals(registerUser.getRePassword())) {
                return HttpResult.failed(ResultMessage.PARAM_MISS);
            }
            String codeInCache = (String) redisService.get(registerUser.getPhone());
            if (null == codeInCache) {
                return HttpResult.failed(ResultMessage.CODE_ERROR);
            }
            if (!registerUser.getCode().equals(codeInCache)) {
                return HttpResult.failed(ResultMessage.CODE_ERROR);
            }
            redisService.del(registerUser.getPhone());
            User user = new User();
            user.setUserName(registerUser.getPhone());
            user.setPassword(BCrypt.hashpw(registerUser.getPassword(), BCrypt.gensalt(12)));
            user.setPhone(registerUser.getPhone());
            userService.save(user);
            return HttpResult.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

}
