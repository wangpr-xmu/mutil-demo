package org.worker.ormframework.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author peiru wang
 * @date 2021/7/24
 */
public interface StudentMapper {
    Student selectOneByKey(int id);
    List<Student> selectListByKeys(@Param("ids") List<Integer> ids);
    int insertOne(Student student);
}
