package it.caoxin.smarthome.domain.common;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
    private Integer page;
    private List<T> beanList = new ArrayList<T>();
    private Integer total;
    private Integer pageSize = 10;

    public PageBean() {
    }

    public PageBean(Integer page, List<T> beanList, Integer total, Integer pageSize) {
        this.page = page;
        this.beanList = beanList;
        this.total = total;
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        int pageTotal = total / pageSize;
        if (total % pageSize != 0){
            this.total = pageTotal + 1;
        }else {
            this.total = pageTotal;
        }

    }
}
