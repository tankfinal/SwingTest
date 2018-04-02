package gov.nsb.kmsapp.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import gov.nsb.kmsapp.dao.TblS0050AuthMapper;
import gov.nsb.kmsapp.model.TblS0050Auth;
import gov.nsb.kmsapp.service.TblS0050AuthService;

@Service
@Configurable
public class TblS0050AuthServiceImpl implements TblS0050AuthService {
	private Logger logger = LogManager.getLogger(this);
	@Resource
	TblS0050AuthMapper tblS0050AuthMapper;

	@Override
	@Transactional(rollbackFor = SQLException.class, readOnly = true)
	public List<TblS0050Auth> selectAll() {
		return tblS0050AuthMapper.selectAll();
	}
}
