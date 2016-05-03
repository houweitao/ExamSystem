package hou.just4fun.ExamSystem.model;

/**
 *@author houweitao
 *@date 2016年3月20日上午1:53:10
 */

//括号对
public class ParenthesesPair {
	public int begin;
	public int end;

	public ParenthesesPair(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public String toString() {
		return "from " + begin + " to " + end;
	}
}
