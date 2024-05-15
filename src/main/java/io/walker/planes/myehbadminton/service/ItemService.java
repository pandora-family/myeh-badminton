package io.walker.planes.myehbadminton.service;

import io.walker.planes.myehbadminton.model.item.*;
import io.walker.planes.myehbadminton.model.item.dto.ItemDTO;
import io.walker.planes.myehbadminton.repo.item.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Planeswalker23
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;
    private final BrandRepo brandRepo;
    private final ItemSkuRepo itemSkuRepo;
    private final ItemPriceRepo itemPriceRepo;
    private final ItemRecommendRecordRepo itemRecommendRecordRepo;

    public ItemDTO getItemByName(String name) {
        Item item = itemRepo.selectByName(name);
        if (item == null) {
            return null;
        }

        ItemDTO result = new ItemDTO(item);

        if (item.getId() != null) {
            result.setItemRecommendRecords(itemRecommendRecordRepo.selectByItemId(item.getId()));
        }

        if (item.getBrandId() != null) {
            Brand brand = brandRepo.selectById(item.getBrandId());
            if (brand != null) {
                result.setBrand(brand);
            }
        }

        if (item.getItemSkuId() != null) {
            ItemSku itemSku = itemSkuRepo.selectById(item.getItemSkuId());
            if (itemSku != null) {
                result.setItemSku(itemSku);
            }

            List<ItemPrice> itemPrices = itemPriceRepo.selectByItemSkuId(item.getItemSkuId());
            if (!CollectionUtils.isEmpty(itemPrices)) {
                result.setItemPriceSnapshot(itemPrices);
            }
        }


        return result;
    }
}
