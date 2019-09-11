import java.util.*;

public class Test {
    private static HashMap<String, String> testMap = new HashMap<>();

    static {
        testMap.put("", "[]");
        testMap.put("     ", "[]");
        testMap.put("ааа ббб ввв", "[]");
        testMap.put("сарай биржа болт бокс сaпог корм", "[б=[биржа, бокс, болт], с=[сaпог, сарай]]");
        testMap.put("a atm at b ba bs bss c", "[a=[atm, at, a], b=[bss, ba, bs, b]]");
    }

    public static void main(String[] args) {
        Set<String> keyList = testMap.keySet();
        for (String key : keyList) {
            WordGroupsImpl w = new WordGroupsImpl(key);
            if (w.getResult(1).contentEquals(testMap.get(key))) {
                System.out.println("Test PASSED ! " + key + "==>" + w.getResult(1));
            } else {
                System.out.println("Test FAILED ! " + key + "==>" + w.getResult(1) + "expected: " + testMap.get(key));
            }
        }
    }
}


class WordGroupsImpl {
    private final TreeMap<Character, List<String>> groupsMap = new TreeMap<>();

    public WordGroupsImpl(String value) {
        List<String> words = Arrays.asList(value.trim().split(" "));
        for (String word : words) {
            if (!word.isEmpty()) {
                put(word);
            }
        }
    }

    public void put(String word) {
        Character ch = word.charAt(0);
        if (groupsMap.get(ch) == null) {
            List<String> wordList = new ArrayList<>();
            wordList.add(word);
            groupsMap.put(ch, wordList);
        } else {
            List<String> words = groupsMap.get(ch);
            words.add(word);
            //сортируем после каждого добавляния, т.к. структура должна сохранятся, если добавили новые слова
            Collections.sort(words, (o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return -1;
                } else if (o1.length() < o2.length()) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            });
            groupsMap.put(word.charAt(0), words);
        }
    }

    public String getResult(int threshold) {
        StringBuilder sb = new StringBuilder("[");
        Set<Character> keySet = groupsMap.keySet();
        for (Character key : keySet) {
            List<String> words = groupsMap.get(key);
            if (words.size() <= threshold) {
                continue;
            }
            sb.append(key + "=[");

            for (String word : words) {
                sb.append(word + ", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("], ");
        }
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}

