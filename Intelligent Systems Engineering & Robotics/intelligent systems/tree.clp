(deftemplate data 
(slot v15)
(slot v12)
(slot v18)
(slot v22)
(slot v3)
(slot class))

(deftemplate result 
(slot was)
(slot got))

(defglobal

; 1st row of confusion matrix
 
  ?*was1got1* = 0
  ?*was1got2* = 0
  ?*was1got3* = 0
  ?*was1got4* = 0

; 2nd row
 
  ?*was2got1* = 0
  ?*was2got2* = 0
  ?*was2got3* = 0
  ?*was2got4* = 0

; 3rd row

  ?*was3got1* = 0
  ?*was3got2* = 0
  ?*was3got3* = 0
  ?*was3got4* = 0

; 4th row

  ?*was4got1* = 0
  ?*was4got2* = 0
  ?*was4got3* = 0
  ?*was4got4* = 0
)

(defrule display_datas
    
  =>
    (open "E:\\Documents\\robotics2\\Testing_input_FULL.txt" input "r")
  	
  	(loop-for-count (?cnt1 1 410) do ; 954 instances for training, 410 for test
 		(bind ?input2 (explode$ (readline input)))
   		(assert (data (v15 (nth$ 3 ?input2))
                    (v12 (nth$ 2 ?input2))
                    (v18 (nth$ 4 ?input2))
                    (v22 (nth$ 5 ?input2))
                    (v3 (nth$ 1 ?input2))
                    (class (nth$ 6 ?input2))
       			  )            
    	)
    )	

    (close input)
)

(defrule a1
(data (v15 ?v15) (class ?class))
(test (<= ?v15 0.9))
=>
(assert (result(was ?class) (got 2))))


(defrule a2
(data(v15 ?v15) (v12 ?v12) (class ?class))
(test(<= ?v12 0.909))
(test(> ?v15 0.9))
=>
(assert (result(was ?class) (got 2))))


(defrule a3
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(<= ?v18 0.505))
=>
(assert (result(was ?class) (got 4))))


(defrule a4
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (class ?class) (v3 ?v3) (v22 ?v22) )
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(<= ?v18 0.902))
(test(<= ?v12 2.582))
=>
(assert (result(was ?class) (got 1))))


(defrule a5
(data(v15 ?v15) (v12 ?v12) (v18 ?v18)(class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(<= ?v18 0.902))
(test(> ?v12 2.582))
(test(<= ?v12 2.994))
(test(<= ?v18 0.698))
=>
(assert (result(was ?class) (got 1))))


(defrule a6pt1
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (class ?class) )
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(<= ?v18 0.902))
(test(> ?v12 2.582))
(test(<= ?v12 2.994))
(test(> ?v18 0.698))
;
(test(> ?v15 0.919))
=>
(assert (result(was ?class) (got 4))))


(defrule a6pt2
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (class ?class) )
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(<= ?v18 0.902))
(test(> ?v12 2.582))
(test(<= ?v12 2.994))
(test(> ?v18 0.698))
;
(test(<= ?v15 0.919))
=>
(assert (result(was ?class) (got 1))))


(defrule a7
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(<= ?v18 0.902))
(test(> ?v12 2.582))
(test(> ?v12 2.994))
(test(<= ?v22 0.402))
=>
(assert (result(was ?class) (got 4))))


(defrule a8
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v12 2.994))
(test(> ?v18 0.505))
(test(> ?v12 2.582))
(test(<= ?v18 0.902))
(test(> ?v22 0.402))
=>
(assert (result(was ?class) (got 1))))

;;;;

(defrule a9
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(<= ?v3 1.649))
=>
(assert (result(was ?class) (got 4))))

(defrule a10
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(> ?v22 4.433))
=>
(assert (result(was ?class) (got 1))))

(defrule a11pt1
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(<= ?v22 4.433))
(test(> ?v18 2.689))
;
(test(<= ?v15 1.332))
=>
(assert (result(was ?class) (got 3))))

(defrule a11pt2
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(<= ?v22 4.433))
(test(> ?v18 2.689))
;
(test(> ?v15 1.332))
=>
(assert (result(was ?class) (got 1))))



(defrule a12
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(<= ?v22 4.433))
(test(<= ?v18 2.689))
(test(<= ?v12 1.929))
=>
(assert (result(was ?class) (got 3))))



(defrule a13
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(<= ?v22 4.433))
(test(<= ?v18 2.689))
(test(> ?v12 1.929))
(test(<= ?v15 1.339))
=>
(assert (result(was ?class) (got 3))))


(defrule a14
(data(v15 ?v15) (v12 ?v12) (v18 ?v18) (v22 ?v22) ( v3 ?v3) (class ?class))
(test(> ?v15 0.9))
(test(> ?v12 0.909))
(test(> ?v18 0.505))
(test(> ?v18 0.902))
(test(> ?v3 1.649))
(test(<= ?v22 4.433))
(test(<= ?v18 2.689))
(test(> ?v12 1.929))
(test(> ?v15 1.339))
=>
(assert (result(was ?class) (got 1))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;; WAS 1 GOT X  ;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defrule b1
(result(was 1)(got 1))
?fact <- (result(was 1)(got 1))
=>
(bind ?*was1got1* (+ 1 ?*was1got1*) )
;(printout t "was1got1 is = " ?*was1got1* crlf)
(retract ?fact))

(defrule b2
(result(was 1)(got 2))
?fact <- (result(was 1)(got 2))
=>
(bind ?*was1got2* (+ 1 ?*was1got2*))
;(printout t "was1got2 is = " ?*was1got2* crlf)
(retract ?fact))

(defrule b3
(result(was 1)(got 3))
?fact <- (result(was 1)(got 3))
=>
(bind ?*was1got3* (+ 1 ?*was1got3*) )
;(printout t "was1got3 is = " ?*was1got3* crlf)
(retract ?fact))

(defrule b4
(result(was 1)(got 4))
?fact <- (result(was 1)(got 4))
=>
(bind ?*was1got4* (+ 1 ?*was1got4*) )
;(printout t "was1got4 is = " ?*was1got4* crlf)
(retract ?fact))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;; WAS 2 GOT X  ;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defrule c1
(result(was 2)(got 1))
?fact <- (result(was 2)(got 1))
=>
(bind ?*was2got1* (+ 1 ?*was2got1*) )
;(printout t "was2got1 is = " ?*was2got1* crlf)
(retract ?fact))

(defrule c2
(result(was 2)(got 2))
?fact <- (result(was 2)(got 2))
=>
(bind ?*was2got2* (+ 1 ?*was2got2*) )
;(printout t "was2got2 is = " ?*was2got2* crlf)
(retract ?fact))

(defrule c3
(result(was 2)(got 3))
?fact <- (result(was 2)(got 3))
=>
(bind ?*was2got3* (+ 1 ?*was2got3*) )
;(printout t "was2got3 is = " ?*was2got3* crlf)
(retract ?fact))

(defrule c4
(result(was 2)(got 4))
?fact <- (result(was 2)(got 4))
=>
(bind ?*was2got4* (+ 1 ?*was2got4*) )
;(printout t "was2got4 is = " ?*was2got4* crlf)
(retract ?fact))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;; WAS 3 GOT X  ;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrule d1
(result(was 3)(got 1))
?fact <- (result(was 3)(got 1))
=>
(bind ?*was3got1* (+ 1 ?*was3got1*) )
;(printout t "was3got1 is = " ?*was3got1* crlf)
(retract ?fact))

(defrule d2
(result(was 3)(got 2))
?fact <- (result(was 3)(got 2))
=>
(bind ?*was3got2* (+ 1 ?*was3got2*) )
;(printout t "was3got2 is = " ?*was3got2* crlf)
(retract ?fact))

(defrule d3
(result(was 3)(got 3))
?fact <- (result(was 3)(got 3))
=>
(bind ?*was3got3* (+ 1 ?*was3got3*) )
;(printout t "was3got3 is = " ?*was3got3* crlf)
(retract ?fact))

(defrule d4
(result(was 3)(got 4))
?fact <- (result(was 3)(got 4))
=>
(bind ?*was3got4* (+ 1 ?*was3got4*) )
;(printout t "was3got4 is = " ?*was3got4* crlf)
(retract ?fact))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;; WAS 4 GOT X  ;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrule e1
(result(was 4)(got 1))
?fact <- (result(was 4)(got 1))
=>
(bind ?*was4got1* (+ 1 ?*was4got1*) )
;(printout t "was4got1 is = " ?*was4got1* crlf)
(retract ?fact))

(defrule e2
(result(was 4)(got 2))
?fact <- (result(was 4)(got 2))
=>
(bind ?*was4got2* (+ 1 ?*was4got2*) )
;(printout t "was4got2 is = " ?*was4got2* crlf)
(retract ?fact))

(defrule e3
(result(was 4)(got 3))
?fact <- (result(was 4)(got 3))
=>
(bind ?*was4got3* (+ 1 ?*was4got3*) )
;(printout t "was4got3 is = " ?*was4got3* crlf)
(retract ?fact))

(defrule e4
(result(was 4)(got 4))
?fact <- (result(was 4)(got 4))
=>
(bind ?*was4got4* (+ 1 ?*was4got4*) )
;(printout t "was4got4 is = " ?*was4got4* crlf)
(retract ?fact))

