package hou.just4fun.ExamSystem.model;

import hou.just4fun.ExamSystem.util.IntegerUtil;
import hou.just4fun.ExamSystem.util.NumberUtil;

/**
 * @author houweitao
 * @date 2016年3月20日上午12:59:01
 */

public class Number {
	private int fenmu;
	private int fenzi;
	private NumberType type = NumberType.postive;

	public Number(int num) {
		fenmu = 1;
		fenzi = num;
		if (num < 0)
			type = NumberType.negative;
	}

	public Number(int fenzi, int fenmu) {
		this.fenmu = fenmu;
		this.fenzi = fenzi;
		if (fenzi * fenmu > 0) {
			type = NumberType.postive;
			this.fenzi = Math.abs(fenzi);
			this.fenmu = Math.abs(fenmu);
			this.type = NumberType.postive;
		}
		if (fenzi * fenmu < 0) {
			// System.out.println(this + " negative");
			type = NumberType.negative;
			if (fenzi > 0) {
				this.fenzi = -fenzi;
				this.fenmu = Math.abs(fenmu);
			}
			this.type = NumberType.negative;
		}
		judgeLeagl();
		adjustSelf();
	}

	// random
	public Number(int limit, boolean random) {
		double r2 = Math.random();
		fenmu = (int) (r2 * limit) + 1;

		double r1 = Math.random();
		fenzi = (int) (r1 * (fenmu * (limit - 1)));

//		System.out.println(fenzi + "," + fenmu);

		Number n = new Number(fenzi, fenmu);
		this.fenmu = n.getFenmu();
		this.fenzi = n.getFenzi();
		this.type = n.getType();
	}

	public Number(boolean random) {
		Number n = new Number(10, fenmu);
		this.fenmu = n.getFenmu();
		this.fenzi = n.getFenzi();
		this.type = n.getType();
	}

	// public Number(String str) {
	// if (!str.contains("/")) {
	// fenzi = Integer.valueOf(str);
	// fenmu = 1;
	// } else {
	// int pos = str.indexOf('/');
	// fenzi = Integer.valueOf(str.substring(0, pos));
	// fenmu = Integer.valueOf(str.substring(pos + 1));
	//
	// judgeLeagl();
	// adjustSelf();
	// }
	// }

	private void judgeLeagl() {
		if (fenmu == 0) {
			System.out.println("error");
			System.exit(0);
		}
	}

	private void adjustSelf() {
		// System.out.println("fenzi " + fenzi + " fenmu " + fenmu);
		// System.out.println(this.toString());
		judgeLeagl();

		if (fenzi == 0)
			fenmu = 1;
		else {
			int maxDivsor = IntegerUtil.maxCommonDivisor(fenzi, fenmu);
			fenzi = fenzi / maxDivsor;
			fenmu = fenmu / maxDivsor;
		}
	}

	@Override
	public String toString() {
		if (fenzi == 0)
			return 0 + "";
		if (fenmu == 1)
			return fenzi + "";
		else
			return fenzi + "$" + fenmu;
	}

	public String zhenFenShuForm() {
		boolean isNegative = false;
		String ret = "";
		if (fenzi == 0)
			return toString();
		if (fenmu == 1)
			return toString();
		else if (Math.abs(fenzi) < fenmu) {
			return fenzi+"/"+fenmu;
		} else {
			if (fenzi < 0)
				isNegative = true;
			int intPart = 0;
			Number fenshuPart = new Number(0);

			if (isNegative) {
				intPart = -1 * fenzi / fenmu;
				fenshuPart = NumberUtil.subtraction(NumberUtil.multiply(this, new Number(-1)), new Number(intPart));
				ret = "-" + intPart + "'" + fenshuPart.fenzi+"/"+fenshuPart.fenmu;
			} else {
				intPart = fenzi / fenmu;
				fenshuPart = NumberUtil.subtraction(this, new Number(intPart));
				ret = intPart + "'" + fenshuPart.fenzi+"/"+fenshuPart.fenmu;
			}

			return ret;
		}
	}

	public int getFenmu() {
		return fenmu;
	}

	public void setFenmu(int fenmu) {
		this.fenmu = fenmu;
	}

	public int getFenzi() {
		return fenzi;
	}

	public void setFenzi(int fenzi) {
		this.fenzi = fenzi;
	}

	public NumberType getType() {
		return type;
	}

	public void setType(NumberType type) {
		this.type = type;
	}
}
