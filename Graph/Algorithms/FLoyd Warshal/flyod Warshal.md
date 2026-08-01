# Floyd Warshall Algorithm — Easy Explanation

## Pehle samjho, ye algorithm kyun use hota hai?

Bhai, agar tumhe har node se har node tak ka shortest path nikalna ho, toh Floyd Warshall kaafi useful hota hai.
Yeh algorithm humein sabhi pairs ke beech minimum distance bata deta hai.

Matlab agar graph me multiple nodes hain aur humein pata karna hai:
- node 0 se node 5 tak kitna minimum cost hai
- node 2 se node 4 tak kitna minimum cost hai
- aur har possible pair ke liye

Toh Floyd Warshall direct kaam kar deta hai.

---

## Floyd Warshall ka basic idea

Iska idea simple hai:

Ek node ko "via" node maan lo.
Agar hum kisi node i se via node tak ja sakte hain aur via se j tak ja sakte hain,
toh phir i se j tak ka path ho sakta hai:

- dist[i][j] = min(dist[i][j], dist[i][via] + dist[via][j])

Yeh basically keh raha hai:

"Agar main i se j tak directly nahi ja sakta, lekin i se via aur via se j tak ja sakta hoon,
toh main un dono ko combine karke ek better path banao."

Isliye is algorithm ko "all-pairs shortest path" kaha jata hai.

---

## Isko samajhne ka simple mindset

Socho tumhare paas ek city map hai.
Har city se har city tak ek path ho sakta hai.
Ab tumhe ye jaan na hai ki kis city se kis city tak sabse chhota route kitna hai.

Floyd Warshall yahan kaam karta hai jaise:

1. Sabse pehle har pair ke liye distance ko initialize kar do.
2. Direct edges ko rakho.
3. Phir har node ko intermediate node maan kar check karo.
4. Agar koi better shortcut mil jaaye, toh update kar do.

Yahi kaam hota hai.

---

## Initialization kaise karte hain?

Sabse pehle ek 2D matrix banao jisme:
- agar i == j, toh distance 0 rakho
- agar direct edge hai, toh uska weight rakho
- agar edge nahi hai, toh Infinity rakho

Isse matlab hota hai:
- khud se khud ka distance 0 hai
- doosre nodes ke beech direct path agar hai toh uska weight hai
- warna abhi koi path available nahi hai

---

## Algorithm ka flow

Agar n nodes hain, toh:

for via from 0 to n-1:
    for i from 0 to n-1:
        for j from 0 to n-1:
            dist[i][j] = min(dist[i][j], dist[i][via] + dist[via][j])

Yeh triple loop ka structure hai.

Har baar ek naya node ko intermediate maan kar dekhte hain.
Agar usse better path milta hai, to update kar dete hain.

---

## Example samjho

Agar graph me edges hain:

- 0 -> 1 = 4
- 0 -> 2 = 1
- 2 -> 1 = 2
- 1 -> 3 = 1
- 2 -> 3 = 5

Toh Floyd Warshall yeh dekh kar path improve karta hai:

- 0 se 2 tak direct path 1 hai
- 2 se 1 tak path 2 hai
- toh 0 se 1 tak path 3 ho jata hai

Isse hum samajh sakte hain ki algorithm intermediate nodes ko use karke shortcuts nikalta hai.

---

## Floyd Warshall ka time complexity

Iska time complexity hota hai:

- O(n^3)

Aur space complexity hoti hai:

- O(n^2)

Iska matlab hai ki agar n ka size chhota hai, toh ye kaafi achha hai.

---

## Jab Floyd Warshall ka use karna chahiye?

Floyd Warshall tab best hota hai jab:
- tumhe har node se har node tak shortest path nikalna ho
- graph ka size thoda chhota ho
- especially n <= 100 ke aas-paas

Agar n ka size zyada ho jaaye, toh O(n^3) ka kaam bahut bada ho sakta hai.
Isliye agar n bohot bada ho toh TLE aa sakta hai.

---

## Bellman-Ford aur Floyd Warshall me kya difference hai?

Bhai, dono shortest path algorithms hain, lekin kaam thoda alag hai.

### Floyd Warshall
- sabhi pairs ke liye shortest path nikalta hai
- all-pairs shortest path ke liye best hai
- O(n^3) time leta hai
- negative weights handle kar sakta hai

### Bellman-Ford
- ek source node se sabhi nodes tak shortest path nikalta hai
- sirf ek source ke liye kaam karta hai
- O(VE) time leta hai
- negative weights handle kar sakta hai
- negative cycle detect karne me bhi useful hai

---

## Bellman-Ford ki limitation kya hai?

Bellman-Ford ka main limitation yeh hai ki ye Floyd Warshall ki tarah har pair ka shortest path nahi nikalta.
Yeh sirf ek source node se sabhi nodes tak ka kaam karta hai.

Isliye agar humein har node se har node tak shortest path chahiye, toh Floyd Warshall zyada convenient hota hai.

Aur agar graph me edges bohot zyada hain, toh Bellman-Ford bhi thoda slow ho sakta hai kyunki uski complexity O(VE) hoti hai.

---

## Dijkstra aur Floyd Warshall me kya choice hoti hai?

### Agar weights non-negative hain
Toh Dijkstra usually better hota hai.
Kyuki:
- ye single-source shortest path ke liye fast hota hai
- O(E log V) complexity hoti hai
- Floyd Warshall ki O(n^3) se zyada efficient hota hai

### Agar weights negative hain
Toh Dijkstra sahi nahi ho sakta.
Isliye Bellman-Ford ya Floyd Warshall use karte hain.

---

## Final practical rule

Bhai, simple rule yeh hai:

- Agar n <= 100 aur tumhe all-pairs shortest path chahiye, toh Floyd Warshall use kar sakte ho.
- Agar n bohot bada hai aur graph sparse hai, toh Dijkstra ya Bellman-Ford better ho sakta hai.
- Agar weights non-negative hain aur sirf ek source se shortest path chahiye, toh Dijkstra.
- Agar weights negative hain aur sirf ek source se shortest path chahiye, toh Bellman-Ford.
- Agar weights negative hain aur har pair ka shortest path chahiye, toh Floyd Warshall bhi kaam kar sakta hai.

> Simple baat, Floyd Warshall chhote graphs ke liye perfect hai, lekin bade graphs me O(n^3) se TLE aa sakta hai. Is case me Dijkstra ya Bellman-Ford ka use karna better hota hai, depending on weights aur problem type.
