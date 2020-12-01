package com.gl.combo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gl.combo.configuration.Values;

@Component
public class SubscriptionDao {

	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Values propertiesReader;
	
	public Integer checkSubscriber(String msisdn) {
		logger.info("Checking if msisdn is subscriber or not");
		String query="select count(detail_id) as count from com_subscribed_users_details where msisdn='"+msisdn+"' and biller_id='"+propertiesReader.getBp()+"' and publisher='"+propertiesReader.getPublisher()+"'";
		logger.info("Query: "+query);
		try {
			return jdbcTemplate.query(query, new ResultSetExtractor<Integer>(){
				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					Integer count=0;
					while(rs.next()) {
						count=rs.getInt("count");
					}
					return count;
				}
			});
		} catch (Exception e) {
			logger.info("Exception in checking if msisdn is subscriber or not: "+e);
			return null;
		}
	}

	public String checkForActiveAndGraceState(String msisdn) {
		logger.info("Checking if msisdn is active subscriber or in grace");
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDate now = LocalDate.now();
			String query="select next_renewal_date from com_subscribed_users_details where msisdn='"+msisdn+"' and biller_id='"+propertiesReader.getBp()+"' and publisher='"+propertiesReader.getPublisher()+"' order by next_renewal_date desc limit 1";
			logger.info("Query: "+query);
			return jdbcTemplate.query(query,new ResultSetExtractor<String>(){
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					String status="";
					while(rs.next()) {
						String nextRenewalDate=rs.getString("next_renewal_date");
						LocalDate date = LocalDate.parse(nextRenewalDate, dtf);
						int dateStatus=date.compareTo(now);
						if(dateStatus<0) {
							status="GRACE";
						}else if(dateStatus>=0){
							status="ACTIVE";
						}
					}
					return status;
				}
			});
		} catch (Exception e) {
			logger.info("Exception in checking if msisdn is active subscriber or in grace: "+e);
			return null;
		}
	}

}
