INSERT INTO process(
	name_requester, zip_code, city, district, street, num_house, date_process, filename_process, deleted, created_at)
	SELECT 'José', '58000000', 'João Pessoa', 'José Américo', 'Rua Alfa', '100', now(), '175897491941.pdf', false, now() WHERE NOT EXISTS (
    SELECT 1 FROM process WHERE name_requester='José'); --Inserir caso não exista, só para testes

INSERT INTO process(
	name_requester, zip_code, city, district, street, num_house, date_process, filename_process, deleted, created_at)
	SELECT 'Maria', '58000000', 'João Pessoa', 'Bancários', 'Rua Beta', '150', now(), '175897458721.pdf', false, now() WHERE NOT EXISTS (
    SELECT 1 FROM process WHERE name_requester='Maria'); --Inserir caso não exista, só para testes

INSERT INTO process(
	name_requester, zip_code, city, district, street, num_house, date_process, filename_process, deleted, created_at)
	SELECT 'Pedro', '58000000', 'João Pessoa', 'Manaíra', 'Rua Bonfim', '129', now(), '175897898735.pdf', false, now() WHERE NOT EXISTS (
    SELECT 1 FROM process WHERE name_requester='Pedro'); --Inserir caso não exista, só para testes