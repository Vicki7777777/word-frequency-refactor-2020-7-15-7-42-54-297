import java.util.*;

public class WordFrequencyGame {
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String WORD_COUNT = " 1";
    private static final String DELIMITER = "\n";
    private String SEPARATOR = "\\s+";
    public String getResult(String word) {
        if (word.split(SEPARATOR).length == 1) {
            return word + WORD_COUNT;
        } else {
            try {
                String[] wordArray = word.split(SEPARATOR);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String words : wordArray) {
                    WordInfo wordInfo = new WordInfo(words, 1);
                    wordInfoList.add(wordInfo);
                }

                Map<String, List<WordInfo>> map = getListMap(wordInfoList);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfoList = list;

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(DELIMITER);
                for (WordInfo w : wordInfoList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordsMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordsMap.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                wordsMap.put(wordInfo.getValue(), arr);
            } else {
                wordsMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordsMap;
    }
}
