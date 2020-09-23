package com.br.process.object;

import lombok.Data;

import java.util.List;
@Data
public class ResultListCriteria {
    private long number_registers;
    private List<?> list_items;
    private int current_page;
    private long total_pages;
}
