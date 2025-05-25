(def range (atom {:min 0 :max 0}))
(def guess (atom 0))


(defn start [n m]
  (reset! range {:min n :max m})
  (swap! guess (constantly (quot (+ n m) 2)))
  (println "Игра началась!"))


(defn guess-my-number []
  (println "Мое предположение: " @guess))


(defn smaller []
  (swap! range assoc :max (dec @guess))
  (swap! guess (constantly (quot (+ (@range :min) (@range :max)) 2)))
  (guess-my-number))


(defn bigger []
  (swap! range assoc :min (inc @guess))
  (swap! guess (constantly (quot (+ (@range :min) (@range :max)) 2)))
  (guess-my-number))


(defn -main []
  (println "Введите диапазон чисел (n и m):")
  (let [n (read) m (read)]
    (start n m)
    (loop []
      (guess-my-number)
      (println "Ваш ответ (smaller, bigger, correct):")
      (let [response (read-line)]
        (cond
          (= response "smaller") (do (smaller) (recur))
          (= response "bigger") (do (bigger) (recur))
          (= response "correct") (println "Ура! Я угадал!")
          :else (println "Некорректный ответ. Попробуйте снова.") (recur))))))
