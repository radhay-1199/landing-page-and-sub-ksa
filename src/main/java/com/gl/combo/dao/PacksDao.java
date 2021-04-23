package com.gl.combo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gl.combo.bean.Packs;
import com.gl.combo.configuration.Values;

@Component
public class PacksDao {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Values propertiesReader;
	
	public Packs getPackDetails(int serviceId) {
		try {
			logger.info("Getting pack details for service id: "+serviceId);
			String query="select * from com_packs where service_id='"+serviceId+"' and publisher='"+propertiesReader.getPublisher()+"'";
			logger.info("Query: "+query);
			return jdbcTemplate.query(query, new ResultSetExtractor<Packs>() {
				@Override
				public Packs extractData(ResultSet rs) throws SQLException, DataAccessException {
					Packs packDetails = new Packs();
					while(rs.next()) {
						packDetails.setPackId(rs.getInt("pack_id"));
						packDetails.setName(rs.getString("name"));
						packDetails.setBillerId(rs.getString("biller_id"));
						packDetails.setProductId(rs.getString("product_id"));
						packDetails.setDuration(rs.getString("duration"));
						packDetails.setPrice(rs.getString("price"));
						packDetails.setPublisher(rs.getString("publisher"));
						packDetails.setServiceId(rs.getString("service_id"));
						packDetails.setServiceNode(rs.getString("service_node"));
					}
					return packDetails;
				}
			});
		} catch (Exception e) {
			logger.info("Exception while getting pack details: "+e);
			return null;
		}
	}
}
