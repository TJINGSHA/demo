package com.dht.util;

import java.util.List;

public class PagerUtils {
    private int pageNumber;
    private int pageSize;
    private int pageTotal;
    private int pageCount;
    private int pageShow;
    private boolean hasFirst;
    private boolean hasLast;
    private int pageStep;
    private int pageBegin;
    private int pageEnd;
    private List<?> pageItems;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageShow() {
        return pageShow;
    }

    public boolean isHasFirst() {
        return hasFirst;
    }

    public boolean isHasLast() {
        return hasLast;
    }

    public int getPageStep() {
        return pageStep;
    }

    public int getPageBegin() {
        return pageBegin;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public List<?> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<?> pageItems) {
        this.pageItems = pageItems;
        this.pageShow = this.pageItems.size();
    }

    public PagerUtils() {
    }

    public PagerUtils(int pageNumber, int pageSize, int pageTotal, int pageStep) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.pageStep = pageStep;

        //计算总页数
        this.pageCount = this.pageTotal%this.pageSize==0?this.pageTotal/this.pageSize:this.pageTotal/this.pageSize+1;

        if(this.pageCount==0){
            return;
        }

        //页码修正 范围 0~(总页数-1)
        this.pageNumber = this.pageNumber<0?0:this.pageNumber;
        this.pageNumber = this.pageNumber>=this.pageCount-1?this.pageCount-1:this.pageNumber;

        this.hasFirst = this.pageNumber==0;
        this.hasLast = this.pageNumber==this.pageCount-1;

        this.pageBegin = this.pageNumber+2-this.pageStep>1?this.pageNumber+2-this.pageStep:1;
        this.pageEnd = this.pageNumber+this.pageStep>this.pageCount?this.pageCount:this.pageNumber+this.pageStep;

    }

    @Override
    public String toString() {
        return "PagerUtils{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", pageCount=" + pageCount +
                ", pageShow=" + pageShow +
                ", hasFirst=" + hasFirst +
                ", hasLast=" + hasLast +
                ", pageStep=" + pageStep +
                ", pageBegin=" + pageBegin +
                ", pageEnd=" + pageEnd +
                ", pageItems=" + pageItems +
                '}';
    }
}
