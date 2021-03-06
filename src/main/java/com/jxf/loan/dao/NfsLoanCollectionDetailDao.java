package com.jxf.loan.dao;

import com.jxf.svc.sys.crud.dao.CrudDao;
import com.jxf.svc.annotation.MyBatisDao;
import com.jxf.loan.entity.NfsLoanCollectionDetail;

/**
 * 催收明细DAO接口
 * @author Administrator
 * @version 2018-12-24
 */
@MyBatisDao
public interface NfsLoanCollectionDetailDao extends CrudDao<NfsLoanCollectionDetail> {
	
}