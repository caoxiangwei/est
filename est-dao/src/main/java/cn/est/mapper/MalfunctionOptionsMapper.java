package cn.est.mapper;

import cn.est.dto.MalfunctionOptionsDto;
import cn.est.pojo.MalfunctionOptions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MalfunctionOptionsMapper {

	public MalfunctionOptions getMalfunctionOptionsById(@Param(value = "id") Long id);

	/**
	 * 根据故障id查询维修选项
	 * @param malfId
	 * @return
	 */
	List<MalfunctionOptionsDto> selectListByMalfId(Long malfId);
}
