package top.alexmmd.redis.delay.bean;

import lombok.*;

/**
 * @author 汪永晖
 * @date 2021/12/22 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

    private String name;

    private Integer id;

    private String level;
}
