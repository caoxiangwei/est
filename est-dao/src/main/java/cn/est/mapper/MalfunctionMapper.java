package cn.est.mapper;
import cn.est.dto.MalfunctionDto;
import cn.est.pojo.Malfunction;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MalfunctionMapper {

	public Malfunction getMalfunctionById(@Param(value = "id") Long id);

	/**
	 * 旧品维修评估维度
	 * @param modelId
	 * @return
	 */
	List<MalfunctionDto> selectListByModelId(Long modelId);
}
