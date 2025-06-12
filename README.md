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

## Dados iniciais

Para facilitar os testes, ao ser iniciada, a aplicação já faz uma carga de dados iniciais, a partir do arquivo "import.sql", com o seguinte script:

```
-- Emitente
INSERT INTO emitente (id, cnpj, razao_social, ie, uf)
VALUES (100, '34177678000166', 'Gasparzinho Co.', '123456789', 'SP'),
       (101, '62033589000181', 'Uma Empresa do Malvado Doofenshmirtz', '456789011', 'SP');

-- Nota Fiscal
INSERT INTO nota_fiscal (id, emitente_id, total_nota, icms, total_com_imposto, protocolo_autorizacao, xml, nome, documento, uf)
VALUES (
    100,
    101,
    17.90,
    3.22,
    21.12,
    'PROTOCOLO-FAKE-001',
    '<xml>Simulação NF-e</xml>',
    'Fulano da Silva',
    '12345678901',
    'RJ'
);

-- Produto
INSERT INTO produto (id, codigo, nome, ncm, cfop, valor_unitario)
VALUES
  (100, 'P001', 'Caneta Azul', '12345678', '5101', 2.50),
  (200, 'P002', 'Caderno 100 folhas', '87654321', '5102', 12.90),
  (300, 'P003', 'Borracha Escolar', '11223344', '5101', 1.20);

-- ItemNota
INSERT INTO item_nota (id, produto_id, quantidade, valor_total, nota_fiscal_id)
VALUES
  (100, 100, 2, 5.00, 100),
  (200, 200, 1, 12.90, 100);
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
      "cnpj": "65.225.377/0001-94",
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

3. Notas
   
   #### POST /notas
   Cria uma nova Nota Fiscal, a partir dos parâmetros passados. Body exemplo:
   
```
   {
    "emitenteId": 100,
    "destinatario": {
        "nome": "Cicrano Pereira",
        "documento": "356.199.070-31",
        "uf": "DF"
    },
    "itens": [
        {
            "produtoId": 100,
            "quantidade": 2
        }
    ]
   }
```

   #### POST /notas/{idNota}/enviar
   Simula o envio do XML e retorna as informações da nota e o XML que seria enviado. Exemplo de response:

```
{
    "id": 1,
    "protocoloAutorizacao": "b31fcb3d-947b-4272-8829-95bc777a7cad",
    "totalNota": 5.00,
    "icms": 0.9000,
    "totalComImposto": 5.9000,
    "emitente": {
        "cnpj": "34177678000166",
        "razaoSocial": "Gasparzinho Co.",
        "ie": "123456789",
        "uf": "SP"
    },
    "destinatario": {
        "nome": "Cicrano Pereira",
        "documento": "356.199.070-31",
        "uf": "DF"
    },
    "itens": [
        {
            "quantidade": 2,
            "valorTotal": 5.00
        }
    ]
}

```
