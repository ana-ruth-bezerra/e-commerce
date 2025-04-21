
# Sistema Gerenciador de E-Commerce 

Projeto desenvolvido como atividade avaliativa da disciplina Programação Orientada a Objetos (INF008) do curso de Análise e Desenvolvimento de Sistemas do IFBA (3º semestre).




## Funcionalidades

- **Login**:
    - O sistema permite que você faça login com seu **e-mail** e **senha**.
- **Gerenciamento de usuários**:
    - Cadastro de usuários (podem ser do tipo `Administrator` ou `Customer`).
- **Gerenciamento de produtos**:
    - Cadastro de produtos (`Administrator`) e aquisição de produtos (`Customer`).
- **Processamento de pedidos**:
    - Clientes (`Customer`) podem adicionar produtos a um carrinho de compras, visualizar o carrinho e finalizar o pedido. 
- **Relatórios** (acessados por usuários administradores):
    - Usuários administradores (`Administrator`) podem acessar relatórios de pedido mais caro realizado e de produto com o menor estoque.




## Tecnologias Utilizadas

Este projeto foi desenvolvido na linguagem de programação **Java** na versão **OpenJDK 21.0.5 LTS** (Long-Term Support). A persistência dos dados foi feita por meio da **serialização** e **desserialização** com a criação do arquivo `ecommerce.dat`.

## Requisitos

- **Java 12** ou superior (recomendado usar a versão mais recente com suporte LTS).
## Instalação

- Faça o download da pasta zipada `INF008_T1_ANA_BEZERRA` e descompacte a pasta usando a ferramenta de sua preferência.
## Execução local

1. Abra o **Prompt de Comando** e navegue até o diretório onde a pasta foi descompactada:
- No Windows, você pode selecionar a pasta e usar o atalho `Ctrl + Shift + C`, ou clicar com o botão direito do mouse e selecionar a opção `Copiar como caminho`, e em seguida colar no terminal.
- Com o comando `cd`, você vai até o diretório da pasta descompactada:  
```bash
  cd <caminho_para_a_pasta>
```
2. Compile os arquivos com o comando abaixo:
```bash
  javac -d . *.java
```
3. Execute o programa com o comando:
```bash
  java Main
```
4. Faça o login utilizando as seguintes credenciais:
```bash
  email: admin
  password: admin
```

## Desenvolvido por:
  Ana Ruth Bezerra

## Contato

  an.bezerra@gmail.com

