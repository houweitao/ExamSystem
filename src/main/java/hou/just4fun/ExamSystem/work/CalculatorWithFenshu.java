package hou.just4fun.ExamSystem.work;

import java.util.HashMap;
import java.util.Stack;

import hou.just4fun.ExamSystem.model.Number;
import hou.just4fun.ExamSystem.model.NumberType;
import hou.just4fun.ExamSystem.model.ParenthesesPair;
import hou.just4fun.ExamSystem.util.NumberUtil;

/**
 * @author houweitao
 * @date 2016年3月19日下午1:26:31
 * @basic 2016年03月20日02:26:31
 * @notBad 2016年03月20日11:57:18
 * @todo 0的正负判别。number 分母为负数？
 */

public class CalculatorWithFenshu {
	public static void main(String[] args) {
		CalculatorWithFenshu ms = new CalculatorWithFenshu();

		System.out.println(ms.dealDot("1-(2+2.03)+5$4"));

		String[] testCase = { "(2$3/3$4)", "3/3$2", "-1-(2-2*3-(2-33))+3/4", "1/6 + 1/8", "25 - 3 * 4 - 2 / 2 + 89",
				"1/2 + 1/3 - 1/4", "（5 - 4 ) * (3 +28)", "（5 - 4 ) * (3 +28-(3+2/3))+6-3", "()()()", "(3)", "1+2.03",
				"-1-(2-2*3-(2-3+0.0333*3/4))+3/4"

				// };
				, "2-2*3-(2-3+0.3*3/4)", "2-2*3-(2-3-0.3*(-3/4))", "(-3/4)", "(2-3+0.3)", "(2-3+0.3*(-3/4))",
				"(2-3-0.3*(-3/4))", "(2-3+1.3*(-3/4))", "(2-3-1.3*(-3/4))", "-2*-3/4" };

		// System.out.println(ms.deal("0.22-0.33"));

		for (String test : testCase) {
			// System.out.println(ms.dealDot(test));
			System.out.println(test + " : " + ms.calculate(test).zhenFenShuForm() + " , " + ms.calculate(test));
		}

		// Number number = new Number(-0);
		// System.out.println(number);
		//
		// System.out.println(ms.deal("1+0.2*(-3/4)"));
		// System.out.println(ms.calculate("1+0.2*(-3/4)"));
		// System.out.println(ms.deal("1-0.2*(-3/4)"));
		// System.out.println(ms.calculate("1-0.2*(-3/4)"));

		System.out.println(ms.dealStrWithDot("2.44"));
		System.out.println(('$' - '0'));
	}

	boolean isNumber(char c) {
		if (c - '0' >= 0 && c - '0' < 10)
			return true;
		else
			return false;
	}

	public Number calculate(String s) {
		int len;
		if (s == null || (len = s.length()) == 0)
			return new Number(0);
		s = s.replace("（", "(").replace("）", ")");
		if (!parenthesesFit(s)) {
			System.out.println("括号数量不匹配或者存在非法字符！");
			// System.exit(0);
			return new Number(0);
		}

		if (s.contains("(") || s.contains(")") || s.contains("（") || s.contains("）") || s.contains(".")) {
			// System.out.println("deal");
			s = deal(s);
			s = dealDot(s);
		}

		// System.out.println(s);

		if ((len = s.length()) == 0)
			return new Number(0);

		// System.out.println(s);

		Stack<Number> stack = new Stack<Number>();
		int num = 0;
		// Number lastNum = new Number(0);
		char lastOperator = 0;
		// int afterNumSize = 0;
		char sign = '+';
		boolean lastIsOperator = false;// 对应括号内容为负数的情况
		boolean adjustNum = false;// 如果有连续2个操作符，这2个操作符不都是‘-’，此时需要调整接下来的num的值
		for (int i = 0; i < len; i++) {
			if (i == 0)
				lastIsOperator = false;

			if (isNumber(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
				// afterNumSize++;
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

					if (s.charAt(i) == '.' || s.charAt(i) == '$') {
						// lastNum = stack.peek();
						lastOperator = sign;
					}

					if (sign == '-') {
						Number number = new Number(-num);
						number.setType(NumberType.negative);
						stack.push(number);
					}
					if (sign == '+') {
						stack.push(new Number(num));
					}
					if (sign == '*') {
						stack.push(NumberUtil.multiply(stack.pop(), new Number(num)));
					}
					if (sign == '/') {
						stack.push(NumberUtil.divide(stack.pop(), new Number(num)));
					}

					// mb
					// if (sign == '.') {
					// Number number = stack.pop();
					//
					// Number first = lastNum, second = null,
					// now = NumberUtil.divide(new Number(num), new Number((int)
					// Math.pow(10, afterNumSize)));
					// if (lastOperator == '-') {
					// stack.push(NumberUtil.subtraction(number, now));
					// } else if (lastOperator == '*') {
					// // first = lastNum;
					// second = NumberUtil.divide(number, first);
					// stack.push(NumberUtil.multiply(first,
					// NumberUtil.add(second, now)));
					// } else if (lastOperator == '/') {
					// // first = lastNum;
					// second = NumberUtil.multiply(number, first);
					// stack.push(NumberUtil.divide(first,
					// NumberUtil.add(second, now)));
					// } else {
					// if (number.getFenzi() > 0)
					// stack.push(NumberUtil.add(number, NumberUtil.divide(new
					// Number(num),
					// new Number((int) Math.pow(10, afterNumSize)))));
					// else if (number.getFenzi() < 0)
					// stack.push(NumberUtil.subtraction(number,
					// NumberUtil.divide(new Number(num),
					// new Number((int) Math.pow(10, afterNumSize)))));
					// else {
					// if (number.getType() == NumberType.postive)
					// stack.push(NumberUtil.divide(new Number(num),
					// new Number((int) Math.pow(10, afterNumSize))));
					// else
					// stack.push(NumberUtil.divide(new Number(num),
					// new Number((int) ((-1) * Math.pow(10, afterNumSize)))));
					//
					// }
					// }
					// }

					if (sign == '$') {
						Number number = stack.pop();

						// Number first = lastNum, second = null;
						Number now = new Number(num);
						if (lastOperator == '+') {
							// second = NumberUtil.subtraction(number, first);
							// Number cur = NumberUtil.divide(second, now);
							// stack.push(NumberUtil.add(first, cur));

							stack.push(NumberUtil.divide(number, now));
						} else if (lastOperator == '-') {
							// second = NumberUtil.subtraction(lastNum, number);
							// Number cur = NumberUtil.divide(second, now);
							// stack.push(NumberUtil.subtraction(first, cur));

							stack.push(NumberUtil.divide(number, now));
						} else if (lastOperator == '*') {
							stack.push(NumberUtil.divide(number, now));
						} else if (lastOperator == '/') {
							stack.push(NumberUtil.multiply(number, now));
						} else if (lastOperator == '$') {
							stack.push(NumberUtil.divide(number, now));
						}

					}

					sign = s.charAt(i);
					num = 0;
//					afterNumSize = 0;
					lastIsOperator = true;
				}
				// System.out.println(num + " " + sign);
			}
		}

		Number re = new Number(0);
		for (Number i : stack) {
			re = NumberUtil.add(re, i);
		}

		// System.out.println(s + " : " + re);

		return re;
	}

	public boolean parenthesesFit(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				count++;
			else if (s.charAt(i) == ')') {
				count--;
				if (count < 0)
					return false;
			} else {
				int pos = s.charAt(i) - '0';
				if (!(pos >= -3 && pos <= 9 || pos <= -5 && pos >= -8 || pos == -16 || pos == -12))
					return false;
			}
		}
		if (count == 0)
			return true;
		else
			return false;
	}

	private String deal(String s) {
		// s = modify(s);

		// List<ParenthesesPair> list = new LinkedList<>();
		HashMap<Integer, ParenthesesPair> record = new HashMap<>();
		HashMap<Integer, ParenthesesPair> dot = new HashMap<>();
		String ret = "";

		s = "0+" + s;

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
					// list.add(new ParenthesesPair(begin, end));
					record.put(begin, new ParenthesesPair(begin, end));
				}
			}
		}

		// System.out.println(dot.size());

		for (int i = 0; i < s.length(); i++) {
			if (record.containsKey(i)) {
				ParenthesesPair pp = record.get(i);
				Number cur = calculate("0+" + s.substring(pp.begin + 1, pp.end));
				// System.out.println(s.substring(pp.begin + 1, pp.end) + " = "
				// + cur.toString());
				ret = ret + cur.toString();
				i = pp.end;
			} else {
				ret = ret + s.charAt(i);
			}
		}

		// System.out.println(list);
		// System.out.println("处理之后的结果：" + ret);

		return ret;
	}

	private String dealDot(String s) {

		// s = modify(s);

		// List<ParenthesesPair> list = new LinkedList<>();
		HashMap<Integer, ParenthesesPair> dot = new HashMap<>();
		String ret = "";

		// s = "0+" + s;
		for (int i = 0; i < s.length(); i++) {
			// System.out.println(i);
			int from = 0, to = 0;

			if (s.charAt(i) == '.') {
				for (int j = i - 1; j >= 0; j--) {
					if (!(s.charAt(j) - '0' >= 0 && s.charAt(j) - '0' <= 9)) {
						from = j + 1;
						break;
					}
					from = j;
				}
				for (int j = i + 1; j < s.length(); j++) {
					if (!(s.charAt(j) - '0' >= 0 && s.charAt(j) - '0' <= 9)) {
						to = j - 1;
						break;
					}
					to = j;
				}
				dot.put(from, new ParenthesesPair(from, to));
				// System.out.println(from + "," + to);
				i = to - 1;
			}
		}

		for (int i = 0; i < s.length(); i++) {
			if (dot.containsKey(i)) {
				// System.out.println("yes");
				ParenthesesPair pp = dot.get(i);
				Number str = dealStrWithDot(s.substring(pp.begin, pp.end + 1));
				ret = ret + str.toString();
				i = Math.max(pp.end, i);
			} else {
				ret = ret + s.charAt(i);
			}
		}

		// System.out.println(list);
		// System.out.println("处理之后的结果：" + ret);

		return ret;

	}

	private Number dealStrWithDot(String str) {
		// System.out.println("do deal :" + str);
		int pos = -1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {
				pos = i;
				break;
			}
		}

		Number first = new Number(Integer.valueOf(str.substring(0, pos)));
		int len = str.substring(pos + 1).length();
		Number second = new Number(Integer.valueOf(str.substring(pos + 1)), (int) Math.pow(10, len));

		// System.out.println(str + " , " + NumberUtil.add(first, second));

		return NumberUtil.add(first, second);
	}
}
