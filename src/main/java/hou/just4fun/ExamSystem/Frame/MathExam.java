package hou.just4fun.ExamSystem.Frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hou.just4fun.ExamSystem.model.Number;
import hou.just4fun.ExamSystem.work.CalculatorWithFenshu;
import hou.just4fun.ExamSystem.work.MakeSentence;
/**
 * @author houweitao
 * @date 2016年3月21日 上午10:18:52
 * @end 2016年3月21日11:47:57
 */

public class MathExam extends JFrame {

	boolean endExam = false;
	long begin;
	// 定义面板，并设置为网格布局，5行4列，组件水平、垂直间距均为3

	JPanel showArea = new JPanel(new GridLayout(5, 4, 3, 3));
	JPanel problemAere = new JPanel(new GridLayout(2, 2, 5, 2));
	JPanel in = new JPanel(new BorderLayout());
	JPanel out = new JPanel(new BorderLayout());
	JPanel nowRightNum = new JPanel(new BorderLayout());
	JPanel nowWrongNum = new JPanel(new BorderLayout());
	JPanel timePanel = new JPanel(new BorderLayout());
	JPanel remainPanel = new JPanel(new BorderLayout());

	JTextField problem = new JTextField(); // 定义文本框
	JTextField result = new JTextField(); // 定义文本框
	JLabel inLabel = new JLabel("问题：");
	JLabel outLabel = new JLabel("结果：");
//	Button random = new Button("random");
	Button beginExam = new Button("begin exam");

	JTextField rightNumber = new JTextField(); // 定义文本框
	JLabel rightLabel = new JLabel("right num：");
	JTextField wrongNumber = new JTextField(); // 定义文本框
	JLabel wrongLabel = new JLabel("wrong num：");
	JTextField time = new JTextField(); // 定义文本框
	JLabel timeAlready = new JLabel("consume time：");
	JTextField remainProblem = new JTextField(); // 定义文本框
	JLabel remain = new JLabel("remaining problems：");

	public static void main(String[] args) {
		MathExam mc = new MathExam("exam");
	}

	public MathExam(String s) {

		super(s); // 为窗体名称赋值

		setLayout(new BorderLayout()); // 定义窗体布局为边界布局
		problem.setEditable(false);
		rightNumber.setEditable(false);
		rightNumber.setText("0");
		wrongNumber.setEditable(false);
		wrongNumber.setText("0");
		time.setEditable(false);
		time.setText("0");
		remainProblem.setText("10");

		Button submit = new Button("submmit");
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String res = result.getText().replace(" ", "");
				String str = problem.getText();
				Number numberResult = new Number(0);
				if (res.equals("")) {
					JOptionPane.showMessageDialog(MathExam.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
//					return;
				} else {
					CalculatorWithFenshu calcutor = new CalculatorWithFenshu();
					boolean canCal = niceAnswer(res);
//							calcutor.parenthesesFit(str);
					if (canCal) {
						numberResult = calcutor.calculate("0+" + str);
//						result.setText(numberResult.zhenFenShuForm());

						if (numberResult.zhenFenShuForm().equals(res)) {
							String cur = rightNumber.getText();
							rightNumber.setText((Integer.valueOf(cur) + 1) + "");
						} else {
							String cur = wrongNumber.getText();
							wrongNumber.setText((Integer.valueOf(cur) + 1) + "");
						}
						result.setText("");
						MakeSentence ms = new MakeSentence();
						String pro = ms.make();
						problem.setText(pro);
					} else {
						JOptionPane.showMessageDialog(MathExam.this, "存在非法字符，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
					}
					
					
					int right = Integer.valueOf(rightNumber.getText());
					int wrong = Integer.valueOf(wrongNumber.getText());
					String consumeTime = time.getText();

					if (right + wrong == 10) {
						endExam = true;
						JOptionPane.showMessageDialog(MathExam.this,
								"考试结束！答对: " + right + " 答错: " + wrong + " 耗时： " + consumeTime, "啦啦啦",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
//					return;
				}
			}

			private boolean niceAnswer(String res) {
				res = res.replace(" ", "");
				for (int i = 0; i < res.length(); i++) {
					char c = res.charAt(i);
					int pos = c - '0';
					if (!(pos >= 0 && pos <= 9 || pos == -9 || c == '/'))
						return false;
				}

				return true;
			}

		});
		showArea.add(submit);

		Button btCal = new Button("calculator");
		btCal.setSize(3, 4);
		btCal.addActionListener(new countListener());
//		showArea.add(btCal);

		beginExam.addActionListener(new randomListener());

		nowRightNum.add(rightLabel, BorderLayout.WEST);
		nowRightNum.add(rightNumber, BorderLayout.CENTER);
		showArea.add(nowRightNum);

		nowWrongNum.add(wrongLabel, BorderLayout.WEST);
		nowWrongNum.add(wrongNumber, BorderLayout.CENTER);
		showArea.add(nowWrongNum);

		timePanel.add(timeAlready, BorderLayout.WEST);
		timePanel.add(time, BorderLayout.CENTER);
		showArea.add(timePanel);

		Button reExam = new Button("I want exam again!");
		reExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				beginExam.setEnabled(true);
				rightNumber.setText("0");
				wrongNumber.setText("0");
				problem.setText("");
				time.setText("0");
				endExam = true;
			}
		});
		showArea.add(reExam);

		in.add(inLabel, BorderLayout.WEST);
		in.add(problem, BorderLayout.CENTER);
		in.add(beginExam, BorderLayout.EAST);
		out.add(outLabel, BorderLayout.WEST);
		out.add(result, BorderLayout.CENTER);
		out.add(submit, BorderLayout.EAST);

		problemAere.add(in);
		problemAere.add(out);

		// 将文本框放置在窗体NORTH位置
		getContentPane().add(problemAere, BorderLayout.NORTH);
		// getContentPane().add(output, BorderLayout.SOUTH);

		// 将面板放置在窗体CENTER位置
		getContentPane().add(showArea, BorderLayout.CENTER);
		setVisible(true);

		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 让窗体居中显示
	}

	class randomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MakeSentence ms = new MakeSentence();
			String str = ms.make();
			problem.setText(str);
			beginExam.setEnabled(false);

			endExam = false;
			begin = System.currentTimeMillis();
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (!endExam) {
						long cost = (System.currentTimeMillis() - begin) / 1000;
						time.setText(cost + " 秒");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();

		}

	}

	class countListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String str = problem.getText();
			Number numberResult = new Number(0);
			if (str.equals("")) {
				JOptionPane.showMessageDialog(MathExam.this, "你没有输入数值，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				CalculatorWithFenshu calcutor = new CalculatorWithFenshu();
				boolean canCal = calcutor.parenthesesFit(str);
				if (canCal) {
					numberResult = calcutor.calculate("0+" + str);
					result.setText(numberResult.zhenFenShuForm());
				} else {
					JOptionPane.showMessageDialog(MathExam.this, "括号数量不匹配或者存在非法字符，请重新输入", "警告",
							JOptionPane.WARNING_MESSAGE);
				}
				return;
			}
			// tfResult.setText(String.valueOf(numberResult));
		}
	}
}
