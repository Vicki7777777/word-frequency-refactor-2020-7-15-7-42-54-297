import java.util.*;

public class WordFrequencyGame {
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String WORD_COUNT = " 1";
    private static final String DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private String SEPARATOR = "\\s+";
    public String getResult(String sentence) {
        if (sentence.split(SEPARATOR).length == 1) {
            return sentence + WORD_COUNT;
        } else {
            try {
                List<WordInfo> wordInfoList = generateWordInfos(sentence);
                return generateWordFrequencySentence(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    public  List<WordInfo> generateWordInfos(String sentence){
        List<WordInfo> wordInfos = new ArrayList<>();
        String[] wordArray = sentence.split(SEPARATOR);
        for (String words : wordArray) {
            WordInfo wordInfo = new WordInfo(words, 1);
            wordInfos.add(wordInfo);
        }
        return wordInfos;
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
