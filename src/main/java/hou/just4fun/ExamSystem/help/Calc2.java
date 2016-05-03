package hou.just4fun.ExamSystem.help;

/**
 *@author houweitao
 *@date 2016年3月20日上午11:33:35
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calc2 extends JFrame {
	private JLabel lbNum1;
	private JLabel lbNum2;
	private JTextField tfNUm1;
	private JTextField tfNUm2;
	private JLabel tfPlus;
	private JLabel tfEquals;
	private JTextField tfResult;
	private JButton btSimmit;
	private JButton btReset;
	private JPanel panel;
	private double num1;
	private double result;

	public Calc2(String title) throws HeadlessException {
		super(title);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		lbNum1 = new JLabel("输入一个数");
		lbNum2 = new JLabel("输入一个数");
		tfPlus = new JLabel("输入一个数");
		tfEquals = new JLabel("输入一个数");
		tfNUm1 = new JTextField(15);
		tfNUm2 = new JTextField(15);
		tfResult = new JTextField(15);
		tfResult.setBackground(Color.yellow);
		btSimmit = new JButton("计算");
		btSimmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String str = tfNUm1.getText();
				if (str.equals("")) {
					JOptionPane.showMessageDialog(Calc2.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						num1 = Double.valueOf(str);
						result = Math.pow(num1, 2);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						tfResult.setText("");
						JOptionPane.showMessageDialog(Calc2.this, "你的输入有异常，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				tfResult.setText(String.valueOf(result));
			}

		});
		btReset = new JButton("重置");
		btReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tfNUm1.setText("");
				tfResult.setText("");
			}

		});
		panel.add(lbNum1);
		panel.add(tfNUm1);
		panel.add(btSimmit);
		panel.add(btReset);
		panel.add(tfResult);
		cp.add(panel, BorderLayout.CENTER);
		setVisible(true);
		setSize(600, 150);
		setLocation(250, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calc2("我的计算器");
	}

}