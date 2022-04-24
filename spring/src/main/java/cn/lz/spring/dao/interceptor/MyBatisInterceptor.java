package cn.lz.spring.dao.interceptor;

import cn.lz.spring.constants.PowerConstant;
import cn.lz.spring.dao.annos.RequireDataPermission;
import cn.lz.spring.po.UserPo;
import cn.lz.spring.util.AuthUtil;
import cn.lz.spring.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

@Slf4j
@Component
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class,
        /*method 的两个参数*/args = {Connection.class, Integer.class})})
public class MyBatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            //获取路由RoutingStatementHandler
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            //获取StatementHandler
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(statementHandler, "delegate");

            //获取sql
            BoundSql boundSql = delegate.getBoundSql();

            //获取mapper接口
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //获取mapper类文件
            Class<?> clazz = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
            //获取mapper执行方法名
            int length = mappedStatement.getId().length();
            String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, length);

            //遍历方法
            for (Method method : clazz.getDeclaredMethods()) {
                //方法是否含有RequiredDataPermission注解，如果含有注解则将数据结果过滤
                if (method.isAnnotationPresent(RequireDataPermission.class) && mName.equals(method.getName())) {
                    RequireDataPermission requiredPermission = method.getAnnotation(RequireDataPermission.class);
                    //判断是否为select语句
                    if (mappedStatement.getSqlCommandType().toString().equalsIgnoreCase("SELECT")) {
                        String sql = boundSql.getSql();
                        //根据用户权限拼接sql
                        UserPo user = AuthUtil.getCurrentUser();
                        String power = user.getPermissionLevel();
                        if (power == null) {
                            throw new RuntimeException("用户未登陆");
                        }
                        String column = requiredPermission.value();
                        int prop = requiredPermission.prop();
                        if (!power.equals("all")) {
                            sql = getSql(user, sql, column, prop);
                            //将sql注入boundSql
                            // TODO
                            ReflectUtil.setFieldValue(boundSql, "sql", sql);
                        }
                    }
                    break;
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String getSql(UserPo user, String sql, String column, int prop) {
        String prop0 = "";
        switch (prop) {
            case PowerConstant.ID:
                prop0 = user.getId().toString();
                break;
            case PowerConstant.PLANT:
                prop0 = user.getName();
                break;
            default:
                throw new RuntimeException("没有符合条件的属性");
        }
        return "select * from (" + sql + " ) as p where p." + column + " = '" + prop0 + "'";
    }
}
