package com.gl.combo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.gl.combo.bean.SubscribedUsersDetails;
import com.gl.combo.configuration.Values;


@Component
public class SubscribedUsersDetailsDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Values propertiesReader;
	
	public int insertdetails(SubscribedUsersDetails s) {
		try {
			logger.info("inserting into table com_subscribed_users_details");
			String query="insert into com_subscribed_users_details(biller_id,publisher,msisdn,pack_id,subscription_date,status,"
					+ "exp_date,session_id,product_id)"
					+ " values(?,?,?,?,"+s.getSubscriptionDate()+",?,"+s.getExpDate()+",?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(query, new String[] {"id"});
							ps.setString(1, s.getBillerId());
							ps.setString(2,s.getPublisher());
							ps.setString(3, s.getMsisdn());
							ps.setInt(4,s.getPackId());
							ps.setInt(5, s.getStatus());
							ps.setString(6,s.getSessionId());
							ps.setString(7,s.getProductId());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
		}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_subscribed_users_details: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateDetails(String msisdn,Integer status) {
		try {
			logger.info("updating unsubscribed users details in table com_subscribed_users_details");
			String query="update com_subscribed_users_details set unsubscription_date=CURRENT_TIMESTAMP(),status='"+status+"' where msisdn='"+msisdn+"' and publisher='"+propertiesReader.getPublisher()+"' and biller_id='"+propertiesReader.getBp()+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
		return 1;
		}catch(Exception e) {
			System.out.println("exception while updating unsubscription details in table com_subscribed_users_details table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int suspend(String msisdn,int status) {
		try {
		logger.info("suspending user");
		String query="update com_subscribed_users_details set status="+status+" where msisdn='"+msisdn+"' and publisher='"+propertiesReader.getPublisher()+"' and biller_id='"+propertiesReader.getBp()+"'";
		logger.info("query: "+query);
		jdbcTemplate.execute(query);
	return 1;
		}catch(Exception e) {
			System.out.println("exception while suspending user in table com_subscribed_users_details table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateDetailsDaily(String msisdn,String expDate,String nextDate) {
		try {
			logger.info("updating daily details in table com_subscribed_users_details");
			String query="update com_subscribed_users_details set exp_date='"+expDate+"',next_renewal_date='"+nextDate+"' where msisdn='"+msisdn+"' and publisher='"+propertiesReader.getPublisher()+"' and biller_id='"+propertiesReader.getBp()+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
		return 1;
		}catch(Exception e) {
			logger.info("exception while updating daily details in table com_subscribed_users_details table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<SubscribedUsersDetails> getAllSubscribers(){ 
		String query ="select distinct(msisdn) from com_subscribed_users_details where status=1 and exp_date>=CURRENT_TIMESTAMP() and publisher='"+propertiesReader.getPublisher()+"'";
		logger.info("query: "+query);
		 return jdbcTemplate.query(query,new ResultSetExtractor<List<SubscribedUsersDetails>>(){  
		    @Override  
		     public List<SubscribedUsersDetails> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      List<SubscribedUsersDetails> subscribedUsersDetailsList=new ArrayList<SubscribedUsersDetails>();  
		        while(rs.next()){  
		        	SubscribedUsersDetails subscribedUsersDetails=new SubscribedUsersDetails(); 
		        	subscribedUsersDetails.setMsisdn(rs.getString("msisdn"));
		        	subscribedUsersDetailsList.add(subscribedUsersDetails);
		        }  
		        return subscribedUsersDetailsList;  
		        }  
		    });  
	}  
}
