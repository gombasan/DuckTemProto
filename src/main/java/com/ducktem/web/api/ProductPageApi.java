package com.ducktem.web.api;

import com.ducktem.web.entity.DucktemUserDetails;
import com.ducktem.web.entity.Product;
import com.ducktem.web.entity.ProductPreview;
import com.ducktem.web.service.ProductPreviewService;
import com.ducktem.web.service.ProductPreviewServiceImpl;
import com.ducktem.web.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductPageApi {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPreviewService productPreviewService;


    @GetMapping("/{page}")
    public List<ProductPreview> homeProductList(@AuthenticationPrincipal DucktemUserDetails user, @PathVariable(name = "page") int page) {
    	String userId = null;
    	
    	if(user != null) {
    		userId = user.getUsername();
    	}
    	
    	List<ProductPreview> previews = productPreviewService.preview(page, userId);

        return previews;
    }

    @PostMapping("/product/myproduct/sellStatesChange/{productId}/{selectState}")
    public void productSellStatesChange(@PathVariable("productId") Long productId ,@PathVariable("selectState") int salesStatusId) {
        productService.stateChange(productId,salesStatusId);

    }

}
