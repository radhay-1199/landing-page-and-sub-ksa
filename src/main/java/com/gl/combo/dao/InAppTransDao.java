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

import com.gl.combo.model.InAppTrans;

@Component
public class InAppTransDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertDetails(InAppTrans entity) {
		try {
			logger.info("Insert into in_app_trans table");
			String sql="Insert into in_app_trans(action,msisdn,interface,response,biller_id) values(?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(sql, new String[] {"id"});
							ps.setString(1,entity.getAction());
							ps.setString(2,entity.getMsisdn());
							ps.setString(3,entity.getInterfacee());
							ps.setString(4,entity.getResponse());
							ps.setString(5,entity.getBillerId());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
}
