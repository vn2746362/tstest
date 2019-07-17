package com.horizon.tstest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*By Phat La*/
public class ProvincesDTO extends DTO {
    private int total;

    @SerializedName("provinces_list")
    private List<String> list;


    //
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
