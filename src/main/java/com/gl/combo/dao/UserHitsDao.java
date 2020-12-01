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

import com.gl.combo.bean.UserHits;



@Component
public class UserHitsDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertdetails(UserHits userhit) {
		try {
			logger.info("inserting into table com_user_hits");
			String query="insert into com_user_hits(ip_address,interface,pack_id,date,user_agent,biller_id,publisher,msisdn)"
					+ " values(?,?,?,"+userhit.getDate()+",?,?,?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(query, new String[] {"id"});
			            ps.setString(1, userhit.getIpAddress());
			            ps.setString(2, userhit.getInterfacee());
			            ps.setInt(3, userhit.getPackId());
			            ps.setString(4, userhit.getUserAgent());			            
			            ps.setString(5, userhit.getBillerId());			            
			            ps.setString(6, userhit.getPublisher());
			            ps.setString(7,"");
			            return ps;
			        }
			    },
			    keyHolder);
			
			
			/*
			 * jdbcTemplate.
			 * execute("insert into com_user_hits(ip_address,interface,pack_id,date,user_agent,biller_id,publisher)"
			 * +
			 * " values('"+userhit.getIpAddress()+"','"+userhit.getInterfacee()+"',"+userhit
			 * .getPackId()+","+userhit.getDate()+",'"+userhit.getUserAgent()+"','"+userhit.
			 * getBillerId()+"','"+userhit.getPublisher()+"')");
			 */
			return Integer.parseInt(""+keyHolder.getKey());
		}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_user_hits: "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public int updateMSISDN(int hitId, String msisdn) {
			try {
				logger.info("updating MSISDN in com_user_hits");
				String query="update com_user_hits set msisdn='"+msisdn+"' where hit_id="+hitId;
				logger.info("query: "+query);
				jdbcTemplate.execute(query);
				return 1;
			} catch (Exception e) {
				System.out.println("exception while updating msisdn in table com_user_hits table: "+e);
				e.printStackTrace();
				return 0;
			}
	}
	
}

