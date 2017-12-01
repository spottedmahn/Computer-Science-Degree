rides_for(petacchi, fassa_bortolo).
rides_for(armstrong, usps).
rides_for(ullrich, bianchi).
rides_for(mcgee, francois_de_jeux).
rides_for(piil, csc).
rides_for(beloki, once).
rides_for(pena, usps).
rides_for(botero, telekom).
rides_for(vinokourov, telekom).
rides_for(millar, credit_agricole).
rides_for(mckwen, lotto).
rides_for(merckx, lotto).
rides_for(cooke, francois_de_jeux).
rides_for(heras, usps).
rides_for(casero, bianchi).
rides_for(mayo, euskatel_euskadi).
rides_for(hamilton, csc).
rides_for(virenque, quick_step).
rides_for(laiseki, euskatel_euskadi).
rides_for(nazon, delatour).
rides_for(booegard, rabobank).
rides_for(friere, rabobank).
rides_for(zabel, telekom).
stage_winner(mcgee, prologue).
stage_winner(vinokourov, 9).
stage_winner(mayo, 8).
stage_winner(virenque, 7).
stage_winner(cooke, 2).
stage_winner(petacchi, 3).
stage_winner(petacchi, 1).
stage_winner(petacchi, 5).
stage_winner(petacchi, 6).
stage_winner(usps, 4).
stage_winner(piil, 10).
bikes_ridden(usps, trek).
bikes_ridden(delatour, colnago).
bikes_ridden(csc, cervelo).
bikes_ridden(fassa_bortolo, pinarello).
bikes_ridden(quick_step, time).
bikes_ridden(rabobank, colnago).
bikes_ridden(francois_de_jeux, lapierre).
bikes_ridden(binachi, bianchi).
bikes_ridden(telekom, pinarello).
bikes_ridden(euskatel_euskadi, orbea).
bikes_ridden(credit_agricole, look).
bikes_ridden(once, giant).
yellow_jersey(mcgee).
yellow_jersey(virenque).
yellow_jersey(nazon).
yellow_jersey(pena).
yellow_jersey(armstrong).
makers_win_stages(X) :- stage_winner(Y, _), rides_for(Y, Z), bikes_ridden(Z, 
X).
teams_won(X) :- stage_winner(Y, _), rides_for(Y, X).
teams_had_yellow_jersey(X) :- yellow_jersey(Y), rides_for(Y, X).
makers_had_yellow_jersey(X) :- yellow_jersey(Y), rides_for(Y, Z), 
bikes_ridden(Z, X).



