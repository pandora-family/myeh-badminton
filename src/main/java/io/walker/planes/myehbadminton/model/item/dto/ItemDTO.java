package io.walker.planes.myehbadminton.model.item.dto;

import io.walker.planes.myehbadminton.common.constant.dict.ItemTypeDict;
import io.walker.planes.myehbadminton.model.BaseModel;
import io.walker.planes.myehbadminton.model.item.Brand;
import io.walker.planes.myehbadminton.model.item.Item;
import io.walker.planes.myehbadminton.model.item.ItemPrice;
import io.walker.planes.myehbadminton.model.item.ItemSku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品
 *
 * @author Planeswalker23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemDTO extends BaseModel<Long> {

    private static final long serialVersionUID = -5052979319033887483L;

    /**
     * 编码
     */
    private String code;

    /**
     * 品牌
     */
    private Brand brand;

    /**
     * 名称
     */
    private String name;

    /**
     * 商品类型
     * {@link ItemTypeDict}
     */
    private String itemTypeDict;

    /**
     * 描述
     */
    private ItemSku itemSku;

    /**
     * 商品价格快照列表
     */
    private List<ItemPrice> itemPriceSnapshot;

    public ItemDTO(Item item) {
        super.setId(item.getId());
        super.setVersion(item.getVersion());
        super.setCreateAt(item.getCreateAt());
        super.setCreateBy(item.getCreateBy());
        super.setUpdateAt(item.getUpdateAt());
        super.setUpdateBy(item.getUpdateBy());

        this.code = item.getCode();
        this.name = item.getName();
        this.itemTypeDict = item.getItemTypeDict();
    }
}
