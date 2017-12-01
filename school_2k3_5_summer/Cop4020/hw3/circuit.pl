and(on, on, on).
and(off, on, off).
and(on, off, off).
and(off, off, off).
or(on, on, on).
or(on, off, on).
or(off, on, on).
or(off, off, off).
not(off, on).
not(on, off).
circuit(A, B, C, D, E, F, G, Z) :-
or(A, B, H), not(C, I), and(I, H, L), and(G, F, K), or(L, K, N), and(E, D, 
J),
or(I, J, M), and(M, K, O), and(N, O, Z).


