package com.gl.combo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.gl.combo.bean.Packs;
import com.gl.combo.bean.Transaction;
import com.gl.combo.configuration.Values;



@Component
public class TransactionDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Values propertiesReader;
	
	@Autowired
	PacksDao packsDao;
	
	public int insertdetails(Transaction t) {
		try {
			logger.info("inserting into table com_transaction");
			String query="insert into com_transaction(subscribed_user_id,transaction_unique_id,pack_id,interface,biller_id,hit_id,publisher,adnetwork_id,product_id,requested_price) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			logger.info("query: "+query);



			/*jdbcTemplate.execute("insert into com_transaction values("+t.getTransactionId()+","
			 * +t.getSubscribedUserId()+","+t.getMsisdn()+","+t.getType()+","+t.getPackId()+","+t.getProductId()+","
			 * +t.getTransactionTime()+","+t.getTransactionUniqueId()+","+t.getRequestedPrice()+","+t.getPriceDeducted()+","
			 * +t.getCgResponseId()+","+t.getCgStatus()+","+t.getCgStatusCode()+","+t.getCgResponseTime()+","
			 * +t.getBillingStatus()+","+t.getBillingResponseTime()+","+t.getBillingResponse()+","+t.getBillingId()+","
			 * +t.getBrowserCode()+","+t.getInterfacee()+","+t.getAdnetworkId()+","+t.getCallbackStatus()+","
			 * +t.getBillerId()+","+t.getHitId()+","+t.getCircleName()+","+t.getPublisher()+","+t.getContentId()+","
			 * +t.getSameDayChurn()+","+t.getV3mobiMsisdn()+","+t.getAppUserId()+","+t.getUserType()+","+t.getProcess()+","+
			 * t.getConsent()+","+t.getPubId()+","+t.getSessionId()+");");
			return 1;*/
			
			
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(query, new String[] {"id"});
							ps.setInt(1, t.getSubscribedUserId());
							ps.setString(2, t.getTransactionUniqueId());
							ps.setInt(3, t.getPackId());
							ps.setString(4, t.getInterfacee());
							ps.setString(5, t.getBillerId());
							ps.setInt(6, t.getHitId());			            
							ps.setString(7, t.getPublisher());
							ps.setString(8, t.getAdnetworkId());
							ps.setString(9, t.getProductId());
							ps.setFloat(10, t.getRequestedPrice());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
			}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_transaction: "+e);
			return 0;
		}
	}
	
	public int updateMsisdn(Transaction t) {
		try {
			logger.info("updating cg in table com_transaction");
			String query="update com_transaction set session_id='"+t.getSessionId()+"',msisdn='"+t.getMsisdn()+"' where transaction_id="+t.getTransactionId();
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
		return 1;
		}catch(Exception e) {
			System.out.println("exception while updating cg in table com_transaction table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateBillingTransaction(Transaction t) {
		try {
			logger.info("updating after callback in table com_transaction");
			String query="update com_transaction set session_id='"+t.getSessionId()+"', requested_price="+t.getRequestedPrice()+",billing_status='PENDD' where billing_status='INT' and msisdn='"+t.getMsisdn()+"' and biller_id='"+propertiesReader.getBp()+"' and publisher='"+propertiesReader.getPublisher()+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated billing transaction");
			return 1;
		}catch(Exception e) {
			logger.info("exception while updating billing transaction in table com_transaction: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateChargeAmmountAndBillingStatus(String msisdn,String serviceId) {
		try {
			Packs cp = packsDao.getPackDetails(serviceId);
			logger.info("updating billing status and price deducted in com_transaction table");
			String query="update com_transaction set price_deducted='"+cp.getPrice()+"', billing_status='DONE'"+"where msisdn='"+msisdn+"' and biller_id='"+propertiesReader.getBp()+"' and publisher='"+propertiesReader.getPublisher()+"' order by transaction_id desc limit 1";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated transaction billing status and price deducted");
			return 1;
		}catch(Exception e) {
			logger.info("exception while updating billing status and price deducted in com_transaction Table : "+e);
			e.printStackTrace();
			return 0;
		}
	}
	public int updateCgStatusDetails(String sessionId) {
		try {
			logger.info("updating cg status in com_transaction Table");
			String query="update com_transaction set cg_status='REJECTED' where session_id='"+sessionId+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated cg status in com_transaction table");
			return 1;
		} catch (Exception e) {
			logger.info("exception while updating cg status in com_transaction table : "+e);
			e.printStackTrace();
			return 0;
		}
	}
	public int updateBillingStatusDetails(String sessionId) {
		try {
			logger.info("updating billing status in com_transaction table");
			String query="update com_transaction set billing_status='FAIL', billing_response='REJECTED', billing_response_time=CURRENT_TIMESTAMP() where session_id='"+sessionId+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated billing status in com_transaction table");
			return 1;
		} catch (Exception e) {
			logger.info("exception while updating billing status and response in com_transaction table : "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public Integer getUserHitId(Integer trxId) {
		String query ="select hit_id from com_transaction where transaction_id="+trxId;
		logger.info("query"+query);
		 return jdbcTemplate.query(query,new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer userHitId=null;
				while(rs.next()) {
					userHitId= rs.getInt("hit_id");
				}
				return userHitId;
			}  
		    });  
	}

}
