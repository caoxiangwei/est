package cn.est.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 *
 * @param <T>
 */
@ApiModel("分页信息")
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 5294589632707269745L;
    //默认页大小
    private static int DEFAULT_PAGE_SIZE = 20;
    private static int DEFAULT_PAGE_NO = 1;
    //当前页码
    @ApiModelProperty(value = "页码")
    private int pageNo;

    /**
     * 每页的记录数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Integer total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总记页数")
    private int pages;

    /**
     * 数据
     */
    @ApiModelProperty(value = "分页数据")
    private List<T> list;

    //开始位置
    private Integer beginPos;

    public Page(List<T> list) {
        this.list = list;
    }

    public Page(int pageNo, int pageSize,Integer total) {
        pageNo = (pageNo==0 )? DEFAULT_PAGE_NO : pageNo;
        pageSize = (pageSize==0 )?DEFAULT_PAGE_SIZE : pageSize;
        this.beginPos=(pageNo-1)*pageSize;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Page(List<T> list, int pageNo, int pageSize, int total) {
        this.list = list;
        this.pageNo = pageNo;
        this.setPageSize(pageSize);
        this.setTotal(total);
    }

    public Page() {
        new Page<T>(new ArrayList<T>(), 0, 0, 0);
    }

    public void setPage(int pageNo, int pageSize,Integer total) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public static void setDefaultPageSize(int defaultPageSize) {
        DEFAULT_PAGE_SIZE = defaultPageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }
}
