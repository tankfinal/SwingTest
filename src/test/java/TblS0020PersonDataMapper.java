package gov.nsb.kmsapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nsb.kmsapp.dao.driver.InsertExtendedLanguageDriver;
import gov.nsb.kmsapp.dao.driver.UpdateExtendedLanguageDriver;
import gov.nsb.kmsapp.model.TblS0020PersonData;

public interface TblS0020PersonDataMapper {
	String SQLSELECT = "SELECT * FROM tbl_S0020_person_data ";

	@Results(id = "tbl_S0020_person_data", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "accId", column = "acc_id"),
			@Result(property = "accPd", column = "acc_pd"),
			@Result(property = "accName", column = "acc_name"),
			@Result(property = "accPermission", column = "acc_permission"),
			@Result(property = "accDeptCode", column = "acc_dept_code"),
			@Result(property = "accEmail", column = "acc_email"), 
			@Result(property = "accExt", column = "acc_ext"),
			@Result(property = "creator", column = "creator"), 
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updater", column = "updater"),
			@Result(property = "updateTime", column = "update_time") })
	@Select(SQLSELECT + " WHERE acc_id = #{accId} and acc_pd = #{accPd} ")
	TblS0020PersonData selectByAccIdAndAccPd(@Param("accId") String accId, @Param("accPd") String accPd);

	@Select(SQLSELECT + " WHERE id =#{id} ")
	TblS0020PersonData selectByPrimaryKey(int id);

	@Select(SQLSELECT)
	@ResultMap(value = "tbl_S0020_person_data")
	List<TblS0020PersonData> selectAll();

	@Insert("INSERT INTO tbl_S0020_person_data (#{tbl_S0020_person_data})")
	@Lang(InsertExtendedLanguageDriver.class)
	void insert(TblS0020PersonData model);

	@Update("UPDATE tbl_S0020_person_data (#{tbl_S0020_person_data}) WHERE id =#{id}")
	@Lang(UpdateExtendedLanguageDriver.class)
	void update(TblS0020PersonData model);

	@Delete("DELETE FROM tbl_S0020_person_data WHERE id =#{id}")
	void delete(int id);
}