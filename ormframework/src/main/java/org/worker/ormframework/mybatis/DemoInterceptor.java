package org.worker.ormframework.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author peiru wang
 * @date 2021/7/25
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)
})
public class DemoInterceptor implements Interceptor {
    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(properties.getProperty("name"));
        Object object = invocation.proceed();
        System.out.println(properties.getProperty("age", "35"));
        return object;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
