package io.walker.planes.myehbadminton.service;

import io.walker.planes.myehbadminton.model.item.ItemRecommendRecord;
import io.walker.planes.myehbadminton.model.item.dto.ItemRecommendDTO;
import io.walker.planes.myehbadminton.repo.item.BrandRepo;
import io.walker.planes.myehbadminton.repo.item.ItemRecommendRecordRepo;
import io.walker.planes.myehbadminton.repo.item.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Planeswalker23
 */
@Service
@RequiredArgsConstructor
public class ActionService {

    private final BrandRepo brandRepo;
    private final ItemRepo itemRepo;
    private final ItemRecommendRecordRepo itemRecommendRecordRepo;

    public ItemRecommendRecord recommend(ItemRecommendDTO recommend) {
        // 1. 根据品牌name查询品牌是否存在

        // 1.2 如果不存在需要创建品牌

        // 1.3 品牌不存在，商品肯定也不存在，继续创建商品

        // 2. 根据品牌ID+商品name查询商品是否存在

        // 2.2 如果不存在需要创建商品

        // 3. 创建推荐记录

        return null;
    }
}
