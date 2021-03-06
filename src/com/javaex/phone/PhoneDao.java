package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String id = "phonedb";
	private String pw = "phonedb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private List<PersonVo> pList = new ArrayList<PersonVo>();

	private void connect() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public List<PersonVo> select(String keyword) {
		connect();
		try {
		    String query ="";
		    query = " select person_id, "+
		            		" name, "+
		            		" hp, "+
		            		" company "+
		    " from person "+
		    " where name like ? "+
		    " or hp like ? "+
		    " or company like ? "+
		    " order by person_id asc";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, '%' + keyword + '%' );
		    pstmt.setString(2, '%' + keyword + '%');
		    pstmt.setString(3, '%' + keyword + '%' );
		    
		    rs = pstmt.executeQuery();
		    pList.removeAll(pList);
		    while(rs.next()) {
		    	int person_id = rs.getInt("person_id");
		    	String name = rs.getString("name");
		    	String hp = rs.getString("hp");
		    	String company = rs.getString("company");
		    	pList.add(new PersonVo(person_id, name, hp, company));
		    }
		    
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
		
		return pList;
	}

	public int delete(int num) {
		connect();
		try {
		    String query = "delete from person where person_id = ?";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setInt(1, num);

		    int count = pstmt.executeUpdate();
		    
		    return count;

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
		return 0;
	}

	public int update(PersonVo vo) {
		
		connect();
		try {
		    String query = "update person set name = ?, hp = ?, company = ? where person_id = ?";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, vo.getName());
		    pstmt.setString(2, vo.getHp());
		    pstmt.setString(3, vo.getCompany());
		    pstmt.setInt(4, vo.getPerson_id());
		    // 4.결과처리
		   int count = pstmt.executeUpdate();
		   return count;

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
		return 0;
	}

	public int insert(PersonVo vo) {
		connect();
		try {

			String query = "insert into person values(seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getCompany());

			int count = pstmt.executeUpdate();
			return count;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return 0;
	}

}
