package org.worker.ormframework.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author peiru wang
 * @date 2021/7/21
 */
public class MyBatisDemo {
    public static void main(String[] args) {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//            Student student = new Student();
//            student.setName("feichen");
//            student.setAge(35);
//            student.setNumber("20120001");
//            int one = mapper.insertOne(student);
//            System.out.println(one);
            System.out.println("=====first query");
            mapper.selectOneByKey(4);
            Object o = sqlSession.selectOne(StudentMapper.class.getName() + ".selectOneByKey", 4);
            System.out.println(o);
            System.out.println("=====second query");
            mapper.selectOneByKey(4);

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
