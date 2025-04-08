# four-ecom-core

Para avaliarmos suas habilidades, criamos um desafio técnico que simula um cenário real. O desafio testa seu conhecimento em Spring Boot, MySQL, Elasticsearch, Kafka e arquitetura limpa.

🎯 Objetivo

Construir um sistema de gerenciamento de pedidos e produtos para um e-commerce, garantindo:

    Autenticação segura com JWT.

    CRUD completo de produtos.

    Gerenciamento de pedidos com regras de negócio.


📌 Orientações
🔑 Autenticação

    Implementar JWT para autenticação.

    Criar dois perfis de usuário:

        Admin

            Pode criar, atualizar e deletar produtos.

            Pode extrair relatórios.

        User

            Pode criar pedidos, pagar pedidos e visualizar produtos.

📦 Produtos

    Implementar o CRUD completo de produtos com os seguintes campos:

        id (UUID)

        nome

        descrição

        preço

        categoria

        quantidade em estoque

        data de criação

        data de atualização

    A rota de busca de produtos deve utilizar o Elasticsearch, permitindo filtros por:

        Nome (com tolerância a erros de digitação)

        Categoria

        Faixa de preço

    Apenas produtos com estoque disponível devem ser retornados na busca.

🎫 Pedidos

    Um usuário pode criar um pedido com múltiplos produtos.

    Um usuário pode realizar o pagamento do pedido.

📜 Relatórios

    Retornar os top 5 usuários que mais compraram, com possibilidade de filtro por datas.

    Retornar o ticket médio dos pedidos por usuário, com possibilidade de filtro por datas.

    Retornar o valor total faturado no mês atual.

🆔 Entidades

    Todas as entidades devem ser persistidas em MySQL (banco principal).

    Produtos devem ser indexados também no Elasticsearch para facilitar buscas.


💼 Regras de negócio

    Ao criar um pedido, o status inicial deve ser PENDENTE.

        Caso algum produto não tenha estoque suficiente, o pedido deve ser salvo como CANCELADO, e o usuário informado.

    O valor total do pedido e os preços dos produtos devem ser calculados com base no preço atual dos produtos no momento da criação do pedido.

    Após o pagamento do pedido, um evento order.paid deve ser enviado para o Kafka.

        Um consumer deve escutar esse evento e realizar a atualização do estoque dos produtos envolvidos.

    Todas as rotas devem aplicar corretamente autenticação e autorização, respeitando os perfis dos usuários.

    Ao criar ou atualizar um produto, esta operação deverá ser replicada no elasticSearch.


🎓 Avaliação

Avaliaremos suas decisões arquiteturais, a qualidade do código, a correta implementação das camadas de segurança, o tratamento das regras de negócio, e demais aspectos relevantes.

Também será considerado:

    O cuidado com a consistência dos dados entre MySQL e Elasticsearch.

    A tomada de decisão sobre quando aplicar transações, garantindo a integridade das operações.

    A adoção de boas práticas de desenvolvimento, com atenção à organização da arquitetura, separação de responsabilidades, segurança do código.


🧑‍💻 Tecnologias

    Java 17+
    Springboot (Security, Data, Web, etc.)
    Mysql
    ElasticSearch
    Kafka