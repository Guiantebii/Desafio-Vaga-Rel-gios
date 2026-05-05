# Watch API — Controle de Estoque de Relógios

Este projeto foi desenvolvido como resolução de um desafio técnico de backend, com o objetivo de simular um cenário real de construção de uma API REST completa utilizando boas práticas de desenvolvimento.

Além dos requisitos propostos, foram implementadas melhorias como:

* organização em camadas (controller, service, repository)
* uso de DTOs e mapeamento manual
* tratamento global de exceções
* filtros dinâmicos com Specifications
* paginação e ordenação

---

## Tecnologias utilizadas

* Java 17+
* Spring Boot (Web, Data JPA, Validation)
* PostgreSQL
* Lombok

---

## Arquitetura

O projeto segue uma arquitetura em camadas, promovendo separação de responsabilidades e facilidade de manutenção:

```
entity → repository → service → controller
```

Estrutura:

```
com.market.watchapi
  ├── entity
  ├── repository
  ├── service
  ├── controller
  ├── dto
  ├── mapper
  ├── config
  └── exception
```

---

## Funcionalidades

### Listagem de relógios

* Paginação

* Busca textual (marca, modelo, referência)

* Filtros combináveis:

  * marca
  * tipoMovimento
  * materialCaixa
  * tipoVidro
  * precoMin / precoMax
  * resistenciaMin / resistenciaMax
  * diametroMin / diametroMax

* Ordenação:

  * newest
  * price_asc
  * price_desc
  * diameter_asc
  * wr_desc

---

### CRUD completo

* Criar relógio
* Buscar por ID
* Atualizar relógio
* Remover relógio

---

## Endpoints principais

### Listar relógios

```
GET /api/relogios
```

Exemplo:

```
/api/relogios?tipoMovimento=automatic&precoMin=50000&precoMax=200000
```

---

### Buscar por ID

```
GET /api/relogios/{id}
```

---

### Criar relógio

```
POST /api/relogios
```

Exemplo de body:

```json
{
  "marca": "Seiko",
  "modelo": "Presage Cocktail Time",
  "referencia": "SRPB43J1",
  "tipoMovimento": "automatic",
  "materialCaixa": "steel",
  "tipoVidro": "sapphire",
  "resistenciaAguaM": 50,
  "diametroMm": 40,
  "lugToLugMm": 47,
  "espessuraMm": 11,
  "larguraLugMm": 20,
  "precoEmCentavos": 289990,
  "urlImagem": "https://picsum.photos/seed/relogio/800/800"
}
```

---

### Atualizar relógio

```
PUT /api/relogios/{id}
```

---

### Remover relógio

```
DELETE /api/relogios/{id}
```

---

## Exemplo de resposta

```json
{
  "items": [
    {
      "id": "uuid",
      "marca": "Seiko",
      "modelo": "Diver 200m",
      "precoEmCentavos": 159990,
      "etiquetaResistenciaAgua": "mergulho",
      "pontuacaoColecionador": 73
    }
  ],
  "total": 1
}
```

---

## Como rodar o projeto

### Pré-requisitos

* Java 17+
* PostgreSQL
* Maven

### Passos
```
git clone https://github.com/seu-usuario/seu-repo.git
cd seu-repo
mvn spring-boot:run
```

## Tratamento de erros

A API possui tratamento global de exceções com respostas padronizadas:

* 400 — erro de validação
* 404 — recurso não encontrado

---

## Dados iniciais

Ao iniciar a aplicação, registros de exemplo são carregados automaticamente para facilitar testes.

---

## Decisões técnicas

* DTO + Mapper manual para maior controle
* Specifications para filtros dinâmicos e escaláveis
* Service centralizado para regras de negócio
* Paginação limitada para evitar sobrecarga

---

* Documentação com Swagger (OpenAPI)
* Testes automatizados (JUnit)
* Dockerização
* Autenticação e autorização
