package org.worker.ormframework.mybatis;

/**
 * @author peiru wang
 * @date 2021/7/24
 */
public interface StudentMapper {
    Student selectOneByKey(int id);
    int insertOne(Student student);
}
