# Bellman-Ford Algorithm

Bellman-Ford ek shortest path algorithm hai jo humein source node se sabhi nodes tak minimum cost dikhata hai. Ye algorithm specially useful hota hai jab graph me negative weight edges ho sakte hain.

---

## 1. Bellman-Ford kyun use karte hain?

Dijkstra algorithm kaafi achhi hai, lekin jab graph me negative weight edges aate hain, tab Dijkstra sahi result nahi de sakti. Isliye Bellman-Ford ka use karte hain.

### Example

Agar graph ye hai:

- a -> b with weight 2
- b -> c with weight -5
- a -> c with weight 3

Toh shortest path a se c ka value hai:

- a -> b -> c = 2 + (-5) = -3

Ye Dijkstra ke cases me galat ya incomplete lag sakta hai, jab tak hum relaxation properly use naa karein.

---

## 2. Negative cycle ka issue

Agar graph me negative cycle hota hai, toh shortest path ko define nahi kiya ja sakta, kyunki path infinite small ho sakta hai.

Example:

- a -> b (2)
- b -> c (-7)
- c -> a (4)

Is graph me cycle ka total weight hota hai:

- 2 + (-7) + 4 = -1

Isliye Bellman-Ford negative cycle detect karne ke liye bhi kaam aata hai.

---

## 3. Bellman-Ford ka simple idea

Algorithm ka idea simple hai:

1. Sabhi nodes ko infinity se initialize kar do.
2. Source node ki distance 0 kar do.
3. Har edge ko relax karo.
4. Ye relaxation tab hoti hai jab:

   `dist[u] + weight < dist[v]`

   toh:

   `dist[v] = dist[u] + weight`

5. Is process ko V - 1 baar repeat karte hain, jahan V nodes ki count hai.
6. Agar ek aur baar relaxation hoti hai, toh graph me negative cycle hai.

---

## 4. Steps of Bellman-Ford

1. Distance array banayein.
2. Source node ki distance `0` rakhein.
3. `V - 1` baar edges ko relax karen.
4. Ek aur baar relax kar ke check karen.
5. Agar koi update hoti hai, toh negative cycle hai.
6. Warna distance array return kar dein.

---

## 5. Pseudocode

```text
initialize dist[] with INF
set dist[src] = 0

for i = 1 to V - 1:
    for each edge (u, v, w):
        if dist[u] != INF and dist[u] + w < dist[v]:
            dist[v] = dist[u] + w

for each edge (u, v, w):
    if dist[u] != INF and dist[u] + w < dist[v]:
        print("Negative cycle detected")
        break
```

---

## 6. Time Complexity

- Time: `O(V * E)`
- Space: `O(V)`

Ye algorithm simple aur reliable hai, lekin Dijkstra ki tarah fast nahi hota.

---

## 7. Java implementation

```java
import java.util.*;

class BellmanFord {
    static int[] bellmanFord(int n, int[][] edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }

            if (!updated) {
                break;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Negative cycle detected");
                break;
            }
        }

        return dist;
    }
}
```

---

## 8. Summary

- Bellman-Ford shortest path dhoondhne ke liye use hota hai.
- Negative weight edges ko handle kar sakta hai.
- Negative cycle detect bhi kar sakta hai.
- Iska basic rule hai: har edge ko relax karna.

Agar chaho, main isko aur bhi simple Hindi style mein likh sakta hoon ya phir Java mein full working example bhi de sakta hoon.

