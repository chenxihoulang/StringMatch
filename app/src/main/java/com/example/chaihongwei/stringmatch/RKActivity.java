package com.example.chaihongwei.stringmatch;

import android.util.Log;

/**
 * 暴力检索的改进
 */
public class RKActivity extends BaseActivity {

    /**
     * RK 算法首先计算子串的哈希值，然后在原字符串中取出同样长度的字符串计算哈希值，如果二者的哈希值不等那么他们一定不同。
     * 如果哈希值相同，由于哈希冲突的存在，也需要再次比对一下是否相同。
     * 一般情况下我们需要匹配的文本含有的关键字占全文的数量应该不是很高，所以这种高效率去除不同的情况效率是高于 BF 的。
     */
    @Override
    protected void textMatch(String originText, String keyword) {
        rkMatch(originText, keyword);
    }

    /**
     * 暴力检索的改进，输出匹配到的关键字的起始索引（包括起始索引在内，因为有的是不包括结尾下标在内，所以这里说明一下）
     *
     * @param originText 原始文字
     * @param keyword    要匹配的关键字
     */
    private void rkMatch(String originText, String keyword) {
        //要匹配关键字的hash值
        int keyHash = keyword.hashCode();
        int keyLength = keyword.length();

        String subString;
        for (int i = 0; i < originText.length(); i++) {
            //如果原始字符串匹配的开始位置+关键字长度>源字符串长度,说明肯定匹配失败了
            if (keyLength + i >= originText.length()) {
                Log.d(TAG, "匹配失败:i=" + i + "\n");
                break;
            }

            //从i开始截取keyLength个字符
            subString = originText.substring(i, i + keyLength);
            //如果两个字符串的hash值相等,那么有肯能两个字符串相同
            if (subString.hashCode() == keyHash) {
                for (int j = 0; j < keyLength; j++) {
                    if (subString.charAt(j) != keyword.charAt(j)) {
                        break;
                    }

                    if (j == keyLength - 1) {
                        Log.d(TAG, "找到匹配字符串，起始：" + i
                                + " 终止：" + (i + keyword.length() - 1));

                        return;
                    }
                }
            }
        }
    }
}
