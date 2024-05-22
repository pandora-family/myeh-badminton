package io.walker.planes.myehbadminton.controller;

import io.walker.planes.myehbadminton.model.item.ItemRecommendRecord;
import io.walker.planes.myehbadminton.model.item.dto.ItemRecommendDTO;
import io.walker.planes.myehbadminton.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Planeswalker23
 */
@RestController("/action")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping("/recommend")
    public ItemRecommendRecord recommend(ItemRecommendDTO recommend) {
        return actionService.recommend(recommend);
    }
}
