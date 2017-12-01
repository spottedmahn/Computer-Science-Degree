(defun retain-first-n(n listIn)
	(remove (- (length listIn) n) listIn)
)

(defun remove(n listIn)
	(cond ((zerop n) listIn)
	      (t (remove (- n 1) (reverse( rest( reverse listIn)))) )
	)
)