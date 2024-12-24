package com.aurionpro.loan.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PageResponse<T> {

	private long totalElements;
	 private int totalPages;
	 private int pageSize;
	 private List<T> contents;
	 private boolean isLastPage;
	
	
}
