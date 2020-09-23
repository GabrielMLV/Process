package com.br.process.object;

import lombok.Data;

@Data
public class SearchCriteria {
    private String search_criteria;
    private int asked_page;
    private int items_per_page;
}
