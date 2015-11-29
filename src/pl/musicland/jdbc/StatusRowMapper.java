package pl.musicland.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pl.musicland.model.Status;


public class StatusRowMapper implements RowMapper<Status> {
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		Status status = new Status();
		status.setStatusid(rs.getInt("statusid"));
		status.setStatus(rs.getString("status"));
		return status;
	}
}
