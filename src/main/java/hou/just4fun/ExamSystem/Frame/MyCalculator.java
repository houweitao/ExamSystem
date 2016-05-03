package hou.just4fun.ExamSystem.Frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hou.just4fun.ExamSystem.work.CalculatorWithFenshu;
import hou.just4fun.ExamSystem.work.MakeSentence;
import hou.just4fun.ExamSystem.model.Number;

/**
 * @author houweitao
 * @date 2016年3月20日下午2:51:36
 */

public class MyCalculator extends JFrame {

	// 定义面板，并设置为网格布局，5行4列，组件水平、垂直间距均为3
	private static final long serialVersionUID = 1L;

	JPanel p = new JPanel(new GridLayout(5, 4, 3, 3));
	JPanel up = new JPanel(new GridLayout(2, 2, 5, 2));
	JPanel in = new JPanel(new BorderLayout());
	JPanel out = new JPanel(new BorderLayout());

	JTextField input = new JTextField(); // 定义文本框
	JTextField output = new JTextField(); // 定义文本框
	JLabel inLabel = new JLabel("Input:");
	JLabel outLabel = new JLabel("Result:");
	Button random = new Button("Random Generate");

	// 注意字符元素的顺序与循环添加按钮保持一致
	private static String str[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "$", "+", "(",
			")" };

	public static void main(String[] args) {
		MyCalculator mc = new MyCalculator("我的计算器");
	}

	public MyCalculator(String s) {

		super(s); // 为窗体名称赋值

		setLayout(new BorderLayout()); // 定义窗体布局为边界布局

		JButton btn[]; // 声明按钮数组
		btn = new JButton[str.length]; // 创建按钮数组

		// 循环定义按钮，并添加到面板中
		for (int i = 0; i < str.length; i++) {
			btn[i] = new JButton(str[i]);
			btn[i].addActionListener(new inputListener(i));
			p.add(btn[i]);
		}

		Button btReset = new Button("Reset");
		btReset.setSize(3, 4);
		btReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				input.setText("");
				output.setText("");
			}

		});
		p.add(btReset);
		Button btCal = new Button("Calculate");
		btCal.setSize(3, 4);
		btCal.addActionListener(new countListener());
		p.add(btCal);

		random.addActionListener(new randomListener());

		in.add(inLabel, BorderLayout.WEST);
		in.add(input, BorderLayout.CENTER);
		in.add(random, BorderLayout.EAST);
		out.add(outLabel, BorderLayout.WEST);
		out.add(output);

		up.add(in);
		up.add(out);

		// 将文本框放置在窗体NORTH位置
		getContentPane().add(up, BorderLayout.NORTH);
		// getContentPane().add(output, BorderLayout.SOUTH);

		// 将面板放置在窗体CENTER位置
		getContentPane().add(p, BorderLayout.CENTER);
		setVisible(true);

		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 让窗体居中显示
	}

	class randomListener implements ActionListener {
		// String str = "";

		// randomListener() {
		// MakeSentence ms = new MakeSentence();
		// str = ms.make();
		// }

		@Override
		public void actionPerformed(ActionEvent e) {
			MakeSentence ms = new MakeSentence();
			String str = ms.make();
			// if (input.getText().length() == 0)
			input.setText(str);
			// else
			// input.setText("");
		}

	}

	class inputListener implements ActionListener {
		int pos = -1;
		char s;

		public inputListener(int pos) {
			this.pos = pos;
		}

		public inputListener(char c) {
			this.s = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (pos != -1)
				input.setText(input.getText() + str[pos]);
			else
				input.setText(input.getText() + s);
		}

	}

	class countListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String str = input.getText();
			Number numberResult = new Number(0);
			if (str.equals("")) {
				JOptionPane.showMessageDialog(MyCalculator.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				CalculatorWithFenshu calcutor = new CalculatorWithFenshu();
				boolean canCal = calcutor.parenthesesFit(str);
				if (canCal) {
					numberResult = calcutor.calculate("0+" + str);
					output.setText(numberResult.zhenFenShuForm());
				} else {
					JOptionPane.showMessageDialog(MyCalculator.this, "括号数量不匹配或者存在非法字符，请重新输入", "警告",
							JOptionPane.WARNING_MESSAGE);
				}
				return;
			}
			// tfResult.setText(String.valueOf(numberResult));
		}
	}
}
