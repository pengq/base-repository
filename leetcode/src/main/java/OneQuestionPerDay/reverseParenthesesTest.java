package OneQuestionPerDay;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 */
public class reverseParenthesesTest {
	public static void main(String[] args) {
		System.out.println(reverseParentheses("123(456)789"));

	}
	public static String reverseParentheses(String s) {
		Deque<String> stack = new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(sb.toString());
				sb.setLength(0);
			} else if (ch == ')') {
				sb.reverse();
				sb.insert(0, stack.pop());
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
