package com.qingteng.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String probMac;
    private String devcMac;
    private String inTime;
    private String outTime;
    private String rssi;


    public Customer(String probMac, String devcMac, String inTime,
                    String outTime, String rssi) {
        this.probMac = probMac;
        this.devcMac = devcMac;
        this.inTime = inTime;
        this.outTime = outTime;
        this.rssi = rssi;
    }



    @Override
    public String toString() {
        return "Customer [probMac=" + probMac + ", devcMac=" + devcMac
                + ", inTime=" + inTime + ", outTime=" + outTime + ", rssi="
                + rssi + "]";
    }
}
