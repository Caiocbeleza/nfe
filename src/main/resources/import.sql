-- Emitente
INSERT INTO emitente (id, cnpj, razaoSocial, ie, uf)
VALUES (1, '34177678000166', 'Gasparzinho Co.', '123456789', 'SP'),
       (2, '62033589000181', 'Uma Empresa do Malvado Doofenshmirtz', '456789011', 'SP');

-- Nota Fiscal
INSERT INTO nota_fiscal (id, emitente_id, totalNota, icms, totalComImposto, protocoloAutorizacao, xml, nome, documento, uf)
VALUES (
    1,
    1,
    15.40,
    2.77,
    18.17,
    'PROTOCOLO-FAKE-001',
    '<xml>Simulação NF-e</xml>',
    'Fulano da Silva',
    '12345678901',
    'RJ'
);

-- Produto
INSERT INTO produto (id, codigo, nome, ncm, cfop, valorUnitario)
VALUES
  (1, 'P001', 'Caneta Azul', '12345678', '5101', 2.50),
  (2, 'P002', 'Caderno 100 folhas', '87654321', '5102', 12.90),
  (3, 'P003', 'Borracha Escolar', '11223344', '5101', 1.20);

-- ItemNota
INSERT INTO item_nota (id, produto_id, quantidade, valorTotal, nota_fiscal_id)
VALUES
  (1, 1, 2, 5.00, 1),
  (2, 2, 1, 12.90, 1);

