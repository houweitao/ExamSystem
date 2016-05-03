package hou.just4fun.ExamSystem.help;

/**
 *@author houweitao
 *@date 2016年3月20日上午10:50:58
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JFrameDemo01 extends JFrame implements ItemListener, ActionListener {

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JCheckBox cbRed;
	private JCheckBox cbGreen;
	private JCheckBox cbBlue;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JLabel lbChange;
	private Font font;
	private Color fgColor;
	private ButtonGroup bg;
	private int fontSize;
	private int r, g, b;

	public JFrameDemo01(String title) throws HeadlessException {
		super(title);
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());// 流式布局
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		Container panel = getContentPane();// 主布局容器
		panel.setLayout(new GridLayout(3, 1));// 网格布局，3行1列
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		cbRed = new JCheckBox("红色");// 定义复选框并注册事件监听器
		cbRed.addItemListener(this);
		cbGreen = new JCheckBox("绿色");
		cbGreen.addItemListener(this);
		cbBlue = new JCheckBox("蓝色");
		cbBlue.addItemListener(this);
		rb1 = new JRadioButton("16");// 定义单选框并注册事件监听器
		rb1.addActionListener(this);
		rb2 = new JRadioButton("24");
		rb2.addActionListener(this);
		rb3 = new JRadioButton("34");
		rb3.addActionListener(this);
		bg = new ButtonGroup();// 将多个单选框放置于同一个按钮组，可保证多选一
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		lbChange = new JLabel("请选择");// 定义一个标签
		fontSize = 12;
		font = new Font("宋体", Font.PLAIN, fontSize);// 定义一个字体对象
		fgColor = new Color(0, 0, 0);// 定义一个颜色对象
		lbChange.setFont(font);
		lbChange.setForeground(fgColor);
		panel1.add(cbRed);// 将复选框布局在panel1
		panel1.add(cbGreen);
		panel1.add(cbBlue);
		panel2.add(rb1);// 将单选按钮布局在panel2
		panel2.add(rb2);
		panel2.add(rb3);
		panel3.add(lbChange);// 将标签布局在panel3

		pack();// 根据控件占据总大小设置JFrame窗口大小
		setLocation(400, 200);// 设置窗口初始化位置
		setVisible(true);// 设置窗口可见
		addWindowListener(new WindowAdapter()// 设置点击窗口右上角的关闭按钮，关闭窗口同时终止当前进程，如不设置，窗口虽然关闭了，可程序仍在后台运行
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		new JFrameDemo01("JFrameDemo01");// 运行JFrame
	}

	@Override
	public void actionPerformed(ActionEvent e) {// 设置选择复选框的对应事件处理程序
		if (e.getSource() == rb1) {
			fontSize = 16;
			System.out.println("16");
		} else if (e.getSource() == rb2) {
			fontSize = 24;
			System.out.println("24");
		} else if (e.getSource() == rb3) {
			fontSize = 34;
			System.out.println("34");
		}

		font = new Font("宋体", Font.PLAIN, fontSize);
		lbChange.setFont(font);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {// 设置选择单选框的对应事件处理程序

		if ((JCheckBox) e.getItem() == cbRed) {
			if (e.getStateChange() == e.SELECTED) {
				r = 255;
			} else {
				r = 0;
			}

		}
		if ((JCheckBox) e.getItem() == cbGreen) {
			if (e.getStateChange() == e.SELECTED) {
				g = 255;
			} else {
				g = 0;
			}
		}
		if ((JCheckBox) e.getItem() == cbBlue) {
			if (e.getStateChange() == e.SELECTED) {
				b = 255;
			} else {
				b = 0;
			}
		}
		fgColor = new Color(r, g, b);
		lbChange.setForeground(fgColor);

	}

}
