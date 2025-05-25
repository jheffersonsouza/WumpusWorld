Gastei 02h e 58 - 05/05
Gastei mais umas 3 horas - 06/05
Gastei mais 1 hora - 09/05

TODO:<br>
~~1. Ajeitar a nomenclatura de Traits~~
~~4. Criar o mecanismo de seeds para o WorldGenerator~~

1. Criar a movimentação do Monstro.
2. Criar outros tipos de movimentos para o Caçador
3. Criar a possibilidade do caçador atirar x flecha(s)
4. Ler os outros TODO dentro do código que deve ter mais coisa para ser feita.
5. "mecanismo de colisão", ver quando caiu no buraco, pegou ouro, foi pego pelo monstro.
Muito provavelmente quando uma entidade se mover o check no novo tile dela acontece, reaproveitando uma iteração
sobre a lista de entidades que já ta la

Melhor verificação de condição de vitória, ajeitar nomenclatura, pattern e etc... 
A maneira atual é que é verificado sempre que um movimento ocorre, o que não parece incorreto, mas talvez
tenha uma maneira de disparada, um metodo abstrato disparado quando o fim for reconhecido.
Os tipos de fim: Perda (obj pode ser false ou true, isAlive é false), Vitória(obj tem que ser true,
winned tem que ser true, isAlive tem que ser true)
Cancelamento de movimento, talvez criar como eventos baseado no Bukkit, atualmente eu cancelo checando
no loop(while) por shouldMove() que é setado se morto ou se ganhou(obj && winned).


Mock de matriz que compara posição 
Criar um game tick que espera movimentação, mas ai eu vou ter
que decidir a prioridade de movimentação que nao vai ser
paralela
