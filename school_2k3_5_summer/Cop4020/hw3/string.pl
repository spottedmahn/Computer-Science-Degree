conc([], L, L).
conc([X|L1], L2, [X|L3]) :- conc(L1, L2, L3).

memberr(X, [X|_]).
memberr(X, [_|Tail]) :- memberr(X, Tail).

reversee([], []).
reversee([Head|Tail], L) :- reversee(Tail, M), conc(Head, M, L).



