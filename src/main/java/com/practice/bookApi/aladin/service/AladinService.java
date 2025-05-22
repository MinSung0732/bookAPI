package com.practice.bookApi.aladin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bookApi.aladin.dto.AladinDto;

@Service
public class AladinService {

    @Value("${aladin.TTBKey}")
    private String ttbKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String buildUrl(String baseUrl, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl).append("?");
        params.put("ttbkey", ttbKey);
        params.put("output", "JS");
        params.put("Version", "20131101");

        params.forEach((key, value) -> urlBuilder.append(key).append("=").append(value).append("&"));
        urlBuilder.setLength(urlBuilder.length() - 1); // 마지막 & 제거
        
        
        return urlBuilder.toString();
    }

    private List<AladinDto> parseItemList(JsonNode items) {
        if (items == null || !items.isArray()) return Collections.emptyList();

        List<AladinDto> books = new ArrayList<>();
        for (JsonNode itemNode : items) {
            try {
                // AladinItem을 Jackson으로 전체 매핑
                AladinDto.AladinItem aladinItem = objectMapper.treeToValue(itemNode, AladinDto.AladinItem.class);

                // AladinDto에 필요한 필드 직접 세팅
                AladinDto book = new AladinDto();
                book.setTitle(aladinItem.getTitle());
                book.setAuthor(aladinItem.getAuthor());
                book.setImageUrl(aladinItem.getCover());
                book.setIsbn13(aladinItem.getIsbn13());
                book.setDescription(aladinItem.getDescription());

                // AladinItem 리스트로 감싸서 저장
                book.setItem(List.of(aladinItem));

                books.add(book);
            } catch (Exception e) {
                e.printStackTrace(); // 필요 시 로깅
            }
        }

        return books;
    }

    public List<AladinDto> getWeeklyBestSellers() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("QueryType", "Bestseller");
        params.put("MaxResults", "10");
        params.put("SearchTarget", "Book");

        String url = buildUrl("http://www.aladin.co.kr/ttb/api/ItemList.aspx", params);
        String json = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(json);
        return parseItemList(root.path("item"));
    }

    public List<AladinDto> getItemList(String searchTarget) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("QueryType", "ItemNewAll");
        params.put("MaxResults", "50");
        params.put("start", "1");
        params.put("SearchTarget", searchTarget);

        String url = buildUrl("http://www.aladin.co.kr/ttb/api/ItemList.aspx", params);
        String json = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(json);
        return parseItemList(root.path("item"));
    }

    public AladinDto getItemDetailByIsbn(String isbn13) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("itemIdType", "ISBN");
        params.put("ItemId", isbn13);

        String url = buildUrl("http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx", params);
        String json = restTemplate.getForObject(url, String.class);
        
        System.out.println(json);
        
        JsonNode root = objectMapper.readTree(json);
        List<AladinDto> items = parseItemList(root.path("item"));
        if (items.isEmpty()) throw new Exception("API 응답에 item 데이터가 없습니다.");
        
        return items.get(0); // 첫 번째 아이템 반환
    }
}
