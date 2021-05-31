package OneQuestionPerDay;

import org.junit.Test;

public class Solution2 {
	/**
	 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
	 * <p>
	 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
	 */
	@Test
	public void test1() {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE + 1);
		System.out.println(Integer.MAX_VALUE + 1 + 1);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MIN_VALUE + 1);
		int x = 4;
		x <<= 2;
		System.out.println(x);

	}

	public static boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
	}

	/**
	 *方法一：二进制表示中 11 的位置
	 * 思路与算法
	 * 如果 n 是 4 的幂，那么 n 的二进制表示中有且仅有一个 1，
	 * 并且这个 1 出现在从低位开始的第偶数个二进制位上（
	 * 这是因为这个 1 后面必须有偶数个 0）。这里我们规定最低位为第 0 位，
	 * 例如 n=16n=16 时，n 的二进制表示为
	 * (10000)_2
	 * (10000)2
	 * 唯一的 11 出现在第 44 个二进制位上，因此 nn 是 44 的幂。
	 * 由于题目保证了 nn 是一个 3232 位的有符号整数，
	 * 因此我们可以构造一个整数 \textit{mask}mask，它的所有偶数二进制位都是 00，所有奇数二进制位都是 11。
	 * 这样一来，我们将 nn 和 \textit{mask}mask 进行按位与运算，如果结果为 00，
	 * 说明 nn 二进制表示中的 11 出现在偶数的位置，否则说明其出现在奇数的位置。
	 * 根据上面的思路，\textit{mask}mask 的二进制表示为：
	 * \textit{mask} = (10101010101010101010101010101010)_2
	 * mask=(10101010101010101010101010101010)_2
	 * 我们也可以将其表示成 1616 进制的形式，使其更加美观：
	 * \textit{mask} = (\text{AAAAAAAA})_{16}
	 * mask=(AAAAAAAA)_16
	 */
}
