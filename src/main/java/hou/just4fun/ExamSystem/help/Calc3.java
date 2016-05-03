package hou.just4fun.ExamSystem.help;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author houweitao
 * @date 2016年3月20日上午11:34:33
 */

public class Calc3 extends JFrame {

	private JLabel lbNum1;
	private JLabel lbNum2;
	private JLabel lbEquals;
	private JTextField tfNum1;
	private JTextField tfNum2;
	private JTextField tfResult;
	private JComboBox cbCalc;
	private JButton btCalc;
	private JButton btClear;
	private JButton btExit;
	private double result = 0;
	private int index = 0;
	private double num1;
	private double num2;

	public Calc3(String title) {
		super(title);
		lbNum1 = new JLabel("第一个数");
		lbNum2 = new JLabel("第二个数");
		lbEquals = new JLabel("=");

		tfNum1 = new JTextField(10);
		tfNum2 = new JTextField(10);
		tfResult = new JTextField(10);
		tfResult.setBackground(Color.cyan);

		String[] str = { "+", "-", "x", "/" };
		cbCalc = new JComboBox(str);
		cbCalc.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				index = cbCalc.getSelectedIndex();
			}

		});

		btCalc = new JButton("计算");
		btCalc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String str1 = tfNum1.getText();
				String str2 = tfNum2.getText();
				if (str1.equals("") || str2.equals("")) {
					if (str1.equals("")) {
						JOptionPane.showMessageDialog(Calc3.this, "第一个数为空，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (str2.equals("")) {
						JOptionPane.showMessageDialog(Calc3.this, "第二个数为空，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				try {
					num1 = Double.parseDouble(str1);
					num2 = Double.parseDouble(str2);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					tfNum1.setText("");
					tfNum2.setText("");
					tfResult.setText("");
					JOptionPane.showMessageDialog(Calc3.this, "输入不正确，请核对后重新输入", "警告", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}

				if (0 == index) {
					result = num1 + num2;
				} else if (1 == index) {
					result = num1 - num2;
				} else if (2 == index) {
					result = num1 * num2;
				} else if (3 == index) {
					result = num1 / num2;
				}
				tfResult.setText(String.valueOf(result));
			}

		});
		btClear = new JButton("清除");
		btClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfNum1.setText("");
				tfNum2.setText("");
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

		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(lbNum1);
		cp.add(tfNum1);
		cp.add(cbCalc);
		cp.add(lbNum2);
		cp.add(tfNum2);
		cp.add(lbEquals);
		cp.add(tfResult);
		cp.add(btCalc);
		cp.add(btClear);
		cp.add(btExit);

		setSize(550, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Calc3("我的计算机器");
	}

}
