package com.example.todoSpringboot.mapper;

import com.example.todoSpringboot.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO accounts(account, password, student_id, type) VALUES(#{account}, #{password}, #{studentId}, #{type})")
    void addAccount(@Param("account") String account, @Param("password") String password, @Param("studentId") Integer studentId, @Param("type") String type);

    @Delete("DELETE FROM accounts WHERE account = #{account}")
    void deleteAccount(@Param("account") String account);
    @Delete("DELETE FROM accounts WHERE student_id = #{id}")
    void deleteAccountById(@Param("id") Integer id);

    @Update("UPDATE accounts SET password = #{password}, student_id = #{studentId}, type = #{type} WHERE account = #{account}")
    void updateAccount(@Param("account") String account, @Param("password") String password, @Param("studentId") Integer studentId, @Param("type") String type);

    @Select("SELECT * FROM accounts WHERE account = #{account}")
    Account getAccountByAccount(@Param("account") String account);

    @Select("SELECT student_id FROM accounts WHERE account = #{account}")
    Integer getStudentIdByAccount(@Param("account") String account);

    @Select("SELECT * FROM accounts")
    List<Account> getAllAccounts();
}