import java.util.*;;

class GuessWord {
    public void findSecretWord(String[] words, Master master) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
        }

        int trials = 0;
        while (trials < 30) {
            Random random = new Random();
            int ind = random.nextInt(list.size());
            String st = list.get(ind);
            list.remove(ind);
            int count = master.guess(st);

            if (count == 6) {
                return;
            }

            List<String> temp = update(list, st, count);
            list = temp;
            trials++;
        }
    }

    public List<String> update(List<String> list, String st, int count) {

        List<String> temp = new ArrayList<>();
        for (String s : list) {
            if (count == match(s, st)) {
                temp.add(s);
            }
        }
        return temp;
    }

    public int match(String s1, String s2) {
        int matches = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                matches++;
            }
        }

        return matches;
    }
}