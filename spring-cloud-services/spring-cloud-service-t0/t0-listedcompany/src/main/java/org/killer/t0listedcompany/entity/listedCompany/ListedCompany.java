package org.killer.t0listedcompany.entity.listedCompany;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * 上市公司信息(ListedCompany)实体类
 *
 * @author killer
 * @since 2020-05-27 23:33:14
 */
@Entity
public class ListedCompany implements Serializable {

    private static final long serialVersionUID = -69662335337814647L;

    /**
    * TS代码
    */
    @Id
    private String tsCode;
    /**
    * 股票代码
    */
    private String symbol;
    /**
    * 股票名称
    */
    private String name;
    /**
    * 所在地域
    */
    private String area;
    /**
    * 所属行业
    */
    private String industry;
    /**
    * 股票全称
    */
    @Column(name = "fullname")
    private String fullName;
    /**
    * 英文全称
    */
    @Column(name = "enname")
    private String enName;
    /**
    * 市场类型
    */
    private String market;
    /**
    * 交易所代码
    */
    private String exchange;
    /**
    * 交易货币
    */
    private String currType;
    /**
    * 上市状态： L上市 D退市 P暂停上市
    */
    private String listStatus;
    /**
    * 上市日期
    */
    private LocalDate listDate;
    /**
    * 退市日期
    */
    private LocalDate delistDate;
    /**
    * 是否沪深港通标的，N否 H沪股通 S深股通
    */
    private String isHs;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrType() {
        return currType;
    }

    public void setCurrType(String currType) {
        this.currType = currType;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public LocalDate getListDate() {
        return listDate;
    }

    public void setListDate(LocalDate listDate) {
        this.listDate = listDate;
    }

    public LocalDate getDelistDate() {
        return delistDate;
    }

    public void setDelistDate(LocalDate delistDate) {
        this.delistDate = delistDate;
    }

    public String getIsHs() {
        return isHs;
    }

    public void setIsHs(String isHs) {
        this.isHs = isHs;
    }

    @Override
    public String toString() {
        return "ListedCompany{" +
                "tsCode='" + tsCode + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", industry='" + industry + '\'' +
                ", fullName='" + fullName + '\'' +
                ", enName='" + enName + '\'' +
                ", market='" + market + '\'' +
                ", exchange='" + exchange + '\'' +
                ", currType='" + currType + '\'' +
                ", listStatus='" + listStatus + '\'' +
                ", listDate=" + listDate +
                ", delistDate=" + delistDate +
                ", isHs='" + isHs + '\'' +
                '}';
    }
}