package com.cayanay.itemservice.controller;

import com.cayanay.itemservice.dto.request.CreateItemRequest;
import com.cayanay.itemservice.dto.response.ItemResponse;
import com.cayanay.itemservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemResponse> getItemsByCode(@Valid @RequestParam("codeId") Long codeId) {
        return itemService.getItemsByCode(codeId);
    }

    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponse getItem(@Valid @PathVariable("itemId") Long itemId) {
        return itemService.getItem(itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@Valid @ModelAttribute CreateItemRequest createItemRequest) {
        itemService.createItem(createItemRequest);
    }
}
