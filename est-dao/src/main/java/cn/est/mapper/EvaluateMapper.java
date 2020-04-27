package cn.est.mapper;
import cn.est.pojo.Evaluate;
import org.apache.ibatis.annotations.Param;

public interface EvaluateMapper {

	public Evaluate getEvaluateById(@Param(value = "id") Long id);

	public Integer insertEvaluate(Evaluate evaluate);

}
