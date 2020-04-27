package cn.est.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   故障选项
*/
public class MalfunctionOptions implements Serializable {
    //
    private Long id;
    //商品型号id
    private Long modelId;
    //故障id
    private Long malfId;
    //名称
    private String optionName;
    //是否有提示0:否,1:是)
    private Integer isHint;
    //提示信息
    private String hintInfo;
    //提示图片
    private String hintImg;
    //处理方式（1:维修,2:更换配件）
    private Integer processType;
    //本项问题价格占比（最大10）
    private Integer ratio;
    //创建人
    private Long createdUserId;
    //修改人
    private Long updatedUserId;
    //创建时间
    private Date creatdTime;
    //修改时间
    private Date updatedTime;
    //是否删除(0:否,1:是)
    private Integer isDelete;
    //get set 方法
    public void setId (Long  id){
        this.id=id;
    }
    public  Long getId(){
        return this.id;
    }
    public void setModelId (Long  modelId){
        this.modelId=modelId;
    }
    public  Long getModelId(){
        return this.modelId;
    }
    public void setMalfId (Long  malfId){
        this.malfId=malfId;
    }
    public  Long getMalfId(){
        return this.malfId;
    }
    public void setOptionName (String  optionName){
        this.optionName=optionName;
    }
    public  String getOptionName(){
        return this.optionName;
    }
    public void setIsHint (Integer  isHint){
        this.isHint=isHint;
    }
    public  Integer getIsHint(){
        return this.isHint;
    }
    public void setHintInfo (String  hintInfo){
        this.hintInfo=hintInfo;
    }
    public  String getHintInfo(){
        return this.hintInfo;
    }
    public void setHintImg (String  hintImg){
        this.hintImg=hintImg;
    }
    public  String getHintImg(){
        return this.hintImg;
    }
    public void setProcessType (Integer  processType){
        this.processType=processType;
    }
    public  Integer getProcessType(){
        return this.processType;
    }
    public void setRatio (Integer  ratio){
        this.ratio=ratio;
    }
    public  Integer getRatio(){
        return this.ratio;
    }
    public void setCreatedUserId (Long  createdUserId){
        this.createdUserId=createdUserId;
    }
    public  Long getCreatedUserId(){
        return this.createdUserId;
    }
    public void setUpdatedUserId (Long  updatedUserId){
        this.updatedUserId=updatedUserId;
    }
    public  Long getUpdatedUserId(){
        return this.updatedUserId;
    }
    public void setCreatdTime (Date  creatdTime){
        this.creatdTime=creatdTime;
    }
    public  Date getCreatdTime(){
        return this.creatdTime;
    }
    public void setUpdatedTime (Date  updatedTime){
        this.updatedTime=updatedTime;
    }
    public  Date getUpdatedTime(){
        return this.updatedTime;
    }
    public void setIsDelete (Integer  isDelete){
        this.isDelete=isDelete;
    }
    public  Integer getIsDelete(){
        return this.isDelete;
    }
}
