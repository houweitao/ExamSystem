package hou.just4fun.ExamSystem.work;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import hou.just4fun.ExamSystem.model.ParenthesesPair;

/**
 * @author houweitao
 * @date 2016年3月20日上午1:51:52
 */

public class CalculatorWithoutFenshu {

	public static void main(String[] args) {
		// Number number = new Number("4/1");
		// System.out.println(number.toString());
		CalculatorWithoutFenshu ms = new CalculatorWithoutFenshu();
		System.out.println(ms.calculateNoFenshu("-1-(2-2*3-(2-33))+3/4"));
		// System.out.println(ms.calculate("2-2*3-(2-3)"));
		// System.out.println(ms.calculate("1*-2+-3"));

		// String str="0123456789s";
		// for(int i=0;i<str.length();i++){
		// System.out.println(ms.isNumber(str.charAt(i)));
		// }
		// String s="+-*/ ";
		// for(int i=0;i<s.length();i++){
		// System.out.println(s.charAt(i)-'0');
		// }

		String[] testCase = { "1/6 + 1/8", "25 - 3 * 4 - 2 / 2 + 89", "1/2 + 1/3 - 1/4", "5 - 4 ） * （3 +28）" };
		for (String test : testCase) {
			System.out.println(ms.calculateNoFenshu(test));
		}
	}

	boolean isNumber(char c) {
		if (c - '0' >= 0 && c - '0' < 10)
			return true;
		else
			return false;
	}

	public int calculateNoFenshu(String s) {
		int len;
		if (s == null || (len = s.length()) == 0)
			return 0;

		if (s.contains("(")) {
			s = dealNoFenshu(s);
		}

		if ((len = s.length()) == 0)
			return 0;

		System.out.println(s);

		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		boolean lastIsOperator = false;// 对应括号内容为负数的情况
		boolean adjustNum = false;// 如果有连续2个操作符，这2个操作符不都是‘-’，此时需要调整接下来的num的值
		for (int i = 0; i < len; i++) {
			if (i == 0)
				lastIsOperator = false;

			if (isNumber(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
				lastIsOperator = false;
			}
			if ((!isNumber(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
				if (lastIsOperator) {
					if (sign == '-')
						sign = '+';
					else
						adjustNum = true;
				} else {
					if (adjustNum) {
						adjustNum = false;
						num = -num;
					}

					if (sign == '-') {
						stack.push(-num);
					}
					if (sign == '+') {
						stack.push(num);
					}
					if (sign == '*') {
						stack.push(stack.pop() * num);
					}
					if (sign == '/') {
						stack.push(stack.pop() / num);
					}
					sign = s.charAt(i);
					num = 0;
					lastIsOperator = true;
				}
				// System.out.println(num + " " + sign);
			}
		}

		int re = 0;
		for (int i : stack) {
			re += i;
		}

		System.out.println(s + " : " + re);

		return re;
	}

	private String dealNoFenshu(String s) {

		// s = modify(s);

		List<ParenthesesPair> list = new LinkedList<>();
		HashMap<Integer, ParenthesesPair> record = new HashMap<>();
		String ret = "";

		int count = 0;
		boolean init = true;
		int begin = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				init = false;
				if (count == 0)
					begin = i;
				count++;
			} else if (s.charAt(i) == ')') {
				count--;
				if (count == 0) {
					end = i;
					list.add(new ParenthesesPair(begin, end));
					record.put(begin, new ParenthesesPair(begin, end));
				}
			}
			// if (count == 0 && !init) {
			// list.add(new ParenthesesPair(begin, end));
			// record.put(begin, new ParenthesesPair(begin, end));
			// }
		}

		for (int i = 0; i < s.length(); i++) {
			if (record.containsKey(i)) {
				ParenthesesPair pp = record.get(i);
				int cur = calculateNoFenshu(s.substring(pp.begin + 1, pp.end));
				System.out.println(s.substring(pp.begin + 1, pp.end) + " = " + cur);
				// if (cur < 0) {
				// ret = ret + cur * (-1);
				// } else
				ret = ret + cur;
				i = pp.end;
			} else {
				ret = ret + s.charAt(i);
			}
		}

		System.out.println(list);
		System.out.println("处理之后的结果：" + ret);

		return ret;

	}

}
