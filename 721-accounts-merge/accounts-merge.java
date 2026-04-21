import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        for (List<String> acc : accounts) {
            String firstEmail = find(parent, acc.get(1));
            for (int i = 2; i < acc.size(); i++) {
                parent.put(find(parent, acc.get(i)), firstEmail);
            }
        }

        for (List<String> acc : accounts) {
            String p = find(parent, acc.get(1));
            unions.putIfAbsent(p, new TreeSet<>());
            for (int i = 1; i < acc.size(); i++) {
                unions.get(p).add(acc.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();

        for (String p : unions.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(owner.get(p));
            list.addAll(unions.get(p));
            res.add(list);
        }

        return res;
    }

    private String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }
}