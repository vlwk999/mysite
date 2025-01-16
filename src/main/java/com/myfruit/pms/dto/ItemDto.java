package com.myfruit.pms.dto;

public class ItemDto {
    private Integer id;
    private String item;


    //Getter,Setter 설정
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id =id;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }
}
