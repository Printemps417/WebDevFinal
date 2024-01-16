package com.example.todoSpringboot.controller;

import com.example.todoSpringboot.entity.Account;
import com.example.todoSpringboot.entity.Student;
import com.example.todoSpringboot.note.AuthToken;
import com.example.todoSpringboot.service.AccountService;
import com.example.todoSpringboot.service.StudentService;
import com.example.todoSpringboot.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final StudentService studentService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.refresh_token.expiration}")
    private Long refreshTokenExpiration;
    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody LoginRequest loginUser) {

        try{
            Account account = accountService.getAccountByAccount(loginUser.getUsername());

            if (!loginUser.getPassword().equals(account.getPassword())) {
                return CommonResult.error(50007,"登录失败，账号密码不正确");
            }
        }catch (Exception e){
            return CommonResult.error(50007,"登录失败，账号密码不正确");
        }

        String account = loginUser.getUsername();
        int stu_id =accountService.getStudentIdByAccount(account);
        String type=accountService.getAccountByAccount(account).getType();
        if(type.equals(loginUser.getType())){
            // 生成访问令牌和刷新令牌
            String accessToken = jwtTokenUtil.generateAccessToken(String.valueOf(stu_id));
            String refreshToken = jwtTokenUtil.generateRefreshToken(String.valueOf(stu_id));
            ThreadLocalParaments.setStuId(""+stu_id);
            System.out.println("id被设置为："+ThreadLocalParaments.getStuId());
            TokenResponse token_resp = new TokenResponse(accessToken,refreshToken);

            CommonResult<TokenResponse> result = CommonResult.success(token_resp);

            return result;
        }
        else{
            return CommonResult.error(50007,"登录失败，用户类型不匹配！");
        }
    }
    @GetMapping("/reloadtoken")
    public CommonResult<?> reloadToken(@RequestHeader("REFRESHTOKEN") String refreshtoken){
        System.out.println("查询刷新条件……，refreshToken："+refreshtoken);
        refreshtoken=refreshtoken.substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(refreshtoken);
        if(username.length()>0&&redisTemplate.hasKey(refreshtoken)){
            // 如果token合法，删除旧的访问令牌，生成新的访问令牌，重置刷新令牌的时间
            System.out.println("根据refresh_token刷新access_token");
            String accessToken = jwtTokenUtil.generateAccessToken(username);
            redisTemplate.expire(refreshtoken, refreshTokenExpiration, TimeUnit.SECONDS);
            TokenResponse token_resp = new TokenResponse(accessToken,refreshtoken);

            CommonResult<TokenResponse> result = CommonResult.success(token_resp);
            return result;
        }
        else{
//            通过jwtTokenUtil判断token是否有效，进而判断能否进行刷新
            return CommonResult.error(401,"刷新失败");
        }
    }
    @AuthToken
    @GetMapping("/getinfo")
    public CommonResult<?> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        try{
            System.out.println("id被查询："+ThreadLocalParaments.getStuId());
            int stu_id = Integer.parseInt(ThreadLocalParaments.getStuId());
            Student res=studentService.getStudentById(stu_id);
            CommonResult<Student> result = CommonResult.success(res);
            return result;
        } catch (Exception e){
            return CommonResult.error(401,"查询失败");
        }
    }
    @GetMapping("/{account}")
    public Account getAccountByAccount(@PathVariable String account) {
        return accountService.getAccountByAccount(account);
    }

    @PostMapping("/")
    public CommonResult<?> addAccount(@RequestBody Account account) {
        try {
            accountService.addAccount(account);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"添加失败");
        }
    }

    @PutMapping("/")
    public CommonResult<?> updateAccount(@RequestBody Account account) {
        try {
            accountService.updateAccount(account);
            return CommonResult.success("更新成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"更新失败");
        }
    }

    @DeleteMapping("/{account}")
    public CommonResult<?> deleteAccount(@PathVariable String account) {
        try {
            accountService.deleteAccount(account);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"删除失败");
        }
    }
    @DeleteMapping("/deleteAccountById/{id}")
    public CommonResult<?> deleteAccountById(@PathVariable int id) {
        try {
            accountService.deleteAccountById(id);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"删除失败");
        }
    }
//    public void deleteAccountById(@PathVariable int id) {
//        accountService.deleteAccountById(id);
//    }
}