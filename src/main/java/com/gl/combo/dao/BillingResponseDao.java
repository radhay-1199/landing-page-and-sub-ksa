package com.gl.combo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.gl.combo.bean.BillingResponse;
import com.gl.combo.configuration.Values;


@Component
public class BillingResponseDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Values propertiesReader;
	
	public int insertdetails(BillingResponse b,String bp,String action) {
		try {
			logger.info("inserting into table com_billing_response");
			String query="insert into com_billing_response(calling_party,serviceId,serviceType,requestPlan,sequenceNo,chargeAmount,appliedPlan,discountPlan,validityDays,operationId,createdon,bearerId,errorCode,result,contentId,category,optParam1,optParam2,optParam3,optParam4,optParam5,same_day_churn,biller_id,action,publisher) "
					+"values(?,?,?,?,?,?,?,?,?,?,"+b.getCreatedon()+",?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(query, new String[] {"id"});
							ps.setString(1,b.getCallingParty());
							ps.setString(2,b.getServiceId());
							ps.setString(3,"");
							ps.setString(4,b.getRequestPlan());
							ps.setString(5,b.getSequenceNo());
							ps.setFloat(6,Float.parseFloat(b.getChargeAmount()));
							ps.setString(7,"");
							ps.setString(8,"");
							ps.setString(9,b.getValidityDays());
							ps.setString(10,b.getOperationId());
							ps.setString(11,b.getBearerId());
							ps.setString(12,"");
							ps.setString(13,"");
							ps.setString(14,"");
							ps.setString(15,"");
							ps.setString(16,"");
							ps.setString(17,"");
							ps.setString(18,"");
							ps.setString(19,"");
							ps.setString(20,"");
							ps.setInt(21,0);
							ps.setString(22,bp);
							ps.setString(23,action);
							ps.setString(24,propertiesReader.getPublisher());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
		}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_billing_response: ."+e);
			e.printStackTrace();
			return 0;
		}
	}
}
