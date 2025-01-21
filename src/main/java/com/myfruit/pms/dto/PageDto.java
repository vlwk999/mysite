package com.myfruit.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto {
    private int page;
    private int limit;
    private int totalPages;
    private int totalElements;
    private List<ItemDto> items;
}
