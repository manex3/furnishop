package com.furni.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailorderDTO {
	private int detailno;
	private int orderno;
	private int itemno;

	//조인
	private String item_name;
	private String item_color;
	private int item_cnt;
}
