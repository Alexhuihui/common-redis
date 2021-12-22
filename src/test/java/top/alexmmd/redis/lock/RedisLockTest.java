package top.alexmmd.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import top.alexmmd.redis.CommonRedisApplicationTests;
import top.alexmmd.redis.delay.bean.Student;
import top.alexmmd.redis.lock.service.IStudentService;

import javax.annotation.Resource;

/**
 * @author 汪永晖
 * @date 2021/12/22 15:39
 */
@Slf4j
public class RedisLockTest extends CommonRedisApplicationTests {

    @Resource
    private IStudentService service;

    @Test
    public void testRedisLock() {
        service.updateStudent(Student.builder()
                .id(1)
                .name("陈")
                .build());
        new Thread(() -> service.updateStudent(Student.builder()
                .id(1)
                .name("汪")
                .build())).start();
        Student student = service.queryStudent(1);
        Assertions.assertNotNull(student);
        log.info(student.toString());
        Assertions.assertEquals("陈", student.getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        student = service.queryStudent(1);
        log.info(student.toString());
        Assertions.assertEquals("汪", student.getName());
    }
}
