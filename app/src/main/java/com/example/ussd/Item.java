package com.example.ussd;

public class Item {

    private String img;
    private String txtName;
    private String txtUssd;
//    private String txtMessage;

    public Item(String txtUssd, String txtName) {

        this.txtName = txtName;
        this.txtUssd = txtUssd;
    }


    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtUssd() {
        return txtUssd;
    }

    public void setTxtUssd(String txtUssd) {
        this.txtUssd = txtUssd;
    }

}