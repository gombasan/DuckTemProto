package com.ducktem.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ducktem.web.entity.DucktemUserDetails;
import com.ducktem.web.entity.ProductPreview;
import com.ducktem.web.service.ProductService;
import com.ducktem.web.service.SearchService;

@RestController
public class SearchApi {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SearchService searchService;

    @GetMapping("/api/search/{keyword}/{option}")
    public List<ProductPreview> filter(@AuthenticationPrincipal DucktemUserDetails user, @PathVariable("keyword") String keyword, @PathVariable("option") String option){
    	
		String userId = null;
    	
    	if(user != null) 
    		userId = user.getUsername();
    	
    	List<ProductPreview> list = searchService.getfilteredResult(keyword, option, userId);
    	
    	return list;
    }
    
	
}
