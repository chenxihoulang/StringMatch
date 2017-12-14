package com.example.chaihongwei.stringmatch;

/**
 * 这个算法还没有明白
 * 参考文档:http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
 */
public class BoyerMooreActivity extends BaseActivity {
    @Override
    protected void textMatch(String originText, String keyword) {

    }

    /**
     * ABCDAB
     * <p>
     * <p>
     * 算法匹配
     */
    public static int pattern(String pattern, String target) {
        int tLen = target.length();
        int pLen = pattern.length();

        if (pLen > tLen) {
            return -1;
        }

        int[] bad_table = buildBadTable(pattern);// 1,3,5,6,2,
        int[] good_table = buildGoodTable(pattern);// 1,8,5,10,11,12,13

        for (int i = pLen - 1, j; i < tLen; ) {
            System.out.println("跳跃位置：" + i);
            for (j = pLen - 1; target.charAt(i) == pattern.charAt(j); i--, j--) {
                if (j == 0) {
                    System.out.println("匹配成功，位置：" + i);
                    //                  i++;   // 多次匹配
                    //                  break;
                    return i;
                }
            }
            i += Math.max(good_table[pLen - j - 1], bad_table[target.charAt(i)]);
        }
        return -1;
    }

    /**
     * 构建坏字符规则表
     */
    public static int[] buildBadTable(String pattern) {
        final int table_size = 256;
        int[] bad_table = new int[table_size];
        int pLen = pattern.length();

        for (int i = 0; i < bad_table.length; i++) {
            //默认初始化全部为匹配字符串长度
            bad_table[i] = pLen;
        }
        for (int i = 0; i < pLen - 1; i++) {
            int k = pattern.charAt(i);
            bad_table[k] = pLen - 1 - i;
        }
        return bad_table;
    }

    /**
     * 构建好后缀规则表
     */
    public static int[] buildGoodTable(String pattern) {
        int pLen = pattern.length();
        int[] good_table = new int[pLen];
        int lastPrefixPosition = pLen;

        for (int i = pLen - 1; i >= 0; --i) {
            if (isPrefix(pattern, i + 1)) {
                lastPrefixPosition = i + 1;
            }
            good_table[pLen - 1 - i] = lastPrefixPosition - i + pLen - 1;
        }

        for (int i = 0; i < pLen - 1; ++i) {
            int slen = suffixLength(pattern, i);
            good_table[slen] = pLen - 1 - i + slen;
        }
        return good_table;
    }

    /**
     * 前缀匹配
     */
    private static boolean isPrefix(String pattern, int p) {
        int patternLength = pattern.length();
        for (int i = p, j = 0; i < patternLength; ++i, ++j) {
            if (pattern.charAt(i) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 后缀匹配
     */
    private static int suffixLength(String pattern, int p) {
        int pLen = pattern.length();
        int len = 0;
        for (int i = p, j = pLen - 1; i >= 0 && pattern.charAt(i) == pattern.charAt(j); i--, j--) {
            len += 1;
        }
        return len;
    }


}
