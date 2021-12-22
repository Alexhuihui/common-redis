package top.alexmmd.redis.lock.service;

import top.alexmmd.redis.delay.bean.Student;

/**
 * @author 汪永晖
 * @date 2021/12/22 15:42
 */
public interface IStudentService {

    /**
     * 修改学生姓名，以学生id为锁的key
     */
    void updateStudent(Student student);

    /**
     * 根据id查询学生信息
     */
    Student queryStudent(Integer id);
}
