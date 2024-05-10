package io.walker.planes.myehbadminton.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode
public class BaseModel<T> {

    private T id;

    private Long version;

    private LocalDateTime createAt;

    private Long createBy;

    private LocalDateTime updateAt;

    private Long updateBy;
}
