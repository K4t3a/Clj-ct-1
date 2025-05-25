(def bounds (atom {:min 0 :max 0}))
(def current-guess (atom 0))

(defn init-game [n m]
  (reset! bounds {:min n :max m})
  (swap! current-guess (constantly (quot (+ n m) 2)))
  (println "Игра началась!"))

(defn show-guess []
  (println "Моё предположение: " @current-guess))

(defn lower []
  (swap! bounds assoc :max (dec @current-guess))
  (swap! current-guess (constantly (quot (+ (@bounds :min) (@bounds :max)) 2)))
  (show-guess))

(defn higher []
  (swap! bounds assoc :min (inc @current-guess))
  (swap! current-guess (constantly (quot (+ (@bounds :min) (@bounds :max)) 2)))
  (show-guess))

(defn -main []
  (println "Введите диапазон чисел (n и m):")
  (let [n (read) m (read)]
    (init-game n m)
    (loop []
      (show-guess)
      (println "Ваш ответ (lower, higher, correct):")
      (let [response (read-line)]
        (cond
          (= response "lower") (do (lower) (recur))
          (= response "higher") (do (higher) (recur))
          (= response "correct") (println "Ура! Я угадал!")
          :else (println "Некорректный ответ. Попробуйте снова.") (recur))))))
