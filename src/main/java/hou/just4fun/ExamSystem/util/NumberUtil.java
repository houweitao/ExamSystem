package hou.just4fun.ExamSystem.util;

import hou.just4fun.ExamSystem.model.Number;;
/**
 * @author houweitao
 * @date 2016年3月20日上午12:55:44
 */

public class NumberUtil {

	public static void main(String[] args) {
		Number m = new Number(-2, 6);
		Number n = new Number(3, -4);
		System.out.println(m);
		System.out.println(n);
		System.out.println(add(m, n));
		System.out.println(subtraction(m, n));
		System.out.println(multiply(m, n));
		System.out.println(divide(m, n));
	}

//	private void adjustSelf(Number n) {
//		int fenzi = n.getFenzi();
//		int fenmu = n.getFenmu();
//		judgeLeagl(n);
//
//		if (fenzi == 0)
//			fenmu = 1;
//		else {
//			int maxDivsor = IntegerUtil.maxCommonDivisor(fenzi, fenmu);
//			fenzi = fenzi / maxDivsor;
//			fenmu = fenmu / maxDivsor;
//		}
//	}

//	private void judgeLeagl(Number n) {
//		if (n.getFenmu() == 0) {
//			System.out.println("error");
//			System.exit(0);
//		}
//	}

	public static Number add(Number m, Number n) {
		int fenmu = IntegerUtil.minCommonMultiple(m.getFenmu(), n.getFenmu());
		int fenzi = m.getFenzi() * fenmu / m.getFenmu() + n.getFenzi() * fenmu / n.getFenmu();
		return new Number(fenzi, fenmu);
	}

	public static Number subtraction(Number m, Number n) {
		int fenmu = IntegerUtil.minCommonMultiple(m.getFenmu(), n.getFenmu());
		int fenzi = m.getFenzi() * fenmu / m.getFenmu() - n.getFenzi() * fenmu / n.getFenmu();
		return new Number(fenzi, fenmu);
	}

	public static Number multiply(Number m, Number n) {
		if (m.getFenzi() == 0 || n.getFenzi() == 0)
			return new Number(0);

		int fenmu = m.getFenmu() * n.getFenmu();
		int fenzi = m.getFenzi() * n.getFenzi();
		return new Number(fenzi, fenmu);
	}

	public static Number divide(Number m, Number n) {
		if (m.getFenzi() == 0)
			return new Number(0);
		if (n.getFenzi() == 0) {
			System.exit(0);
			return null;
		}

		int fenmu = m.getFenmu() * n.getFenzi();
		int fenzi = m.getFenzi() * n.getFenmu();
		return new Number(fenzi, fenmu);
	}
}
