package hou.just4fun.ExamSystem.help;

/**
 *@author houweitao
 *@date 2016年3月20日上午11:00:36
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hou.just4fun.ExamSystem.model.Number;
import hou.just4fun.ExamSystem.work.CalculatorWithFenshu;;

public class Calc extends JFrame {
	private JLabel lbNum1;
	private JTextField tfNUm1;
	private JTextField tfResult;
	private JButton btSimmit;
	private JButton btReset;
	private JButton btExit;
	private JPanel panel;
	private double num1;
	private double result;
	private Number numberResult;

	public Calc(String title) throws HeadlessException {
		super(title);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		lbNum1 = new JLabel("输入一个数" , JLabel.RIGHT);
		lbNum1.isVisible();
		tfNUm1 = new JTextField(15);
		tfResult = new JTextField(15);
		tfResult.setBackground(Color.yellow);
		btSimmit = new JButton("计算");
		btSimmit.addActionListener(new countListener());
		btReset = new JButton("重置");
		btReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tfNUm1.setText("");
				tfResult.setText("");
			}

		});
		btExit = new JButton("退出");
		btExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});

		panel.add(lbNum1);
		panel.add(tfNUm1);
		panel.add(btSimmit);
		panel.add(btReset);
		panel.add(btExit);
		panel.add(tfResult);
		cp.add(panel, BorderLayout.CENTER);
		setVisible(true);
		setSize(600, 150);
		setLocation(250, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calc("hou's calculator");
	}

	class countListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String str = tfNUm1.getText();
			if (str.equals("")) {
				JOptionPane.showMessageDialog(Calc.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				try {
					// num1 = Double.valueOf(str);
					// result = Math.pow(num1, 2);

					CalculatorWithFenshu calcutor = new CalculatorWithFenshu();
					numberResult = calcutor.calculate(str);

				} catch (NumberFormatException e) {
					e.printStackTrace();
					tfResult.setText("");
					JOptionPane.showMessageDialog(Calc.this, "你的输入有异常，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			// tfResult.setText(String.valueOf(numberResult));
			tfResult.setText(numberResult.zhenFenShuForm());
		}

	}
}