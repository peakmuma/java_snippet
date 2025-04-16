package me.peak.algo;

import java.util.ArrayList;
import java.util.List;
import me.peak.utils.PrintUtil;

/**
 * https://leetcode-cn.com/problems/text-justification/
 */
public class TextJustification {
    public static void main(String[] args) {
        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        PrintUtil.printStrList(fullJustify(words, 16));
        System.out.println();
        words = new String[] {"justification."};
        PrintUtil.printStrList(fullJustify(words, 17));
        System.out.println();
        words = new String[] {"justification.", "This", "is", "an", "example", "of", "text"};
        PrintUtil.printStrList(fullJustify(words, 16));
        System.out.println();
        words = new String[] {"This", "is", "an", "example", "of", "text"};
        PrintUtil.printStrList(fullJustify(words, 7));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        int currentRowWordCount = 0;
        int currentRowWidth = 0;
        int whiteSpace;
        List<Integer> wordCountList = new ArrayList<>();
        List<int[]> whiteSpaceList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (currentRowWidth > 0) {
                whiteSpace = 1;
            } else {
                whiteSpace = 0;
            }
            if (currentRowWidth + words[i].length() + whiteSpace <= maxWidth) {
                currentRowWidth += words[i].length() + whiteSpace;
                currentRowWordCount++;
            } else {
                int whiteSpaceCount = maxWidth - currentRowWidth + currentRowWordCount - 1;
                int[] whiteSpaceCountArray;
                whiteSpaceCountArray = new int[currentRowWordCount == 1 ? 1 : currentRowWordCount - 1];
                for (int k = 0; whiteSpaceCount > 0; whiteSpaceCount--) {
                    whiteSpaceCountArray[k]++;
                    k = (k + 1) % (whiteSpaceCountArray.length);
                }
                wordCountList.add(currentRowWordCount);
                whiteSpaceList.add(whiteSpaceCountArray);
                currentRowWidth = 0;
                currentRowWordCount = 0;
                if (i < words.length - 1) {
                    i--;
                }
            }
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int wordIndex = 0;
        for (int i = 0; i < wordCountList.size(); i++) {
            int wordCount = wordCountList.get(i);
            int[] whiteSpaceArray = whiteSpaceList.get(i);
            for (int j = 0; j < wordCount; j++) {
                sb.append(words[wordIndex++]);
                if (j == wordCount - 1 && j > 0) {
                    continue;
                }
                for (int k = 0; k < whiteSpaceArray[j]; k++) {
                    sb.append(" ");
                }
            }
            res.add(sb.toString());
            sb.setLength(0);
        }
        for (;wordIndex < words.length; wordIndex++) {
            sb.append(words[wordIndex]);
            if (wordIndex < words.length - 1) {
                sb.append(" ");
            }
        }
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        res.add(sb.toString());
        return res;
    }
}
