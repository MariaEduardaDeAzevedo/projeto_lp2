# PROJETO LP2

## Parte 1

### US1
#### Codificado:
- Classe Pesquisa
- Classe ControllerPesquisas
- Métodos necessários para implementar os métodos da Facade da US1
#### Falta:

#### Comentário:
- Até o momento, o código atende às especificações pedidas na US1, passando nos testes do EasyAccept propostos até o momento.
- Ambas as classes adicionadas herdam da classe Validacao, em virtude do uso constante dos casos de lançamento de exceções.

### US2
#### Codificado:
- Classe Pesquisador
- Classe ControllerPesquisador
- Métodos necessários para implementar os métodos da Facade US2.
- Métodos de verificação de e-mail e URL na classe Validação.
- Testes de unidade da US1.
#### Falta:

#### Comentário:
- O código atende à todas as especificações, obtendo êxito nos testes de aceitação.
- Todas as classes adicionadas herdam da classe validacao devido à necessidade de se verificar entradas para lançar exceções constantemente.

### US3
#### Codificado:
- Classe Objetivo e ControllerObjetivos.
-Classe Problema e ControllerProblemas.
- Métodos necessários para implementar os métodos da Facade da US3.
-Testes de unidade da US4.
#### Falta:

#### Comentário:
- O codigo atende à todas as especificações e os testes de aceitação não apontam nenhum erro neste.
- Todas as classes adicionadas herdam da classe Validacao devido à necessidade de se verificar entradas para lançar exceções constantemente.

### US4
#### Codificado:
- Classe Item
- Classe Atividade
- Classe ControllerAtividades
- Métodos necessários para implementar os métodos da Facade da US4
- Métodos da US4 na Facade
- Iniciada Classe Validacao
#### Falta:
- ~~Tratar erros~~
- ~~Adequar EasyAccepts~~
- ~~Documentar~~
#### Comentário:
- ~~Ainda irei revisar o código e adequar melhor à especificação. Codifiquei uma primeira versão para o que foi pedido na primeira parte do projeto~~
- **Adicionei uma Classe Validacao e proponho que a gente faça dela uma classe mãe e todas as classes do nosso sistema herdem dela, já que os casos de lançamento de exceções são comuns para todas as classes.** 
- ~~Adequei parte dos EasyAccepts. Ficaram dois erros que não consegui identificar de onde vieram e desconfio de um problema nos testes da US4.~~
- Talvez ainda altere a implementação de alguns detalhes.

## Parte 2
### US5
#### Codificado:
- Funcionaliades da US5
#### Falta:
- ~Testes da US6~_Casos principais concluídos_
#### Comentários:
- ~Ficou um erro remanescente da US5 e irei discutir posteriormente sobre a corretude desse caso de teste na US5.~ _US5 atualizada_

### US6
#### Codificado:
- Todas as funcionalidades da US6.
- Documentação da US6.
- Testes de unidade da US5.
#### Falta:
#### Comentário:
- Foi utilizado herança para implementar as funcionalidades das classes Professor e Aluno, que são subclasses de Pesquisador.
- O código não falha em nenhum teste de aceitação.

## Parte 3
### US9
#### Codificado:
- Funcionalidades de adicionar e retirar uma atividade subsequente.
#### Falta:
- Implementar as funcionalidades de contar próximas atividades, pegar uma próxima atividade e pegar a atividade de maior risco.
- Documentação do código.
- Testes de unidade de outra US.
- Realizar a adequação a todos os testes de aceitação.
#### Comentário:
-Todas as funcionalidades até agora implementadas desta US atendem aos testes de aceitação, porém suspeito de erro na linha 30 destes.
