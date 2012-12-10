package com.proxomoandroidsdk.test;
import java.util.Date;

import com.proxomoandroidsdk.definitions.ProxomoData;
	
public class MyCustomData implements ProxomoData{
	private String TableName;
    private String ID;
    private String Data;
    private Date Date;
    private String Timestamp;

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        this.Date = date;
    }
}
