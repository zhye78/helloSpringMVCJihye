package kr.ac.hansung.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Subject;

@Repository
public class SubjectDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sql = "select count(id) from jihyeSubject";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Subject> getSubjectBySemester() {
		String sqlStatement = "select year, semester, sum(score) from jihyeSubject group by year, semester";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Subject>() {
			
			@Override
			public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Subject subject = new Subject();
				subject.setScore(rs.getInt("sum(score)"));
				subject.setSemester(rs.getInt("semester"));
				subject.setYear((rs.getInt("year")));
				
				return subject;
			}
		});
	}
	
	public List<Subject> getSubjectBySelect(int year) {
		String sqlStatement = "select * from jihyeSubject where year=?";
		return jdbcTemplate.query(sqlStatement, new Object[] { year }, new RowMapper<Subject>() {
			
			@Override
			public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setScore(rs.getInt("score"));
				subject.setSemester(rs.getInt("semester"));
				subject.setCode(rs.getString("code"));
				subject.setName(rs.getString("name"));
				subject.setType(rs.getString("type"));
				subject.setYear((rs.getInt("year")));
				
				return subject;
			}
		});
	}
	
	public List<Subject> getSubjectByType() {
		String sqlStatement = "select type, sum(score) from jihyeSubject group by type";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Subject>() {
			
			@Override
			public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Subject subject = new Subject();
				subject.setGrade(rs.getInt("sum(score)"));
				subject.setType(rs.getString("type"));
				
				return subject;
			}
		});
	}
	
	// query and return a multiple object
	public List<Subject> getSubjects(int year, int semester) {
		String sqlStatement = "select * from jihyeSubject where year=? and semester=?";
		return jdbcTemplate.query(sqlStatement, new Object[] { year, semester }, new RowMapper<Subject>() {
			
			@Override
			public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setScore(rs.getInt("score"));
				subject.setSemester(rs.getInt("semester"));
				subject.setCode(rs.getString("code"));
				subject.setName(rs.getString("name"));
				subject.setType(rs.getString("type"));
				subject.setYear((rs.getInt("year")));
				
				return subject;
			}
		});
	}

	public boolean insert(Subject subject) {
		
		int id = subject.getId();
		int score = subject.getScore();
		int semester = subject.getSemester();
		String code = subject.getCode();
		String name = subject.getName();
		String type = subject.getType();
		int year = subject.getYear();
		
		String sqlStatement = "insert into jihyeSubject (year, semester, code, name, type, score) values (?, ?, ?, ?, ?, ?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {year, semester, code, name, type, score}) == 1);
	}
}