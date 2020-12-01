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

@Component
public class OtpVerificationDao {

	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertInTable(String msisdn, String authCode, String status,String productId) {
		try {
			logger.info("inserting into otp_verification table");
			String query="insert into otp_verification(msisdn,otp,otp_status,product_id)"
					+ " values(?,?,?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(query, new String[] {"id"});
			            ps.setString(1, msisdn);
			            ps.setString(2, authCode);
			            ps.setString(3, status);
			            ps.setString(4, productId);
			            return ps;
			        }
			    },
			    keyHolder);
		}
		catch(Exception e) {
			System.out.println("exception while inserting in otp_verification table: "+e);
			e.printStackTrace();
		}
		
	}

}
