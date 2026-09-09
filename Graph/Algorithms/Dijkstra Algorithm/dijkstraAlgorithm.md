# Dijkstra Algorithm — Easy Explanation

## Ye samjho pehle ki isme priority queue kyu lagti hai

Dekho bhai queue bolta hai ki mujhme jo pehle add hoga, vo mai pehle nikalunga.
Mene kaha theek, to mene kaha ki bhai agar mai ek node se dusre node par jane ka rasta puchu tumse, vo bhi shortest ho aur uska cost bhi kam ho.
To queue bola ki bhai main toh bas order ke hisaab se nodes ko nikalta hoon, mujhe pata nahi hota ki kis path ka cost sabse kam hai.

Abhi aaya ek rakshah, jiska naam hai cost. Graph mein ek node se dusre node par jane ke liye alag-alag cost lag sakte hain.
Mene queue se kaha ki bhai ab batao konsa path shortest hai aur kis path me cost sabse kam hai.
Queue bola bhai main ye kaam nahi kar sakta, kyunki main sirf jis order me tumne mujhe add kiya tha, usi order me nikalta hoon.

Humne kaha fir kese karenge? To queue bolta hai mere ek bhai hai priority queue.
Priority queue ye kaam karta hai ki vo har baar sabse chhota cost wala element pehle nikalta hai.
Isliye hum usko use karte hain.

---

## Abhi iske sath do variants aa sakte hain

### 1. Shortest distance nikalna
Agar humein sirf start node se har node tak minimum distance nikalni ho, to hum dist array use karte hain.
Har node ke liye uska minimum distance store karte rehte hain.

### 2. Shortest distance ke saath kitne raaste hain nikalna
Agar humein ye bhi pata karna ho ki start se end tak minimum distance par kitne alag-alag raaste hain, to hum ways array use karte hain.

Yahan ek important baat samajhni hai:
- Agar humein kisi node tak ek naya path milta hai jo current minimum distance se chhota ho, to hum usko update karte hain.
- Agar humein same minimum distance ka aur path milta hai, to hum usko add karte hain, kyunki matlab ek aur shortest path mil gaya.
- Isliye hum `ways[node] = ways[parent]` use karte hain jab new shortest path milta hai.
- Aur hum `ways[node] += ways[parent]` ya `ways[node] = ways[node] + ways[parent]` use karte hain jab same minimum distance ka aur path milta hai.

Yeh isliye hota hai kyunki ek node ko multiple short paths se bhi reach kiya ja sakta hai. Agar hum `+` nahi karte, to hum sirf ek hi way count karenge, jo galat hoga.

Yahi reason hai ki humne dist aur ways dono maintain kiye.

---

## Dijkstra ka basic idea

Socho tumhe ek city ka map diya gaya hai, aur tumhe start point se destination tak sabse kam time wala route dhoondhna hai.
Har road ka apna time ya weight hota hai.

Aap har baar us node ko choose karte ho jo abhi tak sabse kam cost pe aaya ho.
Phir us node se connected dusre nodes ko dekhte ho aur agar unka path aur chhota ho sakta hai to update kar dete ho.

Yahi Dijkstra algorithm ka kaam hai.

---

## Dijkstra ka flow

1. Start node ko distance 0 rakho.
2. Har dusre node ko infinity rakho.
3. Priority queue me start node daalo.
4. Har baar sabse chhota distance wala node nikal lo.
5. Uske neighbors ko check karo.
6. Agar current node se un tak ka path chhota ho, to distance update kar do.
7. Agar same shortest distance ka aur path mil jaaye, to ways ko add kar do.

---

## Simple example

Agar graph mein edges hain:

- 0 -> 1 with cost 4
- 0 -> 2 with cost 1
- 2 -> 1 with cost 2
- 1 -> 3 with cost 1
- 2 -> 3 with cost 5

Toh shortest path 0 se 3 tak:

- 0 -> 2 -> 1 -> 3
- total cost = 1 + 2 + 1 = 4

Yeh Dijkstra ke through nikalta hai.

---

## Final line

> Dijkstra algorithm ka kaam hi yahi hai ki sabse chhota cost wala path dhoondhna aur us path ke through har node tak minimum distance find karna.
