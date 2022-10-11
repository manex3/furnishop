package com.furni.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.furni.dto.ReviewDTO;
import com.furni.service.ReviewService;

@SpringBootTest
class InsertReview {

	@Autowired
	ReviewService service;
	
	@Test
	void contextLoads() {
		try {
			service.register(new ReviewDTO(0, "id02", 108, 3, "배송빠름 마감은 쏘쏘", "2022-10-14", "reviewchair4" ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
