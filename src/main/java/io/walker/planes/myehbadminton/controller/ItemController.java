package io.walker.planes.myehbadminton.controller;

import io.walker.planes.myehbadminton.model.item.dto.ItemDTO;
import io.walker.planes.myehbadminton.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Planeswalker23
 */
@RestController("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/getItemByName")
    public ItemDTO getItemByName(String name) {
        return itemService.getItemByName(name);
    }
}
