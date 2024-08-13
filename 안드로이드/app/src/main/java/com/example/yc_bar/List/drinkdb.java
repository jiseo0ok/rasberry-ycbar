package com.example.yc_bar.List;

public class drinkdb {
    String drinkid;
    String drinkname;
    String drinkcontent;
    String drinktime;
    String drinkphoto;

    public String getDrinkid() {
        return drinkid;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public String getDrinkcontent() {
        return drinkcontent;
    }

    public String getDrinktime() {
        return drinktime;
    }

    public String getDrinkphoto() {
        return drinkphoto;
    }

    public drinkdb(String drinkid, String drinkname, String drinkcontent, String drinktime, String drinkphoto) {
        this.drinkid = drinkid;
        this.drinkname = drinkname;
        this.drinkcontent = drinkcontent;
        this.drinktime = drinktime;
        this.drinkphoto = drinkphoto;
    }

    public drinkdb(String drinkname, String drinkphoto) {

        this.drinkname = drinkname;


        this.drinkphoto = drinkphoto;
    }

    public drinkdb(String drinkname, String drinkphoto,String drinkcontent) {

        this.drinkname = drinkname;
        this.drinkcontent=drinkcontent;

        this.drinkphoto = drinkphoto;
    }



}
