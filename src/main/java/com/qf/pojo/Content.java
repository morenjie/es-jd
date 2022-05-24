package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Content {
    private String title;
    private String img;
    private String price;
    private String shop;
    private String preferential;
    //可以自己添加其他属性
}
