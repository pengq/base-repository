package OneQuestionPerDay;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution4 {
	/**
	 * 给你一个整数数组 nums 和一个整数 k ，
	 * 编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
	 * 子数组大小 至少为 2 ，且
	 * 子数组元素总和为 k 的倍数。
	 * 如果存在，返回 true ；否则，返回 false 。
	 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
	 */
	@Test
	public void test1() {

	}
/**
 * 朴素的思路是遍历数组 \textit{nums}nums 的每个大小至少为 22 的子数组并计算每个子数组的元素和，判断是否存在一个子数组的元素和为 kk 的倍数。当数组 \textit{nums}nums 的长度为 mm 时，上述思路需要用 O(m^2)O(m
 * 2
 *  ) 的时间遍历全部子数组，对于每个子数组需要 O(m)O(m) 的时间计算元素和，因此时间复杂度是 O(m^3)O(m
 * 3
 *  )，会超出时间限制，因此必须优化。
 *
 * 如果事先计算出数组 \textit{nums}nums 的前缀和数组，则对于任意一个子数组，都可以在 O(1)O(1) 的时间内得到其元素和。用 \textit{prefixSums}[i]prefixSums[i] 表示数组 \textit{nums}nums 从下标 00 到下标 ii 的前缀和，则 \textit{nums}nums 从下标 p+1p+1 到下标 qq（其中 p<qp<q）的子数组的长度为 q-pq−p，该子数组的元素和为 \textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p]。
 *
 * 如果 \textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p] 为 kk 的倍数，且 q-p \ge 2q−p≥2，则上述子数组即满足大小至少为 22 且元素和为 kk 的倍数。
 *
 * 当 \textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p] 为 kk 的倍数时，\textit{prefixSums}[p]prefixSums[p] 和 \textit{prefixSums}[q]prefixSums[q] 除以 kk 的余数相同。因此只需要计算每个下标对应的前缀和除以 kk 的余数即可，使用哈希表存储每个余数第一次出现的下标。
 *
 * 规定空的前缀的结束下标为 -1−1，由于空的前缀的元素和为 00，因此在哈希表中存入键值对 (0,-1)(0,−1)。对于 0 \le i<m0≤i<m，从小到大依次遍历每个 ii，计算每个下标对应的前缀和除以 kk 的余数，并维护哈希表：
 *
 * 如果当前余数在哈希表中已经存在，则取出该余数在哈希表中对应的下标 \textit{prevIndex}prevIndex，\textit{nums}nums 从下标 \textit{prevIndex}+1prevIndex+1 到下标 ii 的子数组的长度为 i-\textit{prevIndex}i−prevIndex，该子数组的元素和为 kk 的倍数，如果 i-\textit{prevIndex} \ge 2i−prevIndex≥2，则找到了一个大小至少为 22 且元素和为 kk 的倍数的子数组，返回 \text{true}true；
 *
 * 如果当前余数在哈希表中不存在，则将当前余数和当前下标 ii 的键值对存入哈希表中。
 *
 * 由于哈希表存储的是每个余数第一次出现的下标，因此当遇到重复的余数时，根据当前下标和哈希表中存储的下标计算得到的子数组长度是以当前下标结尾的子数组中满足元素和为 kk 的倍数的子数组长度中的最大值。只要最大长度至少为 22，即存在符合要求的子数组。

 */
	/**
	 * 【同余定理】 【哈希表】【简化前缀和】
	 * 同余定理：如果两个整数m、n满足n-m能被k整除，那么n和m对k同余
	 * 即 ( pre(j) - pre (i) ) % k == 0 则 pre(j) % k == pre(i) % k
	 * 推导 => pre (i) % k = (a0 + a1 + ... + ai) % k = (a0 % k + a1 % k + ... ai % k ) % k
	 * （该推导在简化前缀和的时候有用，说明当前前缀和 % k 不会影响后面的前缀和 % k ）
	 */
	public boolean checkSubarraySum(int[] nums, int k) {
		int m = nums.length;
		if (m < 2) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int remainder = 0;
		for (int i = 0; i < m; i++) {
			remainder = (remainder + nums[i]) % k;
			if (map.containsKey(remainder)) {
				int prevIndex = map.get(remainder);
				if (i - prevIndex >= 2) {
					return true;
				}
			} else {
				map.put(remainder, i);
			}
		}
		return false;
	}
}
