Deadlock - 

O Deadlock ocorre quando um processo tenta acessar múltiplas threads que estão ocupadas, ou quando 
diversos processos tentam acessar o mesmo recurso, sendo que o recurso não dispõe de concorrência.
Se o processo que adquiriu a exclusividade sobre aquele recurso por algum motivo parar de funcionar,
os outros processos podem ficar esperando indefinidamente até que o recurso seja liberado, isso
configura um deadlock.

além deste tipo de deadlock, exite o caso onde dois processos estão utilizando o mesmo recurso,
porém um está esperando o outro simultaneamente (situação conhecida como espera circular).

Para o tratamento de deadlocks, deve ser necessário implementar rotinas de liberação automática 
de recurso (yeld), encerramento de processos após um determinado tempo (timeout) e limpezas 
de caches de tempos em tempos, para melhora da performance e evitar o deadlock do tipo posse-e-espera
que pode ser causado por lentidão de algum recurso que o processo esteja esperando. o tratamento
correto de exceções em aplicações evita também que algum processo entre em loop, causando um tipo 
de deadlock chamado starvation.

Stream and Parallel Stream

O Parallel Stream funciona exatamente igual ao Stream (mesmas funções). A única diferença é que no 
Parallel Streams você pode utilizar de paralelismo para execução da funcionalidade, assim dividindo
a tarefa em multiplos threads, e reduzindo o tempo de execução da task.

O Parallel stream deve ser evitado em casos que a tarefa à ser concorrente seja muito longa, pois 
ela irá bloquear o thread pool da VM, fazendo com que tarefas mais rápidas que ela fiquem aguradando
a liberação do recurso para poderem ser executadas. isso ocorre porque até o momento, a implantação
não nos permite selecionar o pool de threads que cada tarefa irá utilizar.





 
