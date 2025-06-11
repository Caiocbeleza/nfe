# Sistema de Emissão de Notas Fiscais - Quarkus

Este projeto simula um sistema básico de emissão de Notas Fiscais eletrônicas (NF-e) usando Java, Quarkus e JPA. Ele permite cadastrar produtos, emitentes e gerar notas fiscais com cálculo de impostos e simulação de envio para a SEFAZ.

---

## Tecnologias

- Java 17+
- Quarkus
- Panache (Hibernate ORM)
- RESTEasy (JAX-RS)
- H2 Database (para desenvolvimento)

---

## Executando

```bash
mvn quarkus:dev
```
## Endpoints REST
1. Emitentes

   #### GET /emitentes

   Retorna a lista de todos os emitentes cadastrados.

   #### POST /emitentes
    
    Cadastra um novo emitente.
    Body exemplo:
    ```
   {
      "cnpj": "34177678000166",
      "razaoSocial": "Gasparzinho Co.",
      "ie": "123456789",
      "uf": "SP"
    }   
   ```
   
2. Produtos

   #### GET /produtos
   Lista todos os produtos cadastrados.

   #### POST /produtos
   Cadastra um novo produto.Body exemplo:
   
```
   {
      "codigo": "P001",
      "nome": "Caneta Azul",
      "ncm": "12345678",
      "cfop": "5101",
      "valorUnitario": 2.50
    }
```
