import java.util.*;

public class WordFrequencyGame {
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String WORD_COUNT = " 1";
    private static final String DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private String SEPARATOR = "\\s+";
    public String getResult(String sentence) {

            try {
                List<WordInfo> wordInfoList = generateWordInfos(sentence);
                return generateWordFrequencySentence(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
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

    private String generateWordFrequencySentence(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> map = getWordMap(wordInfos);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfos = list;

        wordInfos.sort((firstWordInfo, secondeWordInfo) -> secondeWordInfo.getWordCount() - firstWordInfo.getWordCount());

        StringJoiner joiner = new StringJoiner(DELIMITER);
        for (WordInfo words : wordInfos) {
            String wordCount = words.getValue() + BLANK_SPACE + words.getWordCount();
            joiner.add(wordCount);
        }
        return  joiner.toString();

    }

    private Map<String, List<WordInfo>> getWordMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordsMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordsMap.containsKey(wordInfo.getValue())) {
                ArrayList wordInfoArr = new ArrayList<>();
                wordInfoArr.add(wordInfo);
                wordsMap.put(wordInfo.getValue(), wordInfoArr);
            } else {
                wordsMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordsMap;
    }
}
