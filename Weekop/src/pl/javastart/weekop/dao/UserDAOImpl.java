package pl.javastart.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.javastart.weekop.model.User;
import pl.javastart.weekop.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {
	
	private static final String CREATE_USER = "INSERT INTO user(username, email, password, is_active) "
			+ "VALUES(:username, :email, :password, :active);";
	
	private static final String READ_USER = "SELECT user_id, username, email, password, is_active "
			+ "FROM user WHERE user_id = :id;";
	private static final String READ_USER_BY_USERNAME = "SELECT user_id, username, email, password, is_active "
			+ "FROM user WHERE username = :username;";
			
	
	private NamedParameterJdbcTemplate template;
	
	public UserDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}
	
	
	@Override
	public User create(User user) {
		User resultUser = new User(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		int update = template.update(CREATE_USER, parameterSource, keyHolder);
		if (update > 1) {
			resultUser.setId(keyHolder.getKey().longValue());
			setPrivigiles(resultUser);
		}
		return null;
	}

	private void setPrivigiles(User resultUser) {
		final String userRoleQuery = "INSERT INTO user_role(username) VALUES(:username);";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(resultUser);
		template.update(userRoleQuery, parameterSource);
	}


	@Override
	public User read(Long primaryKey) {
		User resultUser = null;
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", primaryKey);
		resultUser = template.queryForObject(READ_USER, parameterSource, new UserRowMapper());
		return resultUser;
	}

	@Override
	public boolean update(User updateObject) {
		return false;
	}

	@Override
	public boolean delete(Long key) {
		return false;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		User resultUser = null;
		SqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
		resultUser = template.queryForObject(READ_USER_BY_USERNAME, parameterSource,new UserRowMapper());
		return resultUser;
	}
	
	private class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setEmail(resultSet.getString("email"));
			
			return null;
		}
		
	}

}
