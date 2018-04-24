(ns euler-clojure.problem47)

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(defn prime-factors
  [n]
  (cond
    (= 0 (mod n 2)) (conj (prime-factors (quot n 2)) 2)
    :else (loop [i 3 n' n factors []]
      (cond
        (= 0 (mod n' i)) (recur i (quot n' i) (conj factors i))
        (>= i (int (Math/sqrt n'))) (cond-> factors (is-prime n') (conj n'))
        :else (recur (+ i 2) n' factors)))))

(defn distinct-prime-factors
  [n]
  (count (set (prime-factors n))))

(defn n-consecutive
  ([n xs] (n-consecutive n xs []))
  ([n xs acc]
   (if (= n (count acc))
     acc
     (if (= (first acc) (dec (first xs)))
       (recur n (rest xs) (cons (first xs) acc))
       (recur n (rest xs) [(first xs)])))))

(defn solve
  [n]
  (n-consecutive n (filter #(= n (distinct-prime-factors %)) (map (partial + 2) (range)))))
