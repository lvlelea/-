package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;


public class BorrowRecords {
	public BorrowRecords() {
	}
	//����ͼ��
	public static void Borrow(String user, int bookid, String bookname) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr = "insert into borrowrecords(user,bookid,bookname,borrowtime,returntime,status) values (?,?,?,now(),null,?)";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			preSql.setInt(2, bookid);
			preSql.setString(3, bookname);
			preSql.setString(4, "δ��");
			int ok = preSql.executeUpdate();
			con.close();
			ChangeBorrowState(bookid);
		} catch (SQLException e) {
		}
	}
	//����ͼ���޸�ͼ��״̬
	private static void ChangeBorrowState(int bookid) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr = "update booktable set state=? where bookid = ?";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, "���");
			preSql.setInt(2, bookid);
			
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	
	//����
	public static void Return(String user, int bookid) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;
		String sqlStr = "select now()";
		ResultSet rs;
		Date date=null;
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
				date = rs.getDate(1);
			}
			sqlStr = "update borrowrecords set returntime =  ? where bookid = ? and user = ? and status = ?";
			preSql = con.prepareStatement(sqlStr);
			preSql.setDate(1, date);
			preSql.setInt(2, bookid);
			preSql.setString(3, user);
			preSql.setString(4, "δ��");
			int ok = preSql.executeUpdate();
			
			sqlStr = "update borrowrecords set status = ? where bookid = ? and user = ? and status = ?";
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, "�ѻ�");
			preSql.setInt(2, bookid);
			preSql.setString(3, user);
			preSql.setString(4, "δ��");
			ok = preSql.executeUpdate();
			
			con.close();
			ChangeReturnState(bookid);
		} catch (SQLException e) {
		}
	}
	//����ͼ���޸�ͼ��״̬
	private static void ChangeReturnState(int bookid) {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr = "update booktable set state=? where bookid = ?";

		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, "�ڹ�");
			preSql.setInt(2, bookid);
			
			int ok = preSql.executeUpdate();
			con.close();
		} catch (SQLException e) {
		}
	}
	//�ȶԻ���
	public static boolean comparison(String user,int bookid) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from borrowrecords where bookid = ? and status = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setInt(1, bookid);
			preSql.setString(2, "δ��");
			rs = preSql.executeQuery();
			while (rs.next()) {
				String user2 = rs.getString(2);
				if(user2.equals(user)) {
					return true;
				}else {
					return false;
				}
			}
			con.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
}
