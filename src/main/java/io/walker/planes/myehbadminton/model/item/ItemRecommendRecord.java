package io.walker.planes.myehbadminton.model.item;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商品推荐记录
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemRecommendRecord extends BaseModel<Long> {

    private static final long serialVersionUID = 7755330318662064608L;

    /**
     * 商品
     * {@link io.walker.planes.myehbadminton.model.item.Item}
     */
    private Long itemId;

    /**
     * 商品型号
     * {@link io.walker.planes.myehbadminton.model.item.ItemSku}
     */
    private Long itemSkuId;

    /**
     * 推荐人
     * {@link io.walker.planes.myehbadminton.model.user.UserInfo}
     */
    private Long recommender;

    /**
     * 推荐时间
     */
    private LocalDateTime recommendAt;
}
