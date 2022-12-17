package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Category;

public class AddCategory extends JFrame{
	// ���
	private JPanel jPanel = new JPanel();
	// ��ǩ
	private JLabel jLabel = new JLabel("�������");
	// �ı���
	private JTextField field = new JTextField(22);
	// ����
	private Font font2 = new Font("����", Font.BOLD, 22);
	private Font font3 = new Font("����", Font.BOLD, 18);
	// ��ť �޸�
	private JButton button = new JButton("ȷ    ��");
	

	public AddCategory() {
		setSize(400, 450);
		setTitle("���ͼ�����");
		// �ı䱳��ͼƬ
		Icon i = new ImageIcon("img\\ah.jpg");
		JLabel Label = new JLabel(i);
		Label.setBounds(0, 0, 400, 100);

		jLabel.setFont(font2);
		field.setFont(font2);
		button.setFont(font3);

		jLabel.setBounds(50, 180, 100, 30);
		field.setBounds(150, 180, 185, 28);
		
		button.setBounds(47, 270, 288, 35);
		

		//����¼�
		addEvent();
		
		jPanel.add(jLabel);
		jPanel.add(field);
		jPanel.add(button);
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 600, 400);
		jPanel.setOpaque(false);
		add(jPanel);
		add(Label);
		// �����Ըı䴰��Ĵ�С
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}
	
	private void addEvent() {

		// ���ȷ����ť�¼�
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = field.getText().trim();
				if(Category.addcategory(s)) {
					JOptionPane.showMessageDialog(null, "�������");
				}
			}
		});
	}

}
