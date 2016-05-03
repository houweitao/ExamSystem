package hou.just4fun.ExamSystem.help;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hou.just4fun.ExamSystem.model.Number;
import hou.just4fun.ExamSystem.work.CalculatorWithFenshu;

/**
 * @author houweitao
 * @date 2016年3月20日下午1:55:23
 */

public class GridFrame extends JFrame {

	// 定义面板，并设置为网格布局，4行4列，组件水平、垂直间距均为3

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel p = new JPanel(new GridLayout(5, 4, 3, 3));
	// JPanel up = new JPanel(new GridLayout());

	JTextField input = new JTextField(); // 定义文本框
	JTextField output = new JTextField(); // 定义文本框
	// Number numberResult = new Number(0);

	// 定义字符串数组，为按钮的显示文本赋值

	// 注意字符元素的顺序与循环添加按钮保持一致

	String str[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+", "(", ")" };

	public GridFrame(String s) {

		super(s); // 为窗体名称赋值

		setLayout(new BorderLayout()); // 定义窗体布局为边界布局

		// JTextField tfNUm1 = new JTextField(15);
		// up.add(tfNUm1);
		// Button button = new Button("ss");
		// up.add(button);

		JButton btn[]; // 声明按钮数组

		btn = new JButton[str.length]; // 创建按钮数组

		// 循环定义按钮，并添加到面板中

		for (int i = 0; i < str.length; i++) {
			btn[i] = new JButton(str[i]);
			btn[i].addActionListener(new inputListener(i));
			p.add(btn[i]);

		}

		Button btReset = new Button("重置");
		btReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				input.setText("");
				output.setText("");
			}

		});

		p.add(btReset);
		Button btCal = new Button("计算");
		btCal.addActionListener(new countListener());

		p.add(btCal);

		// 将文本框放置在窗体NORTH位置

		getContentPane().add(input, BorderLayout.NORTH);
		getContentPane().add(output, BorderLayout.SOUTH);

		// 将面板放置在窗体CENTER位置

		// getContentPane().add(p, BorderLayout.CENTER);

		// getContentPane().add(up);
		getContentPane().add(p);
		setVisible(true);

		setSize(250, 200);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null); // 让窗体居中显示

	}

	public static void main(String[] args) {

		GridFrame gl = new GridFrame("网格布局计算机！");

	}

	class inputListener implements ActionListener {
		int pos;

		public inputListener(int pos) {
			this.pos = pos;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			input.setText(input.getText() + str[pos]);
		}

	}

	class countListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String str = input.getText();
			Number numberResult = new Number(0);
			if (str.equals("")) {
				JOptionPane.showMessageDialog(GridFrame.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				try {
					// num1 = Double.valueOf(str);
					// result = Math.pow(num1, 2);

					CalculatorWithFenshu calcutor = new CalculatorWithFenshu();
					numberResult = calcutor.calculate(str);

				} catch (NumberFormatException e) {
					e.printStackTrace();
					output.setText("");
					JOptionPane.showMessageDialog(GridFrame.this, "你的输入有异常，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			// tfResult.setText(String.valueOf(numberResult));
			output.setText(numberResult.zhenFenShuForm());
		}

	}
}
