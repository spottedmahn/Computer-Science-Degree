(defun my-swap (listIn)
 (append (last listIn) (reverse (rest (reverse (rest listIn)))) (list (first listIn))))