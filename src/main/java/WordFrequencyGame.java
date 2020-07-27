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

                List<WordsInput> wordsInputList = new ArrayList<>();
                for (String words : wordArray) {
                    WordsInput wordsInput = new WordsInput(words, 1);
                    wordsInputList.add(wordsInput);
                }

                Map<String, List<WordsInput>> map = getListMap(wordsInputList);

                List<WordsInput> list = new ArrayList<>();
                for (Map.Entry<String, List<WordsInput>> entry : map.entrySet()) {
                    WordsInput wordsInput = new WordsInput(entry.getKey(), entry.getValue().size());
                    list.add(wordsInput);
                }
                wordsInputList = list;

                wordsInputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(DELIMITER);
                for (WordsInput w : wordsInputList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordsInput>> getListMap(List<WordsInput> wordsInputList) {
        Map<String, List<WordsInput>> wordsMap = new HashMap<>();
        for (WordsInput wordsInput : wordsInputList) {
            if (!wordsMap.containsKey(wordsInput.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordsInput);
                wordsMap.put(wordsInput.getValue(), arr);
            } else {
                wordsMap.get(wordsInput.getValue()).add(wordsInput);
            }
        }
        return wordsMap;
    }
}
