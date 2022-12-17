package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * ������
 * @author K.X
 * 
 * */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import database.Landing;


public class MainInterface extends JFrame{
	/*
	 * ѡ�     ������       ͼ���ѯ          ͼ��軹       �˺Ź���
	 * 
	 * */
	//ѡ�
	public  JTabbedPane jTabbedPane = new JTabbedPane();
	//���������
	private JPanel jPanel = new JPanel();
	//��ǩ
	private JLabel jLabel = new JLabel("��ӭ��½ͼ�����ϵͳ");
	// ����
	private Font font = new Font("����", Font.BOLD, 70);
	private Font font2 = new Font("����", Font.BOLD, 20);
	
	private Container con = getContentPane();
	public MainInterface(String user) {
		
		// �ı䴰��ͼ��
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("img\\top.jpg");
		setIconImage(image);
		
		// �ı䱳��ͼƬ
		Icon i = new ImageIcon("img\\Main.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 1200, 800);
		jPanel.setLayout(null);
		jLabel.setFont(font);
		
		jLabel.setBounds(230, 50, 1000,200);
		jLabel.setForeground(Color.yellow);
		jPanel.add(jLabel);
		jPanel.add(Label);
		
		
		
		jTabbedPane.setFont(font2);
		jTabbedPane.add("�� �� ��", jPanel);
		BookSearch search = new BookSearch();
		jTabbedPane.add("ͼ���ѯ",search.jLayeredPane);
		
		BorrowingReturning returning = new BorrowingReturning();
		returning.setUser(user);
		returning.setModel(search.model);
		jTabbedPane.add("ͼ��軹", returning.jLayeredPane);
		
		
		if(Landing.sureadmin(user)) {
			Admin admin = new Admin();
			admin.setUser(user);
			admin.setFrame(this);
			jTabbedPane.add("�˻�����", admin.jPanel2);
			
			BookAdmin bookAdmin = new BookAdmin();
			bookAdmin.setModel(search.model);
			jTabbedPane.add("ͼ�����",bookAdmin.jPanel2);
			
		}else {
			AccountManagement management = new AccountManagement();
			management.setUser(user);
			management.setFrame(this);
			jTabbedPane.add("�˻�����", management.jPanel2);
		}
		
		
		
		con.add(jTabbedPane);
		// �����Ըı䴰��Ĵ�С
		setResizable(false);
		setTitle("ͼ�����ϵͳ");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
