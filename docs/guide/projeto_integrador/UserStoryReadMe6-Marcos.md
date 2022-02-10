# Programação Java
## Requisito 6
####Author: Marcos Vinicius Rodrigues de Sá

### Especificações de Requisitos

#### Requerimentos US:
#### ml-forgot-users-06

**Importante:**
As histórias são narradas do ponto de vista do usuário com base em
suas necessidades. Os serviços são expostos a partir do Marketplace para serem
consumidos pelos usuários que os solicita. Os contratos referem-se à história do usuário.

## Recuperação de Senha: Ao esquecer a senha o usuário poderá solicitar uma nova senha, onde será gerada automaticamente e enviada para seu email cadastrado. 
### User Story

| **CENÁRIO 1:** O usuário esquece sua senha.                  |
|:-----------------------------------------------------------------------|
| **DESDE**  que o usuário não saiba ou tenha esquecido sua senha                        |
| **E** que o usuário esteja cadastrado                                |
| **E** que o email seja válido                                        |
| **QUANDO** o usuário informa o email cadastrado no campo "esqueci senha" |
| **ENTÃO** é gerada uma nova senha e enviada ao email cadastrado              | |

| VALIDAÇÃO                                             |
|-------------------------------------------------------|
| * Verificar se o email informado existe na base de dados | 
      |

> Observação:
 Para que o recebimento do email com a nova senha seja concretizado é necessário informar um email válido e que este o mesmo esteja cadastrado diretamente com algum usuário existente.

##### Representação params:
<details><summary>Request</summary><p>

```PARAMS
                 PARAMS
                 
      KEY          |        VALUE
     email         |   email@exemplo.com 
```
</p></details>


### Contratos relativos a User Story
| HTTP | Modelo de URI                                             | Descrição                                                                                                                                                                                                                                                                           | US-code |
|------|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---|
| POST | /api/v1/auth/forgot/?email=email@exemplo.com                            | Gera uma nova senha e envia para o email informado. <br>Devolve resposta HTTP Status "204 NO CONTENT". | ml-forgot-users-06 |


### Modelo de template referente ao recebimento de email
![Alt text](assets/img/templateEmail.png?raw=true "Title")