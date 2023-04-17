package com.cayanay.itemservice.service;

import com.cayanay.itemservice.core.mapper.ModelMapperService;
import com.cayanay.itemservice.dto.request.CreateItemRequest;
import com.cayanay.itemservice.dto.response.ItemResponse;
import com.cayanay.itemservice.entity.Item;
import com.cayanay.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapperService modelMapperService;
    private final WebClient.Builder webClientBuilder;

    public List<ItemResponse> getItemsByCode(Long codeId) {
        List<Item> items = itemRepository.getItemsByCodeId(codeId);
        return items.stream().map(item -> modelMapperService.forResponse().map(item, ItemResponse.class)).toList();
    }

    public ItemResponse getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        return modelMapperService.forResponse().map(item, ItemResponse.class);
    }

    public void createItem(CreateItemRequest createItemRequest) {
        Item item = modelMapperService.forRequest().map(createItemRequest, Item.class);
        item.setUnableAfter(LocalDateTime.now().plusMinutes(item.getDuration()));
        Long itemId = itemRepository.save(item).getId();
        if (createItemRequest.getFiles() != null && !createItemRequest.getFiles().isEmpty()) {
            createItemRequest.getFiles().forEach(multipartFile -> {
                MultipartBodyBuilder formDataBuilder = new MultipartBodyBuilder();
                formDataBuilder.part("itemId", itemId);
                formDataBuilder.part("file", multipartFile.getResource());
                webClientBuilder.build()
                        .post()
                        .uri("http://file-service/api/files")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .body(BodyInserters.fromMultipartData(formDataBuilder.build()))
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            });
        }
    }
}
