package io.walker.planes.myehbadminton.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode
public class BaseModel<T> implements Serializable {

    private static final long serialVersionUID = 2991044064104751614L;

    private T id;

    private Long version;

    private LocalDateTime createAt;

    private Long createBy;

    private LocalDateTime updateAt;

    private Long updateBy;
}
