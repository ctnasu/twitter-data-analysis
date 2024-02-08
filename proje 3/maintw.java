/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graph1;
import com.mycompany.graph1.MyHashMap.Entry;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}



class User {
    private String username;
    private String name;
    private int followers_count;
    private int following_count;
    private String language;
    private String region;
    private JSONArray tweets; // Kullanıcının tweetlerini içeren JSON array
    private JSONArray following; // Takip edilenleri temsil eden JSON array
    private JSONArray followers; // Takipçileri temsil eden JSON array

 
    public User(String username, String fullName, int followers_count, int following_count, String language, String region) {
        this.username = username;
        this.name = fullName;
        this.followers_count = followers_count;
        this.following_count = following_count;
        this.language = language;
        this.region = region;
        this.tweets = new JSONArray();
        this.following = new JSONArray();
        this.followers = new JSONArray();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTweets(JSONArray tweets) {
        this.tweets = tweets;
    }

    public void setFollowing(JSONArray following) {
        this.following = following;
    }

    public void setFollowers(JSONArray followers) {
        this.followers = followers;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public String getLanguage() {
        return language;
    }

    public String getRegion() {
        return region;
    }

    public JSONArray getTweets() {
        return tweets;
    }

    public JSONArray getFollowing() {
        return following;
    }

    public JSONArray getFollowers() {
        return followers;
    }
    


public String findMostCommonWord() {
    MyHashMap<String, Integer> wordCountMap = new MyHashMap<>();
  String[] bannedWords = {"da","bulunmuştur","ve", "gibi","bir","olarak","bu","ile","için","bulunmaktadır","tarafından","hakkında","de","sonra","ayrıca","ilk","çok","ise","daha","iki","en","her","vardır","yapmaktadır","nedeniyle","içinde","birçok","ilgili","aynı","diğer","gelmiştir","oluşturmaktadır","alanlarda","olan","oldu","gelen","üzerine","sahiptir","yıl","oldu","olmuştur","oldukça","büyük","durumdadır","günümüzde","özellikle","kullanılır","oluşturulmuştur","kazanmıştır","yazmaktadır","değil","değildir","yüksek","hem","almaktadır","genellikle","ancak","fakat","kısa","oldukları","arasında","bölümünde","bilinir","oluşturma","yakınlarındaki","alanlarında","başladı","değerinin","edilmektedir","dönüşmüştür","beklenmektedir","yapmıştır","ikinci","ikincisi","kapladığı","etmektedir","göre","yani","üç","uygulanır","vermektedir","olduğu","oluşturur","yapılmaktadır","içerisinde","uygulanır","ardından","bulunamamıştır","çıkmıştır","seçilmiştir","yıllarında","yakınında","çıkmıştır","bilinmektedir","belirlediği","birlikte","yoktur","bulunmamaktadır","yıllarda","edilmiştir","kullanılmıştır","olmasına","yer","bölgeleride","mevcuttur","sürdürmektedir","çalışmaktadır","sayesinde","kullanılmaktadır","olabilir","kalmıştır","dolayısıyla","süre","birkaç","buradan","kısımları","çoğunlukla","kendi","rağmen","bulunur","açısından","gösteren","kazandı","çeşitli","üzerinde","kullanılan","tamamlamıştır","pozisyonunda","gelmektedir","birbirine","kesimlerde","geçmiştir","yaşanmaktadır","kullanılarak","yapılmıştır","döneminde","konusunda","kullanılan","pek","üzerinden","farklı","almıştır","bölgesinde","bilmektedir","çalıştı","sırasında","kişiler","benzer","göstermektedir","kendisinden","sırasında","önemli","tüm","nin","nın","bilinmemektedir","şekilde","bilinen","hemen","bunun","kullanılabilir","böylece","kullanımı","kullanılmaktadır","aslında","olduğunu","taşımaktadır","bulmuştur","değişti","arasındaki","arasında","mümkündür","verdiği","adlandırılır","bulundu","yaklaşık","etti","arasındaki","şekilde","düşünülmektedir","yayınlanmıştır","gösterilmiştir","son","aşağıdaki","ayrılır","değildi","getirildi","genelde","azledildi","beklemediği","tahminler","verirler","etmişlerdir","aldı","yaptı","yapılabiliyor","vermiştir","veya","uygulanmıştır","kendini","sık","çekti","değişiklik","miktarda","edildiği","görülmektedir","zamanda","meydana","kendilerine","ya","sonrasında","bazı","kullanılmaktaydı","kadar","o","şu","şunu","yoğunlaştı","olağanüstü","karşısında","alanında","hiçbir","bölümde","geldiğinde","bölgedeki","adlandırıldı","bırakıldı","miktarını","devam","etmiştir","dört","ettiler","kez","tek","gelinceye","olmamıştır","kullanılmaktadır","sonucunda"};
    // Ensure 'tweets' array is properly initialized and contains data
    if (tweets != null && tweets.length() > 0) {
        for (int i = 0; i < tweets.length(); i++) {
            String tweet = tweets.getString(i);
//tweet boş mu kontrol et
            if (tweet != null && !tweet.isEmpty()) {
                StringBuilder word = new StringBuilder();
                for (char ch : tweet.toCharArray()) {
                    if (Character.isLetter(ch) || ch == '\'') {
                        word.append(ch);
                    } else if (word.length() > 0) {
                        String cleanedWord = word.toString().toLowerCase();
                        int count = wordCountMap.getOrDefault(cleanedWord, 0);
                        wordCountMap.put(cleanedWord, count + 1);
                        word.setLength(0); 
                    }
                }
                if (word.length() > 0) {
                    String cleanedWord = word.toString().toLowerCase();
                    int count = wordCountMap.getOrDefault(cleanedWord, 0);
                    wordCountMap.put(cleanedWord, count + 1);
                }
            }
        }
    } else {
        return "No tweets found."; 
    }

    // MyLinkedListte en çok geçen kelimeyi bul
    MyLinkedList<String> mostCommonWords = new MyLinkedList<>();
    int maxCount = 0;
 
 for (Entry<String, Integer> entry : wordCountMap.buckets) {
        Entry<String, Integer> current = entry;
        while (current != null) {
            int count = current.value;
            String word = current.key;

            // Yasaklı kelimeleri kontrol et
            boolean isBanned = false;
            for (String bannedWord : bannedWords) {
                if (word.equalsIgnoreCase(bannedWord)) {
                    isBanned = true;
                    break;
                }
            }

            // Eğer yasaklı kelime değilse devam et
            if (!isBanned) {
                if (count > maxCount) {
                    maxCount = count;
                    mostCommonWords = new MyLinkedList<>();
                    mostCommonWords.add(word);
                } else if (count == maxCount) {
                    mostCommonWords.add(word);
                }
            }

            current = current.next;
        }
    }

    //  Aynı sayıya sahip birden fazla kelime varsa en uzun kelimeyi seç
    String mostCommonWord = "";
    Iterator<String> wordIterator = mostCommonWords.iterator();
    while (wordIterator.hasNext()) {
        String word = wordIterator.next();
        if (word.length() > mostCommonWord.length()) {
            mostCommonWord = word;
        }
    }

    return mostCommonWord.isEmpty() ? "No common word found." : mostCommonWord;
}


}
class Graph {
    private MyHashMap<String, MyLinkedList<String>> followedByMap;
    private MyHashMap<String, MyLinkedList<String>> followingByMap;

    public Graph() {
        this.followedByMap = new MyHashMap<>();
        this.followingByMap = new MyHashMap<>();
    }

    public void addEdge(String source, String destination) {
        if (!followedByMap.containsKey(source)) {
            followedByMap.put(source, new MyLinkedList<>());
        }
        if (!followingByMap.containsKey(destination)) {
            followingByMap.put(destination, new MyLinkedList<>());
        }

        followedByMap.get(source).add(destination);
        followingByMap.get(destination).add(source);
    }

    public MyLinkedList<String> getFollowedBy(String source) {
        return followedByMap.getOrDefault(source, new MyLinkedList<>());
    }

    public MyLinkedList<String> getFollowingBy(String source) {
        return followingByMap.getOrDefault(source, new MyLinkedList<>());
    }

    public void writeRelationshipToFile(String source, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        MyLinkedList<String> followedBy = getFollowedBy(source);
        MyLinkedList<String> followingBy = getFollowingBy(source);

        writer.write("following: " + source + ":\n");
        for (String followed : followedBy) {
            writer.write("- " + followed + "\n");
        }

        writer.write("followers: " + source + ":\n");
        for (String follower : followingBy) {
            writer.write("- " + follower + "\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public void writeRelationshipBetweenUsers(String user1, String user2, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        MyLinkedList<String> followedByUser1 = getFollowedBy(user1);
        MyLinkedList<String> followingByUser1 = getFollowingBy(user1);
        MyLinkedList<String> followedByUser2 = getFollowedBy(user2);
        MyLinkedList<String> followingByUser2 = getFollowingBy(user2);

        writer.write("Relationship between " + user1 + " and " + user2 + ":\n");
        writer.write(user1 + " follows " + user2 + ": " + followingByUser1.contains(user2) + "\n");
        writer.write(user2 + " follows " + user1 + ": " + followingByUser2.contains(user1) + "\n");

        writer.write("\nFollowers of " + user1 + ":\n");
        for (String follower : followedByUser1) {
            writer.write("- " + follower + "\n");
        }

        writer.write("\nFollowers of " + user2 + ":\n");
        for (String follower : followedByUser2) {
            writer.write("- " + follower + "\n");
        }
        
        writer.write("\nFollowing of " + user1 + ":\n");
        for (String followed : followingByUser1) {
            writer.write("- " + followed + "\n");
        }

        writer.write("\nFollowing of " + user2 + ":\n");
        for (String followed : followingByUser2) {
            writer.write("- " + followed + "\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    

}


public class maintw {
   
    public static void main(String[] args) {
        // myHashMap'in oluşturulması
        MyHashMap<String, User> myHashMap = new MyHashMap<>();
        Graph graph=new Graph();

    
try {
    String content = new String(Files.readAllBytes(Paths.get("/Users/ASUS/Desktop/twitter_data_50K.json")));
    JSONArray usersArray = new JSONArray(content);

    for (int i = 0; i < usersArray.length(); i++) {
        JSONObject userObj = usersArray.getJSONObject(i);

        String username = userObj.getString("username");
        String name = userObj.getString("name");
        int followers_count = userObj.getInt("followers_count");
        int following_count = userObj.getInt("following_count");
        String language = userObj.getString("language");
        String region = userObj.getString("region");

        // Yeni bir User nesnesi oluştur
        User user = new User(username, name, followers_count, following_count, language, region);

        // Diğer bilgileri ekle (tweetler, takip edilenler, takipçiler)
        user.setTweets(userObj.getJSONArray("tweets"));
        user.setFollowing(userObj.getJSONArray("following"));
        user.setFollowers(userObj.getJSONArray("followers"));

        // Kullanıcıyı MyHashMap'e ekle
        myHashMap.put(username, user);
         
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Örnek kullanıcı
User sampleUser = myHashMap.get("holly.stanton");

   
  
if (sampleUser != null) {
    // Kullanıcı bilgilerini yazdır
    
    System.out.println("kullanıcı adı: " + sampleUser.getUsername());
    System.out.println("adı: " + sampleUser.getName());
    System.out.println(" takipçi sayısı: " + sampleUser.getFollowers_count());
    System.out.println(" takip ettiği lişi sayısı: " + sampleUser.getFollowing_count());
    System.out.println("dil : " + sampleUser.getLanguage());
    System.out.println("bölge: " + sampleUser.getRegion());

  // Kullanıcının tweetlerini göster
    JSONArray tweets = sampleUser.getTweets();
    System.out.println("tweetleri:");
    for (int i = 0; i < tweets.length(); i++) {
        System.out.println("- " + tweets.getString(i));
    }

    // Kullanıcının takip ettiklerini göster
    JSONArray following = sampleUser.getFollowing();
    System.out.println("takip ettikleri:");
    for (int i = 0; i < following.length(); i++) {
        System.out.println("- " + following.getString(i));
    }

    // Kullanıcının takipçilerini göster
    JSONArray followers = sampleUser.getFollowers();
    System.out.println("takipçileri:");
    for (int i = 0; i < followers.length(); i++) {
        System.out.println("- " + followers.getString(i));
    }
   
} 

for (User user : myHashMap.values()) {
    String mostCommonWord = user.findMostCommonWord();
    System.out.println("kullanıcı: " + user.getUsername() + " - en çok kullandığı anlamlı kelime: " + mostCommonWord);
}

 //Tüm kullanıcıları döngü ile dolaşarak bilgilerini gösterme
for (User user : myHashMap.values()) {
    
    System.out.println("kullanıcı adı: " + user.getUsername());
    System.out.println("adı: " + user.getName());
    System.out.println("takipçi sayısı: " + user.getFollowers_count());
    System.out.println("takip ettiği kişi sayısı: " + user.getFollowing_count());
    System.out.println("dil: " + user.getLanguage());
    System.out.println("bölge: " + user.getRegion());

    // Kullanıcının tweetlerini göster
    JSONArray tweets = user.getTweets();
    System.out.println("tweetleri");
    for (int i = 0; i < tweets.length(); i++) {
        System.out.println("- " + tweets.getString(i));
    }

    // Kullanıcının takip ettiklerini göster
    JSONArray following = user.getFollowing();
    System.out.println("takip ettikleri:");
    for (int i = 0; i < following.length(); i++) {
        System.out.println("- " + following.getString(i));
    }

    // Kullanıcının takipçilerini göster
    JSONArray followers = user.getFollowers();
    System.out.println("takipçileri:");
    for (int i = 0; i < followers.length(); i++) {
        System.out.println("- " + followers.getString(i));
    }
    String mostCommonWord = user.findMostCommonWord();
    System.out.println("en çok geçen kelime " + user.getUsername() + ": " + mostCommonWord);
    
    System.out.println("----------------------------------");
    
}
// En yaygın kelimeleri ve bu kelimeleri kullanan kullanıcıları saklamak için MyHashMap kullanımı
MyHashMap<String, MyLinkedList<String>> commonWordsByUsers = new MyHashMap<>();

// Tüm kullanıcıların en yaygın kelimesini bul ve kelimeleri ve kullanıcıları sakla
for (User user : myHashMap.values()) {
    String mostCommonWord = user.findMostCommonWord();

    // En yaygın kelimeyi depola
    if (!commonWordsByUsers.containsKey(mostCommonWord)) {
        commonWordsByUsers.put(mostCommonWord, new MyLinkedList<>());
    }
    commonWordsByUsers.get(mostCommonWord).add(user.getUsername());
}

// Her kelime için aynı en yaygın kelimeye sahip kullanıcıları bul ve MyHashMap içinde sakla

for (MyHashMap.Entry<String, MyLinkedList<String>> entry : commonWordsByUsers.entries()) {
    String word = entry.getKey();
    MyLinkedList<String> users = entry.getValue();
    int userCount = users.size();

    // Eğer aynı en yaygın kelimeye sahip en az iki kullanıcı varsa göster
    if (userCount >= 2) {
        System.out.println("en çok geçen anlamlı kelime: " + word + " - aynı en çok geçen kelimeye sahip kullanıcılar (" + userCount + " kullanıcı): ");
        for (String user : users) {
            System.out.println("- " + user);
        }
        System.out.println();
    }
}


try {
    String content = new String(Files.readAllBytes(Paths.get("/Users/ASUS/Desktop/twitter_data_50K.json")));
    JSONArray usersArray = new JSONArray(content);

    for (int i = 0; i < usersArray.length(); i++) {
        JSONObject userObj = usersArray.getJSONObject(i);

        String username1 = userObj.getString("username");
        String name1 = userObj.getString("name");
        int followers_count = userObj.getInt("followers_count");
        int following_count = userObj.getInt("following_count");
        String language = userObj.getString("language");
        String region = userObj.getString("region");

        
        // Yeni bir User nesnesi oluştur
        User user = new User(username1, name1, followers_count, following_count, language, region);

        // Diğer bilgileri ekle (tweetler, takip edilenler, takipçiler)
        user.setTweets(userObj.getJSONArray("tweets"));
        user.setFollowing(userObj.getJSONArray("following"));
        user.setFollowers(userObj.getJSONArray("followers"));

        // Kullanıcıyı MyHashMap'e ekle
        myHashMap.put(username1, user);
         // Grafa kullanıcıları ekleme
       JSONArray following = userObj.getJSONArray("following");
    for (int j = 0; j < following.length(); j++) {
        String followedUser = following.getString(j);
        graph.addEdge(username1, followedUser);
    }

    JSONArray followers = userObj.getJSONArray("followers");
    for (int j = 0; j < followers.length(); j++) {
        String follower = followers.getString(j);
                    myHashMap.putIfAbsent(follower, new User(follower, "", 0, 0, "", ""));
                    graph.addEdge(follower, username1);
    }
        
        
    }
} catch (IOException e) {
    e.printStackTrace();
}


    String username = "monserrat82";
        String filePath = "/Users/ASUS/Desktop/relationship_output.txt";
        graph.writeRelationshipToFile(username, filePath);
        //tek kullancının takip takip etme ilişkisini txte yazdır
        
         String user1 = "ymohr";
        String user2 = "jupton";

        String filePathBetweenUsers = "/Users/ASUS/Desktop/relationship_between_users.txt";
        graph.writeRelationshipBetweenUsers(user1, user2, filePathBetweenUsers);
         //iki kullancının takip takip etme ilişkisini txte yazdır
   
    }


}
