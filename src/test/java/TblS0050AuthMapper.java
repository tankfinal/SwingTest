package gov.nsb.kmsapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import gov.nsb.kmsapp.dao.driver.InsertExtendedLanguageDriver;
import gov.nsb.kmsapp.dao.driver.UpdateExtendedLanguageDriver;
import gov.nsb.kmsapp.model.TblS0050Auth;

public interface TblS0050AuthMapper {
	String SQLSELECT = "SELECT * FROM tbl_S0050_auth ";

	@Results(id = "tbl_S0050_auth", value = { @Result(property = "id", column = "id"),
			@Result(property = "applicationNumber", column = "application_number"),
			@Result(property = "groupCode", column = "group_code"),
			@Result(property = "seasonCode", column = "season_code"),
			@Result(property = "fileTypeCode", column = "file_type_code"),
			@Result(property = "creator", column = "creator"), 
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updater", column = "updater"),
			@Result(property = "updateTime", column = "update_time") })
	@Select(SQLSELECT + " WHERE id = #{id} ")
	TblS0050Auth selectByPrimaryKey(int id);

	@Select(SQLSELECT)
	@ResultMap(value = "tbl_S0050_auth")
	List<TblS0050Auth> selectAll();

	@Insert("INSERT INTO tbl_S0050_auth (#{tbl_S0050_auth})")
	@Lang(InsertExtendedLanguageDriver.class)
	void insert(TblS0050Auth model);

	@Update("UPDATE tbl_S0050_auth (#{tbl_S0050_auth}) WHERE id = #{id}")
	@Lang(UpdateExtendedLanguageDriver.class)
	void update(TblS0050Auth model);

	@Delete("DELETE FROM tbl_S0050_auth WHERE id = #{id}")
	void deleteByPrimarykey(int id);
}