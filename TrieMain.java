import java.util.*;
public class TrieProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie trie = new Trie();
        while (true) {
            System.out.println("\n--- TRIE MENU ---");
            System.out.println("1. Insert word");
            System.out.println("2. Search word");
            System.out.println("3. Show all words");
            System.out.println("4. Show words starting with 'S'");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  
            if (choice == 1) 
            {
                System.out.print("Enter word: ");
                String word = sc.nextLine();
                trie.insert(word);
                System.out.println("Word inserted.");
            } 
            else if (choice == 2) 
            {
                System.out.print("Enter word: ");
                String word = sc.nextLine();
                boolean exists = trie.search(word);
                System.out.println("Found: " + exists);
            }
            else if (choice == 3) 
            {
                List<String> all = trie.getAllWords();
                System.out.println("Words in trie:");
                for (String w : all)
                    {
                    System.out.println(w);
                    }
            } 
            else if (choice == 4) 
            {
                List<String> sWords = trie.getWordsWithPrefix("s");
                System.out.println("Words starting with 'S':");
                if(sWords.isEmpty())
                {
                    System.out.println("There are no words starting with (S)");
                }
                for (String w : sWords) {
                    System.out.println(w);
                }
            } 
            else if (choice == 5)
            {
                System.out.println("Thanks for using 22H51A6290'S Trie Program!");
                break;
            } 
            else
            {
                System.out.println("Invalid choice, try again.");
            }
        }
    }
    static class Node 
    {
        Node[] next = new Node[26];
        boolean isWord = false;
    }
    static class Trie {
        Node root = new Node();
        void insert(String word)
        {
            Node cur = root;
            for (char c : word.toLowerCase().toCharArray()) 
            {
                int idx = c - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new Node();
                }
                cur = cur.next[idx];
            }
            cur.isWord = true;
        }
        boolean search(String word) {
            Node cur = root;
            for (char c : word.toLowerCase().toCharArray()) {
                int idx = c - 'a';
                if (cur.next[idx] == null) {
                    return false;
                }
                cur = cur.next[idx];
            }
            return cur.isWord;
        }
        List<String> getAllWords() {
            List<String> res = new ArrayList<>();
            collect(root, "", res);
            return res;
        }
        List<String> getWordsWithPrefix(String prefix) {
            List<String> res = new ArrayList<>();
            Node cur = root;
            for (char c : prefix.toLowerCase().toCharArray()) {
                int idx = c - 'a';
                if (cur.next[idx] == null) {
                    return res;
                }
                cur = cur.next[idx];
            }
            collect(cur, prefix.toLowerCase(), res);
            return res;
        }
        void collect(Node cur, String prefix, List<String> res) {
            if (cur.isWord) {
                res.add(prefix);
            }
            for (char c = 'a'; c <= 'z'; c++) {
                int idx = c - 'a';
                if (cur.next[idx] != null) {
                    collect(cur.next[idx], prefix + c, res);
                }
            }
        }
    }
}
