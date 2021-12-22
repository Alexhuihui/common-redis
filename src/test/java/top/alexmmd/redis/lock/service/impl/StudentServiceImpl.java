package top.alexmmd.redis.lock.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.alexmmd.redis.annos.RedisLock;
import top.alexmmd.redis.delay.bean.Student;
import top.alexmmd.redis.lock.service.IStudentService;

/**
 * @author 汪永晖
 * @date 2021/12/22 15:43
 */
@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {

    private static final LRUCache<Integer, Student> lruCache;

    static {
        lruCache = CacheUtil.newLRUCache(2);
    }

    @Override
    @RedisLock(lockName = "updateStudent", key = "#student.getId()")
    public void updateStudent(Student student) {
        // 模拟操作数据库
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        Student existStudent = lruCache.get(student.getId());
        if (ObjectUtil.isNull(existStudent)) {
            lruCache.put(student.getId(), student);
        } else {
            existStudent.setName(student.getName());
        }
    }

    @Override
    public Student queryStudent(Integer id) {
        return lruCache.get(id);
    }
}
