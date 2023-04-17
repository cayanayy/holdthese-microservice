package com.cayanay.itemservice.repository;

import com.cayanay.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getItemsByCodeId(Long codeId);
}
