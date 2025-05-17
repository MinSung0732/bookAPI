package com.practice.bookApi.aladin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AladinItemSearchService {
	
	@Value("${aladin.TTBKey}")
	private String ttbKey;
}
