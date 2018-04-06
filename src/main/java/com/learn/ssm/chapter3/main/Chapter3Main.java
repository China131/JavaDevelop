package com.learn.ssm.chapter3.main;


import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.pojo.Role;
import com.learn.ssm.chapter3.utils.SqlSessionFactoryUtils;
import com.oracle.tools.packager.Log;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

/**
 * Created by jianhao on 2018/4/6.
 */
public class Chapter3Main {
    public static void main(String[] args){

        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            Log.info(role.getRoleName());
            roleMapper.findRoles("43");
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
