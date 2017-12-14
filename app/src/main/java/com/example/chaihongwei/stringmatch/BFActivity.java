package com.example.chaihongwei.stringmatch;

import android.util.Log;

/**
 * 暴力检索
 */
public class BFActivity extends BaseActivity {
    @Override
    protected void textMatch(String originText, String keyword) {
        bfMatch(originText, keyword);
    }

    /**
     * 暴力检索，输出匹配到的关键字的起始索引（包括起始索引在内，因为有的是不包括结尾下标在内，所以这里说明一下）
     *
     * @param originText 原始文字
     * @param keyword    要匹配的关键字
     */
    private void bfMatch(String originText, String keyword) {
        char originChar;
        for (int i = 0; i < originText.length(); i++) {
            for (int j = 0; j < keyword.length(); j++) {
                //已经匹配到源字符串的末尾
                if (i + j >= originText.length()) {
                    Log.d(TAG, "匹配失败:i=" + i + ",j=" + j + "\n");
                    break;
                }

                //获取到要和关键字中的当前字符进行匹配的元字符串中的字符
                originChar = originText.charAt(i + j);

                if (originChar != keyword.charAt(j)) {
                    break;
                }

                //匹配到关键字的最后一个字符,代表匹配成功
                if (j == keyword.length() - 1) {
                    Log.d(TAG, "找到匹配字符串，起始：" + i
                            + " 终止：" + (i + keyword.length() - 1));

                    return;
                }
            }
        }
    }

    /**
     * 查找源字符串中从startIndex开始的子字符串
     *
     * @param originText 源字符串
     * @param startIndex 搜索起始位置
     * @param keyword    子字符串
     * @return 成功返回子字符串首字符的位置, 失败返回-1
     */
    private int bfMatch(String originText, int startIndex, String keyword) {
        int i = startIndex, j = 0, resultIndex;

        int kewwordLength = keyword.length();
        while (i < originText.length() && j < kewwordLength) {
            if (originText.charAt(i) == keyword.charAt(j)) {
                i++;
                j++;
            } else {
                /**
                 * cddcdc
                 *   cdc
                 */

                /**
                 * abacabab
                 *   abab
                 */

                i = i - j + 1;
                j = 0;
            }
        }

        if (j == kewwordLength) {
            resultIndex = i - kewwordLength;
        } else {
            resultIndex = -1;
        }

        return resultIndex;
    }
}
