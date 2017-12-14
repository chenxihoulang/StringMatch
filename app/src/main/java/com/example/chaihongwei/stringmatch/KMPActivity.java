package com.example.chaihongwei.stringmatch;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * KMP算法
 * 参考文档:http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 */
public class KMPActivity extends BaseActivity {

    @Override
    protected void textMatch(String originText, String keyword) {
        kmpMatch(originText, keyword);
    }

    /**
     * 计算部分匹配值
     * keword:ABCDABD
     * <p>
     * －　"A"的前缀和后缀都为空集，共有元素的长度为0；
     * －　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
     * －　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     * －　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     * －　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
     * －　"ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；
     * －　"ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     */
    public static int[] calcPartMatch(String keyword) {
        int[] partMatchVal = new int[keyword.length()];

        for (int i = 0; i < keyword.length(); i++) {
            if (i == 0) {
                partMatchVal[0] = 0;

                continue;
            }

            String subKey = keyword.substring(0, i + 1);
            // 求前缀
            List<String> list1 = new ArrayList<>();
            for (int j = 1; j < subKey.length(); j++) {
                list1.add(subKey.substring(0, j));
            }

            // 求后缀
            List<String> list2 = new ArrayList<>();
            for (int j = 1; j < subKey.length(); j++) {
                list2.add(subKey.substring(j, subKey.length()));
            }

            Log.d(TAG, "\ni = " + i);

            for (String s : list1) {
                Log.d(TAG, "前缀：" + s);
            }

            for (String s : list2) {
                Log.d(TAG, "后缀：" + s);
            }

            // 求交集
            list1.retainAll(list2);
            if (list1.size() == 0) {
                partMatchVal[i] = 0;
            } else {
                partMatchVal[i] = list1.get(0).length();
            }

            Log.d(TAG, "\n长度为：" + partMatchVal[i]);
        }

        return partMatchVal;
    }

    /**
     * 具体的操作流程就是：
     * 1.计算 keyword 的部分匹配值
     * 2.进行匹配操作，碰到部分匹配成功，下一次 起始点索引 = 原位置 + 已匹配的字符数 - 对应的部分匹配值
     */
    public static void kmpMatch(String originText, String keyword) {
        // 部分匹配值
        int[] partMatch = calcPartMatch(keyword);

        for (int i = 0; i < originText.length(); ) {
            // 已匹配字符数
            int count = 0;
            for (int j = 0; j < keyword.length(); j++) {
                if (i + j >= originText.length()) {
                    break;
                }
                char c = originText.charAt(i + j);
                if (c != keyword.charAt(j)) {
                    break;
                }
                count++;
                if (j == keyword.length() - 1) {
                    Log.d(TAG, "找到匹配字符串，起始：" + i +
                            " 终止：" + (i + keyword.length() - 1));
                }
            }

            if (count == 0) {
                i++;
            } else {
                //碰到部分匹配成功，下一次 起始点索引 = 原位置 + 已匹配的字符数 - 对应的部分匹配值
                i += count - partMatch[count - 1];
            }

            if (i > originText.length()) {
                break;
            }
        }
    }
}
