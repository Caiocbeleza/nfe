-- Emitente
INSERT INTO emitente (id, cnpj, razao_social, ie, uf)
VALUES (1, '34177678000166', 'Gasparzinho Co.', '123456789', 'SP'),
       (2, '62033589000181', 'Uma Empresa do Malvado Doofenshmirtz', '456789011', 'SP');

-- Nota Fiscal
INSERT INTO nota_fiscal (id, emitente_id, total_nota, icms, total_com_imposto, protocolo_autorizacao, xml, nome, documento, uf)
VALUES (
    1,
    1,
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
  (1, 'P001', 'Caneta Azul', '12345678', '5101', 2.50),
  (2, 'P002', 'Caderno 100 folhas', '87654321', '5102', 12.90),
  (3, 'P003', 'Borracha Escolar', '11223344', '5101', 1.20);

-- ItemNota
INSERT INTO item_nota (id, produto_id, quantidade, valor_total, nota_fiscal_id)
VALUES
  (1, 1, 2, 5.00, 1),
  (2, 2, 1, 12.90, 1);

